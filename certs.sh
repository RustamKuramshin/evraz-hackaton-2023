#!/bin/zsh

#awk 'NF {sub(/\r/, ""); printf "%s\\n",$0;}'  CA.pem

openssl x509 -outform der -in CA.pem -out cert.der

keytool -genkey -keyalg RSA -alias endeca -keystore kafka-client-truststore.jks
keytool -delete -alias endeca -keystore kafka-client-truststore.jks
keytool -import -v -trustcacerts -alias endeca-ca -file CA.pem -keystore kafka-client-truststore.jks

keytool -genkey -keyalg RSA -alias endeca -keystore kafka-client-keystore.jks
keytool -delete -alias endeca -keystore kafka-client-keystore.jks
keytool -v -importkeystore -srckeystore cert.der -srcstoretype x509 -deststoretype JKS -destkeystore kafka-client-keystore.jks
