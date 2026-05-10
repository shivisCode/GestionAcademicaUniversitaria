@echo off
cd /d "%~dp0"

echo =========================================
echo EJECUTANDO MODELO IA PREDICTIVO
echo =========================================

python predictorReplicas.py

echo.
echo =========================================
echo MODELO FINALIZADO
echo =========================================

pause