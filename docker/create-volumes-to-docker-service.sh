#!/bin/bash

BASE_PATH=/opt/volumes

sudo mkdir -p $BASE_PATH/keycloak/db
sudo mkdir -p $BASE_PATH/keycloak/pgadmin

sudo mkdir -p $BASE_PATH/pg12
sudo mkdir -p $BASE_PATH/pgadmin

sudo mkdir -p $BASE_PATH/mongodb
sudo mkdir -p $BASE_PATH/mongodb2

sudo mkdir -p  $BASE_PATH/els

sudo chmod 777 -R $BASE_PATH