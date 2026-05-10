@echo off
cd /d "%~dp0"

echo =========================================
echo MONITOREO KUBERNETES
echo =========================================

echo.
echo Abriendo monitoreo de Pods...
start cmd /k "kubectl get pods -w"

echo.
echo Abriendo monitoreo de HPA...
start cmd /k "kubectl get hpa -w"

echo.
echo Abriendo monitoreo de Services...
start cmd /k "kubectl get svc -w"

echo.
echo Abriendo monitoreo de Deployments...
start cmd /k "kubectl get deployments -w"

echo.
echo =========================================
echo Ventanas de monitoreo iniciadas
echo =========================================

pause