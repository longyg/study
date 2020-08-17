#!/bin/bash

KAFKA_HOME="/opt/kafka"
cd /opt
wget http://mirror.netinch.com/pub/apache/kafka/2.5.0/kafka_2.12-2.5.0.tgz

tar -zxvf kafka_*.tgz
rm kafka_*.tgz
mv kafka_* kafka

cd $KAFKA_HOME
