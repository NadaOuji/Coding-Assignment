FROM openjdk:17-jdk-alpine

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

COPY src/main/resources/application.properties /app/

ENV SPRING_CLOUD_CONFIG_URI http://config-server:8888
ENV SPRING_PROFILES_ACTIVE dev

ENTRYPOINT ["java","-Dspring.config.location=/app/application.properties","-jar","/app.jar"]
