#!/bin/bash

APP_NAME=customer-service
KEYSTORE_NAME=$APP_NAME-keystore.p12
KEYSTORE_PASSWORD=my-keystore-password
CERTIFICATE_ALIAS=$APP_NAME-alias

# Generate a self-signed SSL certificate
keytool -genkeypair -alias $CERTIFICATE_ALIAS -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore $KEYSTORE_NAME -validity 3650 -storepass $KEYSTORE_PASSWORD -dname "CN=localhost,OU=Development,O=MyOrganization,L=MyCity,S=MyState,C=MyCountry"

# Export the SSL certificate to a file
keytool -export -alias $CERTIFICATE_ALIAS -storetype PKCS12 -keystore $KEYSTORE_NAME -rfc -file $APP_NAME.cer -storepass $KEYSTORE_PASSWORD

# Import the SSL certificate into a truststore
keytool -import -alias $CERTIFICATE_ALIAS -file $APP_NAME.cer -storetype PKCS12 -keystore truststore.p12 -storepass my-truststore-password -noprompt

# Remove the exported SSL certificate file
rm $APP_NAME.cer

# Set the SSL properties in the application.properties file
echo "server.ssl.key-store=./CustomerService/src/main/resources/ssl/$KEYSTORE_NAME" >> ../application.properties;
echo "server.ssl.key-store-password=$KEYSTORE_PASSWORD" >> ../application.properties
echo "server.ssl.key-alias=$CERTIFICATE_ALIAS" >> ../application.properties
echo "server.ssl.trust-store=./CustomerService/src/main/resources/ssl/truststore.p12" >> ../application.properties
echo "server.ssl.trust-store-password=my-truststore-password" >> ../application.properties
