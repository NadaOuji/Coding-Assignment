FROM openjdk:17-jdk-alpine

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENV SPRING_PROFILES_ACTIVE native

ENTRYPOINT ["java","-jar","/app.jar"]
