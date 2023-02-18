#!/usr/bin/env bash

git fetch && git checkout main && git pull

docker compose rm -f -s exhauster-monitoring-api exhauster-monitoring-ui

docker rmi -f evraz-hackaton-2023-exhauster-monitoring-ui:latest

cd backend

./gradlew bootBuildImage

docker compose up -d mongodb-primary mongodb-secondary mongodb-arbiter exhauster-monitoring-api && \
docker compose up -d exhauster-monitoring-ui