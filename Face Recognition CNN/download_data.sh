#!/usr/bin/env bash
set -euo pipefail

mkdir -p data

# Comprueba que la CLI de Kaggle está instalada
if ! command -v kaggle >/dev/null 2>&1; then
  echo "ERROR: No se encontró el comando 'kaggle'. Instala dependencias:"
  echo "  pip install -r requirements.txt"
  exit 1
fi

echo "Descargando dataset FER2013 desde Kaggle (msambare/fer2013) ..."
kaggle datasets download -d msambare/fer2013 -p data --unzip

# Normalmente queda como data/fer2013.csv
if [ ! -f "data/fer2013.csv" ]; then
  echo "WARNING: No se encontró data/fer2013.csv. Contenido de data/:"
  ls -lah data
else
  echo "OK: dataset listo en data/fer2013.csv"
fi
