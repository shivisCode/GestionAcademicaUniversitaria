@echo off

echo =========================================
echo Iniciando Minikube
echo =========================================
minikube start -p gestion-academica --driver=docker

echo =========================================
echo Verificando Nodo Kubernetes
echo =========================================
kubectl get nodes

echo =========================================
echo Habilitando Metrics Server
echo =========================================
minikube addons enable metrics-server

echo =========================================
echo Despliegue Kubernetes completado
echo =========================================

pause