# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Копіюємо головний pom.xml та всі модулі для збірки
COPY pom.xml .
COPY common/ ./common/
COPY dao/ ./dao/
COPY core-service/ ./core-service/

# Збираємо весь проект (це важливо для зв'язків між модулями)
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Аргумент для вибору модуля при збірці (за замовчуванням dao)
ARG MODULE_NAME=dao

# Копіюємо зібраний JAR-файл з першого етапу
# Використовуємо wildcard для версії, щоб не правити Dockerfile щоразу
COPY --from=build /app/${MODULE_NAME}/target/${MODULE_NAME}-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]