# Coding-Assignment

## Spring Boot Microservices Application
## Introduction
To ensure high availability and fault tolerance, each service could be deployed on multiple instances, and a load
balancer is used to distribute requests among them.
By breaking down the system into smaller services, it becomes easier to develop and deploy each service independently.
This approach also allows for scaling individual services based on their usage patterns, rather than having to scale the
entire system.

This is a coding assignment is based on Spring Boot microservices that consists of multiple services, including a Config Server, Discovery Server, API Gateway, and various other microservices. The application uses OAuth2 authentication to secure the endpoints and also includes a H2 in-memory database.

## Requirements
To run the application, you need to have the following installed:

* JDK 17
* Maven 
* Spring Cloud 
* H2 database 
* OAuth2

## Getting Started

1. Clone the project from the Git repository.
2. Update the docker-credentials.yml file by adding your Docker username and token.

dockerhub:
username: <your_username>
password: <your_token>
registry: https://index.docker.io/v1/

3. Build the Docker images for each microservice by running the following commands in each microservice directory:
   
   * Config Server: docker build -t my-config-server:latest .
   * Discovery Server: docker build -t my-discovery-server:latest .
   * API Gateway: docker build -t my-api-gateway:latest .
   * Customer Service: docker build -t my-customer-service:latest .
   * Account Service: docker build -t my-account-service:latest .
   * Transaction Service: docker build -t my-transaction-service:latest .

4. Generate an SSL certificate for each microservice (if needed).

cd <microservice_directory>/src/main/resources/ssl
./generate-ssl-cert.sh
5. In the application-dockerFile.properties file, replace eureka.client.service-url.defaultZone with the Docker container name of the Discovery Server.
* eureka.client.service-url.defaultZone=http://<discovery_server_container_name>:8761/eureka/
6. Run the following command to start the application:
   docker-compose up
   
7. Access the Discovery Server by going to http://localhost:8761/dashboard. Here, you can check if all the microservices are registered and up in the desired port.

8. Access the Customer Service Swagger UI by going to https://localhost:8081/swagger-ui.html.

9. Access the Account Service Swagger UI by going to https://localhost:8082/swagger-ui.html.

10. Access the Transaction Service Swagger UI by going to https://localhost:8083/swagger-ui.html.














## Account Service:

This service would handle the creation and management of customer accounts. It would have endpoints to create new
accounts, update account information, and retrieve account information. The Account service would be responsible for
managing account balances and ensuring that each account has a unique identifier. The Account service would communicate
with the Transaction service to record transactions.

## Transaction Service:

This service would be responsible for recording transactions related to customer accounts. It would have endpoints to
record new transactions and retrieve transaction history for a given account. The Transaction service would communicate
with the Account service to verify that the account exists before recording a new transaction.

## Customer Service:

This service would handle customer information and authentication. It would have endpoints to create and update customer
information, authenticate customers, and retrieve customer information. The Customer service would communicate with the
Account and Transaction services to retrieve information about customer accounts and transactions.

## Gateway Service:

This service would act as an API gateway for the entire system. It would handle authentication and routing requests to
the appropriate service based on the requested endpoint. The Gateway service would communicate with the Customer service
to authenticate users before allowing them to access other services.
