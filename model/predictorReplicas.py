import pandas as pd
from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_absolute_error, r2_score
from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import OneHotEncoder
from sklearn.pipeline import Pipeline

# Leer dataset desde archivo CSV
df = pd.read_csv("dataset.csv")

X = df[
    [
        "servicio",
        "usuarios_concurrentes",
        "cpu_promedio",
        "tiempo_respuesta_ms"
    ]
]

y = df["replicas_recomendadas"]

# Transformar variable categórica servicio
preprocesador = ColumnTransformer(
    transformers=[
        (
            "servicio_encoder",
            OneHotEncoder(handle_unknown="ignore"),
            ["servicio"]
        )
    ],
    remainder="passthrough"
)

modelo = Pipeline(
    steps=[
        ("preprocesador", preprocesador),
        (
            "regresor",
            RandomForestRegressor(
                n_estimators=100,
                random_state=42
            )
        )
    ]
)

X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.3,
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
    "servicio": [
        "matriculacion-service",
        "materia-service",
        "estudiante-service",
        "comprobante-service"
    ],
    "usuarios_concurrentes": [250, 600, 100, 180],
    "cpu_promedio": [130, 180, 60, 95],
    "tiempo_respuesta_ms": [1100, 2200, 500, 900]
})

resultado = modelo.predict(escenarios)

print("\nPredicción de réplicas")
print("----------------------")

for i, replicas in enumerate(resultado):
    replicas_optimas = round(replicas)
    servicio = escenarios.iloc[i]["servicio"]

    print(f"\nEscenario {i + 1}")
    print(f"Servicio: {servicio}")
    print(f"Usuarios concurrentes: {escenarios.iloc[i]['usuarios_concurrentes']}")
    print(f"CPU promedio: {escenarios.iloc[i]['cpu_promedio']}%")
    print(f"Tiempo respuesta: {escenarios.iloc[i]['tiempo_respuesta_ms']} ms")
    print(f"Réplicas óptimas sugeridas: {replicas_optimas}")
    print("Comando sugerido:")
    print(f"kubectl scale deployment {servicio} --replicas={replicas_optimas}")