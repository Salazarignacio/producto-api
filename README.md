# Producto API

Backend desarrollado en Java para la gestión de productos, pensado como base para sistemas de stock, facturación o puntos de venta (por ejemplo, supermercados o comercios).

El proyecto sigue una arquitectura en capas utilizando el **patrón DAO**, separando claramente la lógica de negocio, el acceso a datos y los controladores de la API.

---

## 🧩 Descripción general

Producto API es una aplicación backend que expone endpoints REST para realizar operaciones CRUD sobre productos, conectándose a una base de datos MySQL.

El objetivo del proyecto es construir una API mantenible, escalable y fácil de extender, aplicando buenas prácticas de diseño y organización del código.

Actualmente el proyecto se encuentra en desarrollo, avanzando de forma incremental y documentada.

---

## 🏗️ Arquitectura

La aplicación está organizada en las siguientes capas:

- **Controller**  
  Maneja las peticiones HTTP y expone los endpoints REST.

- **Service**  
  Contiene la lógica de negocio de la aplicación.

- **DAO (Data Access Object)**  
  Se encarga del acceso a datos y la comunicación con la base de datos MySQL.

- **Model**  
  Representa las entidades del dominio (por ejemplo, `Producto`).

Esta separación permite:
- Bajo acoplamiento
- Código más legible
- Facilidad para mantenimiento y testing

---

## 🛠️ Tecnologías utilizadas

- **Java**
- **Spring Boot**
- **Maven**
- **MySQL**
- **JDBC**
- **Git & GitHub**
- **NetBeans IDE**

---

## 📦 Estado del proyecto

🟡 **En desarrollo**

Implementado hasta el momento:
- Estructura base del proyecto
- Patrón DAO con interfaces genéricas
- Capa Service
- Modelo `Producto`
- Configuración inicial de Spring Boot

Próximos pasos:
- Implementación completa de la lógica CRUD
- Integración total con MySQL
- Validaciones y manejo de errores
- Documentación de endpoints

---

## 🎯 Objetivo del proyecto

El objetivo principal es desarrollar una API backend sólida, aplicando buenas prácticas de arquitectura y diseño, simulando un proyecto real orientado a un entorno productivo.

---

## 🚀 Ejecución del proyecto

1. Clonar el repositorio
2. Configurar la conexión a la base de datos MySQL
3. Ejecutar el proyecto desde el IDE o mediante Maven

---

## 📌 Notas

El desarrollo se realiza de manera progresiva, con commits que reflejan cada etapa del avance del proyecto.
