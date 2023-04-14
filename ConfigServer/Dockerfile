FROM openjdk:17-jdk-alpine

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

# Copy application.properties to container
COPY src/main/resources/application-dockerFile.properties .

ENV SPRING_PROFILES_ACTIVE native

# Set entrypoint
ENTRYPOINT ["java","-Dspring.config.location=./application-dockerFile.properties","-jar","/app.jar"]
