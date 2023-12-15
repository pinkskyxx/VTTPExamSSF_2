# Build stage

FROM maven:3-eclipse-temurin-21 AS builder

LABEL MAINTAINER=""
LABEL APPLICATION=""

WORKDIR /usr/app

ARG APP_DIR=/app
WORKDIR ${APP_DIR}

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

RUN mvn package -Dmaven.test.skip=true


FROM openjdk:21-bookworm 

WORKDIR /app

# copy and rename to app.jar
COPY --from=builder /app/target/eventmanagement-0.0.1-SNAPSHOT.jar app.jar


ENV PORT=8080
ENV SPRING_REDIS_HOST=localhost SPRING_REDIS_PORT=6379
ENV SPRING_REDIS_DATABASE=0
ENV SPRING_REDIS_USERNAME= SPRING_REDIS_PASSWORD=

EXPOSE $PORT

ENTRYPOINT SERVER_PORT=${PORT} java -jar ./app.jar




# setting env variables in railway:
# SPRING_REDIS_HOST
# SPRING_REDIS_PASSWORD
# SPRING_REDIS_PORT
# SPRING_REDIS_USERNAME