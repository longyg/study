package com.yglong.hbase.test;

import com.google.common.collect.Lists;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoprocessorTest {
    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure(); // for log4j

        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        TableName tableName = TableName.valueOf("test");
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
        ColumnFamilyDescriptor info = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("info")).build();
        ColumnFamilyDescriptor basic = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("basic")).build();
        tableDescriptorBuilder.setColumnFamilies(Lists.newArrayList(info, basic));
        // 设置自定义的协处理器
        tableDescriptorBuilder.setCoprocessor("com.yglong.hbase.coprocessor.TestCoprocessor");
        TableDescriptor tableDescriptor = tableDescriptorBuilder.build();
        admin.createTable(tableDescriptor);
        System.out.println("Table is created");

        System.out.println("Insert data to table...");
        Table table = connection.getTable(tableName);
        List<Put> puts = new ArrayList<>();
        Put put = new Put(Bytes.toBytes("10001"));
        put.addColumn(Bytes.toBytes("basic"), Bytes.toBytes("name"), Bytes.toBytes("zhangsan"));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("mobile"), Bytes.toBytes("1214232523"));
        puts.add(put);

        Put put1 = new Put(Bytes.toBytes("10002"));
        put1.addColumn(Bytes.toBytes("basic"), Bytes.toBytes("age"), Bytes.toBytes("30"));
        put1.addColumn(Bytes.toBytes("info"), Bytes.toBytes("email"), Bytes.toBytes("xxx@xxx.com"));
        puts.add(put1);
        table.put(puts);
        System.out.println("Data is inserted.");
    }
}
