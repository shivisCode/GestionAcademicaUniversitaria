@echo off
cd /d "%~dp0"

echo =========================================
echo Verificando estructura del proyecto
echo =========================================

echo Ruta actual:
cd

echo =========================================
echo Contenido carpeta kubernetes
echo =========================================

dir "..\kubernetes"

echo =========================================
echo Aplicando ConfigMap PostgreSQL
echo =========================================
kubectl apply -f "..\kubernetes\postgres-configmap.yaml"

echo =========================================
echo Desplegando PostgreSQL
echo =========================================
kubectl apply -f "..\kubernetes\postgres-deployment.yaml"

echo =========================================
echo Desplegando estudiante-service
echo =========================================
kubectl apply -f "..\kubernetes\estudiante-deployment.yaml"

echo =========================================
echo Desplegando materia-service
echo =========================================
kubectl apply -f "..\kubernetes\materia-deployment.yaml"

echo =========================================
echo Desplegando matriculacion-service
echo =========================================
kubectl apply -f "..\kubernetes\matriculacion-deployment.yaml"

echo =========================================
echo Desplegando comprobante-service
echo =========================================
kubectl apply -f "..\kubernetes\comprobante-deployment.yaml"

echo =========================================
echo Aplicando HPA
echo =========================================
kubectl apply -f "..\kubernetes\hpa.yaml"

timeout /t 10

echo =========================================
echo Verificando Pods
echo =========================================
kubectl get pods

echo =========================================
echo Verificando Servicios
echo =========================================
kubectl get svc

echo =========================================
echo Verificando HPA
echo =========================================
kubectl get hpa

echo =========================================
echo Despliegue Kubernetes completado
echo =========================================

pause