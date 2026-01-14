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

Es necesario configurar la conexión a la base de datos antes de ejecutar la aplicación.

Ejemplo de configuración en `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/productos_db
spring.datasource.username=usuario
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

> ⚠️ **Nota:** Las credenciales no deben subirse al repositorio. Se recomienda usar variables de entorno o archivos de configuración locales.

---

## ▶️ Ejecución del proyecto

1. Crear la base de datos.
2. Ejecutar el script SQL para crear la tabla `Producto`.
3. Configurar los datos de conexión JDBC.
4. Ejecutar la aplicación.
5. Probar los endpoints mediante Postman, Insomnia u otra herramienta similar.

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
