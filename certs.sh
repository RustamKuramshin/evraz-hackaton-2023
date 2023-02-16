#!/bin/zsh

#awk 'NF {sub(/\r/, ""); printf "%s\\n",$0;}'  CA.pem

openssl pkcs12 -export -in CA.pem -out cert.p12
keytool -importkeystore -srckeystore cert.p12 -srcstoretype pkcs12 -destkeystore cert.jks