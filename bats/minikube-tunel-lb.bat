@echo off
cd /d "%~dp0"

echo =========================================
echo Iniciando Tunnel de Minikube
echo =========================================

echo.
echo Este proceso debe permanecer abierto
echo para exponer los servicios LoadBalancer.
echo.

minikube tunnel

pause