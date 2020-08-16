#!/bin/zsh
sbt clean
sbt run -Dhttp.port=9001 -Dconfig.file=./conf/application.conf -Dlogger.file=./conf/logback.xml
