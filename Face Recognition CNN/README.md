# 😀 Face Recognition CNN — Emotion Classification (FER2013)

## 📌 Project / Proyecto

**EN:**  
Training of a **Convolutional Neural Network (CNN)** for **facial emotion classification** using the **FER2013 dataset** from Kaggle.  
The model works with **48×48 grayscale images** and classifies faces into **7 emotion categories**.

**ES:**  
Entrenamiento de una **red neuronal convolucional (CNN)** para la **clasificación de emociones faciales** utilizando el dataset **FER2013** de Kaggle.  
El modelo trabaja con **imágenes en escala de grises de 48×48** y clasifica las caras en **7 categorías emocionales**.

---

## 📊 Dataset / Base de datos

**EN:**  
This project uses the **FER2013** dataset, which provides facial images encoded as pixel strings.  
Each sample contains:
- `emotion`: integer label (0–6)
- `pixels`: string with 2304 values (`48 × 48`)
- `Usage`: split indicator (`Training`, `PublicTest`, `PrivateTest`)

**ES:**  
Este proyecto utiliza el dataset **FER2013**, que proporciona imágenes faciales codificadas como cadenas de píxeles.  
Cada muestra contiene:
- `emotion`: etiqueta entera (0–6)
- `pixels`: cadena con 2304 valores (`48 × 48`)
- `Usage`: indicador de partición (`Training`, `PublicTest`, `PrivateTest`)

El entrenamiento se realiza **solo con las filas marcadas como `Training`**.  
Si la columna `Usage` no existe, se usa todo el dataset para entrenamiento.

---

## 🧠 Model architecture / Arquitectura del modelo

**EN:**  
The CNN is a **deep architecture inspired by VGG-style networks**, composed of:
- Multiple convolutional blocks with increasing depth
- Zero-padding to preserve spatial dimensions
- MaxPooling layers for downsampling
- Fully connected layers with Dropout
- Softmax output layer with **7 classes**

**ES:**  
La CNN es una **arquitectura profunda inspirada en redes tipo VGG**, compuesta por:
- Múltiples bloques convolucionales con profundidad creciente
- Zero-padding para preservar dimensiones espaciales
- Capas MaxPooling para reducción de resolución
- Capas totalmente conectadas con Dropout
- Capa de salida Softmax con **7 clases**

**Input shape:** `(48, 48, 1)`  
**Output:** probability distribution over 7 emotions

---

## ⚙️ Data processing / Procesado de datos

**EN:**
- Pixel strings are converted into `(48,48,1)` NumPy arrays
- Values are normalized to `[0,1]`
- Labels are one-hot encoded (`to_categorical`)

**ES:**
- Las cadenas de píxeles se convierten en arrays `(48,48,1)`
- Los valores se normalizan a `[0,1]`
- Las etiquetas se codifican en one-hot (`to_categorical`)
