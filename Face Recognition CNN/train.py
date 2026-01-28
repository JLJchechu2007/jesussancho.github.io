"""
Entrenamiento CNN para clasificación de emociones (48x48) a partir de un dataset tipo FER:
columnas esperadas: emotion (int 0..6), pixels (string "0 0 0 ..."), Usage ("Training"/etc).

Uso:
  python train.py --data data/face_detection.csv --out models/Resultados.h5

También admite .json o .jsonl (ver --format).
"""

import os
import argparse
import numpy as np
import pandas as pd
import tensorflow as tf


def load_dataset(path: str, file_format: str = "auto") -> pd.DataFrame:
    if file_format == "auto":
        ext = os.path.splitext(path)[1].lower()
        if ext == ".csv":
            file_format = "csv"
        elif ext in [".json", ".jsonl"]:
            file_format = "json"
        else:
            raise ValueError(f"Formato no soportado para {ext}. Usa .csv, .json o .jsonl")

    if file_format == "csv":
        df = pd.read_csv(path)
    elif file_format == "json":
        # Si es jsonl (una fila por línea), lines=True suele ser necesario
        # Si es un JSON "normal" (lista/dict), lines=False
        try:
            df = pd.read_json(path, lines=True)
        except ValueError:
            df = pd.read_json(path, lines=False)
    else:
        raise ValueError("file_format debe ser: auto|csv|json")

    required = {"emotion", "pixels"}
    missing = required - set(df.columns)
    if missing:
        raise ValueError(f"Faltan columnas requeridas: {missing}. Columnas disponibles: {list(df.columns)}")

    # Usage es opcional: si no está, todo será training
    if "Usage" not in df.columns:
        df["Usage"] = "Training"

    return df


def pixels_to_image_array(pixels_series: pd.Series) -> np.ndarray:
    """
    Convierte una serie de strings "0 1 2 ..." a un array (N,48,48,1) float32.
    """
    n = len(pixels_series)
    x = np.zeros((n, 48, 48, 1), dtype=np.float32)

    for i, s in enumerate(pixels_series):
        # s puede venir como string; por seguridad casteamos
        vals = np.fromstring(str(s), sep=" ", dtype=np.float32)
        if vals.size != 48 * 48:
            raise ValueError(f"Fila {i}: pixels tiene {vals.size} valores, se esperaban 2304 (48*48).")
        x[i, :, :, 0] = vals.reshape(48, 48)

    # Normalización a [0,1] (muy recomendable)
    x /= 255.0
    return x


def build_model() -> tf.keras.Model:
    model = tf.keras.Sequential()

    model.add(tf.keras.layers.ZeroPadding2D((1, 1), input_shape=(48, 48, 1)))
    model.add(tf.keras.layers.Conv2D(64, (3, 3), activation="relu"))

    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(64, (3, 3), activation="relu"))
    model.add(tf.keras.layers.MaxPool2D((2, 2), strides=(2, 2), padding="same"))

    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(128, (3, 3), activation="relu"))
    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(128, (3, 3), activation="relu"))
    model.add(tf.keras.layers.MaxPool2D((2, 2), strides=(2, 2), padding="same"))

    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(256, (3, 3), activation="relu"))
    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(256, (3, 3), activation="relu"))
    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(256, (3, 3), activation="relu"))
    model.add(tf.keras.layers.MaxPool2D((2, 2), strides=(2, 2), padding="same"))

    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(512, (3, 3), activation="relu"))
    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(512, (3, 3), activation="relu"))
    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(512, (3, 3), activation="relu"))

    # Bloque extra "same" como en tu código
    model.add(tf.keras.layers.Conv2D(512, (3, 3), activation="relu", padding="same"))
    model.add(tf.keras.layers.Conv2D(512, (3, 3), activation="relu", padding="same"))
    model.add(tf.keras.layers.Conv2D(512, (3, 3), activation="relu", padding="same"))
    model.add(tf.keras.layers.Conv2D(512, (3, 3), activation="relu", padding="same"))

    model.add(tf.keras.layers.MaxPool2D((2, 2), strides=(2, 2), padding="same"))

    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(1024, (3, 3), activation="relu"))
    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(1024, (3, 3), activation="relu"))
    model.add(tf.keras.layers.ZeroPadding2D((1, 1)))
    model.add(tf.keras.layers.Conv2D(1024, (3, 3), activation="relu"))
    model.add(tf.keras.layers.MaxPool2D((2, 2), strides=(2, 2), padding="same"))

    model.add(tf.keras.layers.Flatten())
    model.add(tf.keras.layers.Dense(4096, activation="relu"))
    model.add(tf.keras.layers.Dropout(0.5))
    model.add(tf.keras.layers.Dense(4096, activation="relu"))
    model.add(tf.keras.layers.Dropout(0.5))
    model.add(tf.keras.layers.Dense(7, activation="softmax"))

    return model


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--data", required=True, help="Ruta al dataset (.csv/.json/.jsonl)")
    parser.add_argument("--format", default="auto", choices=["auto", "csv", "json"], help="Formato del dataset")
    parser.add_argument("--epochs", type=int, default=70)
    parser.add_argument("--batch_size", type=int, default=32)
    parser.add_argument("--val_split", type=float, default=0.3)
    parser.add_argument("--out", default="models/Resultados.h5", help="Ruta salida del mejor modelo .h5")
    args = parser.parse_args()

    df = load_dataset(args.data, args.format)

    # Split como en tu notebook: Training vs no Training
    train_mask = df["Usage"].astype(str).str.lower().eq("training")
    df_train = df.loc[train_mask].reset_index(drop=True)

    if len(df_train) == 0:
        # Si no hay Usage=Training, entrenamos con todo
        df_train = df.copy().reset_index(drop=True)

    y = df_train["emotion"].astype(int).to_numpy()
    x = pixels_to_image_array(df_train["pixels"])

    y_cat = tf.keras.utils.to_categorical(y, num_classes=7)

    print("x shape:", x.shape)
    print("y shape:", y_cat.shape)

    model = build_model()
    model.compile(optimizer="Adam", loss="categorical_crossentropy", metrics=["accuracy"])

    # Crear carpeta destino
    out_dir = os.path.dirname(args.out) or "."
    os.makedirs(out_dir, exist_ok=True)

    checkpoint = tf.keras.callbacks.ModelCheckpoint(
        filepath=args.out,
        save_weights_only=False,
        monitor="val_accuracy",
        mode="max",
        save_best_only=True,
        verbose=1,
    )

    model.fit(
        x=x,
        y=y_cat,
        batch_size=args.batch_size,
        epochs=args.epochs,
        validation_split=args.val_split,
        callbacks=[checkpoint],
        shuffle=True,
    )


if __name__ == "__main__":
    main()
