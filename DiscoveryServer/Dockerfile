FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy jar and SSL script to container
COPY target/*.jar app.jar
COPY src/main/resources/ssl/generate-ssl-cert.sh generate-ssl-cert.sh
COPY src/main/resources/ssl/discovery-service-keystore.p12 discovery-service-keystore.p12

# Install necessary packages
RUN apk add --no-cache bash

# Generate SSL certificate
RUN chmod +x generate-ssl-cert.sh && ./generate-ssl-cert.sh

# Copy application.properties to container
COPY src/main/resources/application-dockerFile.properties .

EXPOSE 8761

# Set entrypoint
ENTRYPOINT ["java","-Dspring.config.location=./application-dockerFile.properties","-jar","/app/app.jar"]
