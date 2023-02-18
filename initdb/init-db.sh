#!/bin/bash

psql -v ON_ERROR_STOP=1 --username pgadmin --dbname pgadmin <<-EOSQL
  CREATE DATABASE monitoring WITH OWNER pgadmin;
EOSQL
