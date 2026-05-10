@echo off

echo =========================================
echo Construyendo estudiante-service
echo =========================================
docker build -t estudiante-service ../estudiante-service

echo =========================================
echo Construyendo materia-service
echo =========================================
docker build -t materia-service ../materia-service

echo =========================================
echo Construyendo matriculacion-service
echo =========================================
docker build -t matriculacion-service ../matriculacion-service

echo =========================================
echo Construyendo comprobante-service
echo =========================================
docker build -t comprobante-service ../comprobante-service

echo =========================================
echo Imagenes Docker construidas correctamente
echo =========================================

pause