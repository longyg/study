#!/bin/bash
#########################################
#
# Install and start hbase 2 based on HDFS
#
#########################################
HBASE_HOME="/opt/hbase"
JAVA_HOME_DIR=$(echo $JAVA_HOME)

cd /opt
wget https://www.nic.funet.fi/pub/mirrors/apache.org/hbase/stable/hbase-2.2.4-bin.tar.gz

tar -zxvf hbase-2.2.4-bin.tar.gz
rm hbase-2.2.4-bin.tar.gz
mv hbase-* hbase

cd $HBASE_HOME

echo "export JAVA_HOME=$JAVA_HOME_DIR" >> conf/hbase-env.sh

# Configure HDFS used by Hbase
cat <<EOF >conf/hbase-site.xml
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
	<property>
		<name>hbase.cluster.distributed</name>
		<value>true</value>
	</property>
	<property>
		<name>hbase.rootdir</name>
		<value>hdfs://localhost:9000/hbase</value>
	</property>
</configuration>
EOF

# Start Hbase
bin/start-hbase.sh

# Verify installation
hadoop fs -ls /hbase

export PATH="$HBASE_HOME/bin:$PATH"

