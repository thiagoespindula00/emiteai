FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /build

COPY pom.xml .

RUN mvn -q dependency:go-offline

COPY src ./src

RUN mvn -q clean package -DskipTests

FROM bellsoft/liberica-openjre-alpine:24.0.1
WORKDIR /app

ARG JAR_FILE=target/*-SNAPSHOT.jar
COPY --from=builder /build/${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
