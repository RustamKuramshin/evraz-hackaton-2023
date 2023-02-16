#!/usr/bin/env bash

docker-compose rm -f -s exhauster-monitoring-api

cd backend

./gradlew bootBuildImage

docker-compose up -d exhauster-monitoring-api