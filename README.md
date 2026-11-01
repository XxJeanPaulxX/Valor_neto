# valor-neto (Spring Boot)

API REST que calcula el **valor neto** de un empleado.

## Reglas (punto 4 de la pizarra)

| Regla | Condicion | Resultado |
|---|---|---|
| Descuento 8% | sueldo >= S/ 2000 | S/ 1200 no aplica, S/ 2500 si aplica |
| Bonificacion horas extras | horas trabajadas > 8 | + S/ 300 |

`valorNeto = sueldo - descuento + bonificacion`

> El umbral de S/ 2000 es un supuesto (la pizarra solo muestra S/ 1200 y S/ 2500).
> Se cambia en `src/main/resources/application.properties` sin tocar el codigo.

## Abrir en IntelliJ

1. `File > Open...` y elegir la carpeta **valor-neto** (donde esta el `pom.xml`).
2. Si pregunta, elegir **Trust Project** y **Load Maven Project** (la primera vez descarga dependencias, necesita internet).
3. JDK 17 o superior (`File > Project Structure > SDK`).
4. Ejecutar la clase `ValorNetoApplication` (boton verde de play).

## Probar la API (punto 5: request / response)

**GET** (datos en la URL) — abrir en el navegador:

```
http://localhost:8080/api/valor-neto?sueldo=2500&horas=9&nombre=Pedro
```

**POST** (datos en el cuerpo JSON) — Postman o curl:

```
curl -X POST http://localhost:8080/api/valor-neto \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Pedro","sueldo":2500,"horasTrabajadas":9}'
```

Respuesta (RESPONSE):

```json
{
  "nombre": "Pedro",
  "sueldo": 2500.00,
  "horasTrabajadas": 9,
  "aplicaDescuento": true,
  "descuento": 200.00,
  "aplicaBonificacion": true,
  "bonificacion": 300.00,
  "valorNeto": 2600.00
}
```

Si los datos son invalidos (por ejemplo sueldo negativo) responde `400` con el detalle del error.

## Docker (punto 3)

```
docker compose up --build
```

La API queda en `http://localhost:8080`. Para detenerla: `docker compose down`.

## Principio S — responsabilidad unica (punto 2)

Cada clase hace **una sola cosa**:

| Clase | Responsabilidad |
|---|---|
| `ValorNetoController` | Recibir el request HTTP (GET/POST) y devolver el response |
| `CalcularValorNeto` | Calcular el valor neto (la logica de negocio) |
| `ReglasNetoProperties` | Guardar las reglas configurables (8%, S/ 300, etc.) |
| `ValorNetoRequest` / `ValorNetoResponse` | Datos de entrada y de salida |
| `ManejadorErrores` | Convertir errores de validacion en respuestas 400 |

## Repositorio y roles (punto 1)

1. Juan (**ADMIN**) crea el repositorio en GitHub y sube el proyecto:
   ```
   git init
   git add .
   git commit -m "feat: proyecto inicial valor-neto"
   git branch -M main
   git remote add origin https://github.com/<usuario-de-juan>/valor-neto.git
   git push -u origin main
   ```
2. En GitHub: `Settings > Collaborators > Add people` y agregar a **Pedro**.
   Su rol es de colaborador con permiso de escritura (equivale a **Developer**; en GitLab se elige el rol *Developer* en `Manage > Members`).
3. Pedro clona el repo, trabaja en su rama y hace commits con mensajes claros (el profe puede revisar el historial).

## Tests

```
mvn test
```

(o en IntelliJ: clic derecho a `src/test` > *Run All Tests*).
