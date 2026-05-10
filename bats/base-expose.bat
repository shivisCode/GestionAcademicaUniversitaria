@echo off
cd /d "%~dp0"

echo =========================================
echo EXPONIENDO POSTGRESQL KUBERNETES
echo =========================================

echo.
echo PostgreSQL quedara accesible en:
echo localhost:5432
echo.

kubectl port-forward service/postgres-service 5432:5432

pause