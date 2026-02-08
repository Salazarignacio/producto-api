# Usa Java 21
FROM eclipse-temurin:21-jdk

# Carpeta de trabajo
WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Dar permisos a mvnw
RUN chmod +x mvnw

# Compilar proyecto
RUN ./mvnw package -DskipTests

# Exponer puerto (Render usa 8080)
EXPOSE 8080

# Ejecutar jar
CMD ["java", "-jar", "target/*.jar"]
