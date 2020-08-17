#!/bin/bash

#########################################
#
# Install and start hadoop 2 on single node as Pseudo-Distributed mode
#
# Prerequisites:
#	- JDK is installed
#	- JAVA_HOME env is set
#
#########################################

USER_NAME="yglong"
HADOOP_HOME="/opt/hadoop"
JAVA_HOME_DIR=$(echo $JAVA_HOME)
apt-get update && apt-get install ssh rsync

cd /opt
wget https://www.nic.funet.fi/pub/mirrors/apache.org/hadoop/common/hadoop-2.10.0/hadoop-2.10.0.tar.gz

tar -zxvf hadoop-2.10.0.tar.gz
rm hadoop-2.10.0.tar.gz
mv hadoop-* hadoop

cd $HADOOP_HOME

echo "export JAVA_HOME=$JAVA_HOME_DIR" >> etc/hadoop/hadoop-env.sh

# Pseudo-Distributed configuration
cat <<EOF >etc/hadoop/core-site.xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>
EOF

cat <<EOF >etc/hadoop/hdfs-site.xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
</configuration>
EOF

# Setup passphraseless ssh
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 0600 ~/.ssh/authorized_keys

# Format the filesystem
bin/hdfs namenode -format

# Start NameNode daemon and DataNode daemon
sbin/start-dfs.sh

# Make the HDFS directories required to execute MapReduce jobs
bin/hdfs dfs -mkdir /user
bin/hdfs dfs -mkdir /user/$USER_NAME

# YARN on a Single Node configuration
cat <<EOF >etc/hadoop/mapred-site.xml
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>
EOF

cat <<EOF >etc/hadoop/yarn-site.xml
<?xml version="1.0"?>
<configuration>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
</configuration>
EOF

# Start ResourceManager daemon and NodeManager daemon
sbin/start-yarn.sh

export PATH="$HADOOP_HOME/bin:$PATH"






