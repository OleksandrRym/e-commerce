# --- ЕТАП 1: Збірка (Build stage) ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# 1. Копіюємо кореневий pom.xml та всі pom.xml модулів для кешування залежностей
COPY pom.xml .
COPY common/pom.xml common/
COPY dao/pom.xml dao/
COPY core-service/pom.xml core-service/

# Завантажуємо залежності (це пришвидшить наступні збірки)
RUN mvn dependency:go-offline -B

# 2. Копіюємо вихідний код усіх модулів
COPY . .

# 3. Збираємо весь проект
# install необхідний, щоб модуль common з'явився в локальному репозиторії Maven всередині Docker
RUN mvn clean install -DskipTests

# --- ЕТАП 2: Робочий образ (Run stage) ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Використовуємо ARG, щоб вказати, який саме модуль ми хочемо запустити
ARG MODULE_NAME
# Копіюємо зібраний jar файл з етапу build
# Назва jar файлу зазвичай artifactId-version.jar
COPY --from=build /app/${MODULE_NAME}/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]