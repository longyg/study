package com.yglong.hbase.coprocessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessor;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.coprocessor.RegionObserver;
import org.apache.hadoop.hbase.wal.WALEdit;

import java.io.IOException;

/**
 * 定义一个协处理器：在添加数据时同时插入test1表
 * 需要被打成jar包并部署到hbase中（放到hbase/lib下）
 */
public class TestCoprocessor implements RegionObserver, RegionCoprocessor {
    private static final Log logger = LogFactory.getLog(TestCoprocessor.class);

    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put,
                        WALEdit edit, Durability durability) throws IOException {
        // 将数据同步put到test1表中
        logger.info("Starting postPut...");
        Connection connection = ConnectionFactory.createConnection(HBaseConfiguration.create());
        logger.info("Got connection in postPut..." + connection);
        Table table = connection.getTable(TableName.valueOf("test1"));
        logger.info("Got table in postPut..." + table);
        table.put(put);
        logger.info("Save in postPut...");

        // 关闭表
        table.close();
        connection.close();
    }
}
