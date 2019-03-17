#!/bin/bash

echo "Building image '$1:$2'"
sudo docker build -t $1:$2 .
