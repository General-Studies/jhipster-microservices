#!/bin/bash

cd keycloak && docker-compose -f keycloak.yml down && cd ..
cd mongodb && docker-compose -f mongodb.yml down && cd ..
cd postgres && docker-compose -f postgres.yml down