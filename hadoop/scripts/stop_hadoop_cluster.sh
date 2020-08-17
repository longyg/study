#!/bin/bash

# Note:
#   - This script must be executed on first node, e.g. bigdata01
#   - This script must be executed as root user

HADOOP_NODES=("bigdata01" "bigdata02" "bigdata03")
HADOOP_USER_NAME="bigdata"

# Stop map reduce history server
for node in ${HADOOP_NODES[@]}; do
	echo "Stopping mapreduce history server on $node..."
	ssh $node "su - $HADOOP_USER_NAME -c \"mapred --daemon stop historyserver\""
done

# Stop hadoop cluster
echo "Stopping hadoop cluster..."
su - $HADOOP_USER_NAME -c "stop-all.sh"

su - $HADOOP_USER_NAME -c "jps"