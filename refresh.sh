#!/usr/bin/env bash

git fetch && git checkout main && git pull

docker compose rm -f -s exhauster-monitoring-api exhauster-monitoring-ui

docker rmi -f evraz-hackaton-2023-exhauster-monitoring-ui:latest

cd backend

./gradlew bootBuildImage

docker compose up -d mongodb-primary  && \
docker compose up -d mongodb-secondary && \
docker compose up -d mongodb-arbiter && \
docker compose up -d exhauster-monitoring-ui && \
docker compose up -d exhauster-monitoring-api
