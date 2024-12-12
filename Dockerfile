FROM openjdk:23-jdk AS builder

ARG APP_DIR=/app
WORKDIR ${APP_DIR}

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

COPY events.json /app/events.json

RUN chmod a+x ./mvnw && ./mvnw clean package -Dmaven.test.skip=true

FROM openjdk:23-jdk

ARG DEPLOY_DIR=/app
WORKDIR ${DEPLOY_DIR}

COPY --from=builder /app/target/MOCK_SSF-0.0.1-SNAPSHOT.jar app.jar

COPY --from=builder /app/events.json /app/events.json

ENV SERVER_PORT=4000
EXPOSE ${SERVER_PORT}

ENTRYPOINT [ "java", "-jar", "app.jar" ]
