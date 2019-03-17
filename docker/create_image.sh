#!/bin/bash

echo "Building image '$1:$2'"
sudo mvn package spring-boot:repackage
sudo docker build -t $1:$2 .