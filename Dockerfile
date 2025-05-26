FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/CoursesManagementAPI-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

