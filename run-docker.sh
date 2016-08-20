#!/bin/bash
NAME=$1
PORT=$2
RELEASE=$3
docker ps | grep $NAME | awk '{print $1}' | xargs --no-run-if-empty docker stop | xargs --no-run-if-empty docker rm
docker run --restart=always --name=$NAME -d \
-p 127.0.0.1:$PORT:8080 \
-e DATABASE_URL=mysql://$NAME:$NAME@172.17.0.1:3306/$NAME \
-e JAVA_OPTS=-Xmx256m \
bespalhuk/faw:$RELEASE \
/usr/local/tomcat/bin/catalina.sh run