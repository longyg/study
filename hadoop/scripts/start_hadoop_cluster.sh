#!/bin/bash

# Note:
#   - This script must be executed on first node, e.g. bigdata01
#   - This script must be executed as root user

HADOOP_NODES=("bigdata01" "bigdata02" "bigdata03")
HADOOP_USER_NAME="bigdata"

# Start hadoop cluster
echo "Starting hadoop cluster..."
su - $HADOOP_USER_NAME -c "start-all.sh"

# Start map reduce history server
for node in ${HADOOP_NODES[@]}; do
	echo "Starting mapreduce history server on $node..."
	ssh $node "su - $HADOOP_USER_NAME -c \"mapred --daemon start historyserver\""
	ssh $node "jps"
done