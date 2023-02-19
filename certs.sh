#!/bin/zsh

#awk 'NF {sub(/\r/, ""); printf "%s\\n",$0;}'  CA.pem

# old
openssl pkcs12 -export -in CA.pem -out cert.p12
keytool -importkeystore -srckeystore cert.p12 -srcstoretype pkcs12 -destkeystore cert.jks

# new
openssl x509 -outform der -in CA.pem -out cert.der

keytool -genkey -keyalg RSA -alias endeca -keystore kafka-client-truststore.jks
keytool -delete -alias endeca -keystore kafka-client-truststore.jks
keytool -import -v -trustcacerts -alias endeca-ca -file CA.pem -keystore kafka-client-truststore.jks

keytool -genkey -keyalg RSA -alias endeca -keystore kafka-client-keystore.jks
keytool -delete -alias endeca -keystore kafka-client-keystore.jks
keytool -v -importkeystore -srckeystore cert.der -srcstoretype x509 -deststoretype JKS -destkeystore kafka-client-keystore.jks
