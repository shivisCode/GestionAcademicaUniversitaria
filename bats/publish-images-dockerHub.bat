@echo off

echo =========================================
echo Publicando imagenes en Docker Hub
echo =========================================

set /p USERNAME=Ingrese su usuario de Docker Hub: 

echo =========================================
echo Etiquetando imagenes
echo =========================================

docker tag estudiante-service %USERNAME%/estudiante-service:latest
docker tag materia-service %USERNAME%/materia-service:latest
docker tag matriculacion-service %USERNAME%/matriculacion-service:latest
docker tag comprobante-service %USERNAME%/comprobante-service:latest

echo =========================================
echo Subiendo estudiante-service
echo =========================================
docker push %USERNAME%/estudiante-service:latest

echo =========================================
echo Subiendo materia-service
echo =========================================
docker push %USERNAME%/materia-service:latest

echo =========================================
echo Subiendo matriculacion-service
echo =========================================
docker push %USERNAME%/matriculacion-service:latest

echo =========================================
echo Subiendo comprobante-service
echo =========================================
docker push %USERNAME%/comprobante-service:latest

echo =========================================
echo Imagenes publicadas correctamente
echo =========================================

pause