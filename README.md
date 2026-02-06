# Product Management API

## 📌 Descripción del proyecto

Este proyecto es un backend desarrollado en **Java** que implementa un **CRUD completo de productos**, siguiendo una **arquitectura en capas (Controller – Service – DAO)** y utilizando **JDBC** para la persistencia de datos.

La aplicación está pensada como base para sistemas de gestión comercial, inventario o puntos de venta, permitiendo crear, consultar, actualizar y eliminar productos desde una API REST.

---

## 🛠️ Tecnologías utilizadas

* Java
* Spring Boot
* JDBC
* Maven
* Base de datos relacional (MySQL / PostgreSQL)

---

## 🧱 Arquitectura

El proyecto está organizado siguiendo una arquitectura en capas bien definida:

* **Controller**
  Expone los endpoints REST y gestiona las respuestas HTTP.

* **Service**
  Contiene la lógica de negocio, validaciones y traducción de errores.

* **DAO**
  Se encarga del acceso a datos mediante JDBC y operaciones SQL.

* **Model**
  Define las entidades del dominio.

Esta separación permite un código más mantenible, escalable y fácil de testear.

---

## 🗄️ Base de datos

La aplicación requiere una base de datos con la siguiente estructura:

```sql
CREATE TABLE Producto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    articulo VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    codigo INT UNIQUE NOT NULL
);
```

---

## 🔌 Configuración JDBC

La aplicación está configurada para leer las credenciales de base de datos desde **variables de entorno** con valores por defecto predefinidos. Esto permite una configuración flexible para diferentes entornos (desarrollo, staging, producción).

### Variables de Entorno Disponibles

| Variable | Descripción | Valor por Defecto |
|----------|-------------|-------------------|
| `DB_URL` | URL de conexión JDBC | `jdbc:mysql://localhost:3306/db_user?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true` |
| `DB_USERNAME` | Usuario de la base de datos | `javauser` |
| `DB_PASSWORD` | Contraseña del usuario | `admin` |
| `DB_DRIVER` | Driver JDBC a utilizar | `com.mysql.cj.jdbc.Driver` |

### Formas de Configuración

#### 1️⃣ **Uso con valores por defecto** (Desarrollo local)
```bash
# No se necesitan variables - usa los valores por defecto
mvn spring-boot:run
```

#### 2️⃣ **Configuración con variables de entorno** (Producción/Staging)
```bash
# Exportar variables (Linux/macOS)
export DB_URL="jdbc:mysql://servidor-empresa.com:3306/produccion?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"
export DB_USERNAME="prod_user"
export DB_PASSWORD="contraseña_secreta"
export DB_DRIVER="com.mysql.cj.jdbc.Driver"

# Ejecutar aplicación
mvn spring-boot:run
```

#### 3️⃣ **Configuración temporal** (One-liner)
```bash
DB_URL="jdbc:mysql://servidor-empresa.com:3306/produccion?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true" \
DB_USERNAME="prod_user" \
DB_PASSWORD="contraseña_secreta" \
mvn spring-boot:run
```

#### 4️⃣ **Configuración con Docker Compose**
```yaml
version: '3.8'
services:
  app:
    build: .
    environment:
      - DB_URL=jdbc:mysql://mysql:3306/produccion?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
      - DB_USERNAME=prod_user
      - DB_PASSWORD=contraseña_secreta
    depends_on:
      - mysql
```

#### 5️⃣ **Configuración con archivo .env** (Recomendado para desarrollo)
Crear archivo `.env` en la raíz del proyecto:
```env
DB_URL=jdbc:mysql://localhost:3306/mi_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USERNAME=mi_usuario
DB_PASSWORD=mi_contraseña
DB_DRIVER=com.mysql.cj.jdbc.Driver
```

### Archivo de configuración
La configuración principal se encuentra en `src/main/resources/application.properties` con la siguiente sintaxis:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/db_user?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true}
spring.datasource.username=${DB_USERNAME:javauser}
spring.datasource.password=${DB_PASSWORD:admin}
spring.datasource.driver-class-name=${DB_DRIVER:com.mysql.cj.jdbc.Driver}
```

> 💡 **Ventaja de esta configuración:**
> - Los valores sensibles nunca se suben al repositorio
> - Cada entorno puede tener su propia configuración
> - La aplicación funciona sin configuración inicial (usa valores por defecto)
> - Fácil integración con CI/CD y orquestación de contenedores

> ⚠️ **Nota de Seguridad:** Nunca incluyas credenciales reales en el código fuente o commits. Usa siempre variables de entorno para información sensible.

---

## ▶️ Ejecución del proyecto

### Opción A: Con Docker Compose (Recomendado)

1. **Iniciar base de datos con Docker**
   ```bash
   docker-compose up -d
   ```

2. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

### Opción B: Con base de datos externa

1. **Crear base de datos y tabla** en tu servidor MySQL

2. **Configurar variables de entorno** (opcional)
   ```bash
   export DB_URL="jdbc:mysql://tu-servidor:3306/produccion?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"
   export DB_USERNAME="tu_usuario"
   export DB_PASSWORD="tu_contraseña"
   ```

3. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

### Opción C: Sin variables de entorno (Usa valores por defecto)

1. **Asegurar que MySQL está corriendo** en localhost:3306 con base de datos `db_user`

2. **Ejecutar aplicación** directamente
   ```bash
   mvn spring-boot:run
   ```

### Verificación
La aplicación mostrará en consola:
- ✅ Conexión a la base de datos establecida exitosamente
- 🚀 API de Productos lista para recibir peticiones en http://localhost:8080

### Probar Endpoints
Usa Postman, Insomnia o curl para probar los endpoints (ver sección "Endpoints disponibles").

---

## 🌐 Endpoints disponibles

| Método | Endpoint          | Descripción                 |
| ------ | ----------------- | --------------------------- |
| POST   | `/productos`      | Crear un nuevo producto     |
| GET    | `/productos/all`  | Obtener todos los productos |
| GET    | `/productos/{id}` | Obtener un producto por ID  |
| PUT    | `/productos/{id}` | Actualizar un producto      |
| DELETE | `/productos/{id}` | Eliminar un producto        |

---

## 📦 Ejemplo de JSON (Producto)

```json
{
  "articulo": "Yerba Mate",
  "categoria": "Alimentos",
  "precio": 1200.50,
  "stock": 50,
  "codigo": 123456
}
```

---

## 🧠 Notas finales

* El proyecto está diseñado para ser fácilmente extensible.
* Se puede agregar paginación, DTOs y manejo centralizado de excepciones.
* Ideal como base para integraciones con frontend o sistemas de gestión más complejos.

---

## 👤 Autor

Ignacio Salazar
