# Build the Spring Boot application with Java 17 and Maven.
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

# Run the packaged application on a lightweight Java 17 runtime.
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/nitte-bus-tracker-1.0.0.jar app.jar
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
