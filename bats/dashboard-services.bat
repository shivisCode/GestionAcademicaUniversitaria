@echo off
cd /d "%~dp0"

echo =========================================
echo VERIFICACION KUBERNETES
echo =========================================

echo.
echo ================= PODS ==================
kubectl get pods -o wide

echo.
echo =============== SERVICES ================
kubectl get svc

echo.
echo ============= DEPLOYMENTS ===============
kubectl get deployments

echo.
echo ================= HPA ===================
kubectl get hpa

echo.
echo =========================================
echo VERIFICACION COMPLETADA
echo =========================================

pause