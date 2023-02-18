#!/usr/bin/env bash

git fetch && git checkout main && git pull && \

docker compose rm -f -s exhauster-monitoring-api exhauster-monitoring-ui grafana && \

docker rmi -f evraz-hackaton-2023-exhauster-monitoring-ui:latest && \
docker rmi -f exhauster-monitoring:1.0.0 && \

cd backend

./gradlew bootBuildImage && \

docker compose up -d mongodb-primary  && \
docker compose up -d mongodb-secondary && \
docker compose up -d mongodb-arbiter && \
docker compose up -d influxdb && \
docker compose up -d postgres_db && \
docker compose build grafana --no-cache && docker compose up -d grafana && \
docker compose up -d exhauster-monitoring-api && \
docker compose up -d exhauster-monitoring-ui