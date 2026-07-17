FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


# FROM eclipse-temurin:21-jdk
# WORKDIR /app
# COPY . .
# RUN chmod +x mvnw && ./mvnw clean package -DskipTests
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
