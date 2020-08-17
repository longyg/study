#!/bin/bash

# This script must be executed as root user
# It can be executed on any one node of the cluster

ZK_NODES=("bigdata01" "bigdata02" "bigdata03")
ZK_USER_NAME="bigdata"

# Start zookeeper
for node in ${ZK_NODES[@]}; do
	echo "Starting zookeeper on $node..."
	ssh $node "su - $ZK_USER_NAME -c \"zkServer.sh start\""
done