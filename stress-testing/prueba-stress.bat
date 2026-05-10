@echo off
cd /d "%~dp0"

:menu
cls

echo =========================================
echo         PRUEBAS DE STRESS K6
echo =========================================
echo.
echo 1. Stress estudiante-service
echo 2. Stress materia-service
echo 3. Stress matriculacion-service
echo 4. Stress comprobante-service
echo 5. Ejecutar TODAS
echo 6. Salir
echo.

set /p opcion=Seleccione una opcion: 

if "%opcion%"=="1" goto estudiante
if "%opcion%"=="2" goto materia
if "%opcion%"=="3" goto matriculacion
if "%opcion%"=="4" goto comprobante
if "%opcion%"=="5" goto todas
if "%opcion%"=="6" exit

goto menu

:estudiante
echo =========================================
echo Ejecutando stress estudiante-service
echo =========================================
k6 run stress-test-estudiante.js
pause
goto menu

:materia
echo =========================================
echo Ejecutando stress materia-service
echo =========================================
k6 run stress-test-materia.js
pause
goto menu

:matriculacion
echo =========================================
echo Ejecutando stress matriculacion-service
echo =========================================
k6 run stress-test-matriculacion.js
pause
goto menu

:comprobante
echo =========================================
echo Ejecutando stress comprobante-service
echo =========================================
k6 run stress-test-test-comprobante.js
pause
goto menu

:todas
echo =========================================
echo Ejecutando TODAS las pruebas
echo =========================================

start cmd /k "k6 run stress-test-estudiante.js"
start cmd /k "k6 run stress-test-materia.js"
start cmd /k "k6 run stress-test-matriculacion.js"
start cmd /k "k6 run stress-test-comprobante.js"

pause
goto menu