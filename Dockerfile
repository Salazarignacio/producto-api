FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY producto-api-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
