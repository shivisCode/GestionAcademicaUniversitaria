@echo off
cd /d "%~dp0"

echo =========================================
echo Port Forward estudiante-service
echo =========================================
start cmd /k "kubectl port-forward service/estudiante-service 8081:8081"

echo =========================================
echo Port Forward materia-service
echo =========================================
start cmd /k "kubectl port-forward service/materia-service 8082:8082"

echo =========================================
echo Port Forward matriculacion-service
echo =========================================
start cmd /k "kubectl port-forward service/matriculacion-service 8083:8083"

echo =========================================
echo Port Forward comprobante-service
echo =========================================
start cmd /k "kubectl port-forward service/comprobante-service 8084:8084"

echo =========================================
echo Servicios expuestos localmente
echo =========================================

pause