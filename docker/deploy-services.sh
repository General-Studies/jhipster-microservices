#!/bin/bash

cd keycloak && docker-compose -f keycloak.yml up -d && cd ..
cd mongodb && docker-compose -f mongodb.yml up -d && cd ..
cd postgres && docker-compose -f postgres.yml up -d