FROM eclipse-temurin:21-jdk

WORKDIR /app

# instalar maven
RUN apt-get update && apt-get install -y maven

# copiar proyecto
COPY . .

# compilar
RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/*.jar"]
