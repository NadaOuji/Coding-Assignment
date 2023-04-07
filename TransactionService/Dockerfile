FROM openjdk:17-jdk-alpine

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

COPY application.properties /app/

ENTRYPOINT ["java","-Dspring.config.location=/app/application.properties","-jar","/app.jar"]