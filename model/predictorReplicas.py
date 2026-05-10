import pandas as pd
from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_absolute_error, r2_score

# Dataset simulado de carga del sistema
data = {
    "usuarios_concurrentes": [50, 100, 150, 200, 300, 400, 500, 700, 900, 1200],
    "cpu_promedio": [20, 35, 45, 55, 65, 75, 82, 88, 93, 97],
    "tiempo_respuesta_ms": [120, 180, 250, 330, 450, 620, 800, 1100, 1500, 2100],
    "replicas_recomendadas": [1, 1, 2, 2, 3, 4, 4, 5, 6, 8]
}

df = pd.DataFrame(data)

X = df[["usuarios_concurrentes", "cpu_promedio", "tiempo_respuesta_ms"]]
y = df["replicas_recomendadas"]

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.3, random_state=42
)

modelo = RandomForestRegressor(
    n_estimators=100,
    random_state=42
)

modelo.fit(X_train, y_train)

predicciones = modelo.predict(X_test)

mae = mean_absolute_error(y_test, predicciones)
r2 = r2_score(y_test, predicciones)

print("Evaluación del modelo")
print("----------------------")
print(f"Error absoluto medio: {mae:.2f}")
print(f"R2 Score: {r2:.2f}")

# Escenarios de prueba
escenarios = pd.DataFrame({
    "usuarios_concurrentes": [250, 600, 1000],
    "cpu_promedio": [60, 85, 95],
    "tiempo_respuesta_ms": [400, 950, 1700]
})

resultado = modelo.predict(escenarios)

print("\nPredicción de réplicas")
print("----------------------")

servicio = "matriculacion-service"

for i, replicas in enumerate(resultado):
    replicas_optimas = round(replicas)

    print(f"\nEscenario {i + 1}")
    print(f"Réplicas óptimas sugeridas: {replicas_optimas}")
    print(f"Comando sugerido:")
    print(f"kubectl scale deployment {servicio} --replicas={replicas_optimas}")