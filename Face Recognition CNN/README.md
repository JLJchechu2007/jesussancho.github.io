Entrenamiento de una red convolucional (CNN) para clasificación de **emociones faciales** usando imágenes en escala de grises de **48×48** del dataset **FER2013**.

El repositorio está preparado para:
- Instalar dependencias con `requirements.txt`
- Descargar el dataset desde Kaggle con `download_data.sh`
- Entrenar el modelo con `train.py`
- Guardar el mejor modelo como `.h5` en `models/`

# Cómo usar este proyecto (FER2013)

## 1) Instalar dependencias
En la carpeta del proyecto, ejecuta:

pip install -r requirements.txt

## 2) Configurar Kaggle (para poder descargar el dataset)
Necesitas tu archivo kaggle.json (API token de Kaggle).

- Descárgalo desde Kaggle: Account -> API -> Create New Token
- Colócalo en tu ordenador en:

Linux/Mac:
~/.kaggle/kaggle.json

Windows:
C:\Users\TU_USUARIO\.kaggle\kaggle.json

(IMPORTANTE: no subas ese archivo a GitHub)

## 3) Descargar el dataset
Ejecuta:

./download_data.sh

Eso descargará y descomprimirá el CSV en:
data/fer2013.csv

## 4) Entrenar el modelo
Ejecuta:

python train.py --data data/fer2013.csv --out models/Resultados.h5

Al terminar, el mejor modelo se guardará en:
models/Resultados.h5
