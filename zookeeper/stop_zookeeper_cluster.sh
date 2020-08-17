#!/bin/bash

# This script must be executed as root user
# It can be executed on any one node of the cluster

ZK_NODES=("bigdata01" "bigdata02" "bigdata03")
ZK_USER_NAME="bigdata"

# stop zookeeper
for node in ${ZK_NODES[@]}; do
	echo "Stopping zookeeper on $node..."
	ssh $node "su - $ZK_USER_NAME -c \"zkServer.sh stop\""
done