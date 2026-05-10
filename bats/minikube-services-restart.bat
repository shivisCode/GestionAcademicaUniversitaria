@echo off
cd /d "%~dp0"

echo =========================================
echo Reiniciando Deployments Kubernetes
echo =========================================

kubectl rollout restart deployment estudiante-service
kubectl rollout restart deployment materia-service
kubectl rollout restart deployment matriculacion-service
kubectl rollout restart deployment comprobante-service
kubectl rollout restart deployment postgres

echo =========================================
echo Esperando reinicio de pods
echo =========================================

timeout /t 15

echo =========================================
echo Verificando Pods
echo =========================================

kubectl get pods

echo =========================================
echo Reinicio completado
echo =========================================

pause