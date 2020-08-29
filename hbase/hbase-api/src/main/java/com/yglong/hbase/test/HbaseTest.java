package com.yglong.hbase.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hbase.thirdparty.com.google.common.collect.Lists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HbaseTest {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        System.out.println("Connection is established.");
        Admin admin = connection.getAdmin();
        System.out.println("Admin is get");
        TableName tableName = TableName.valueOf("student");
        System.out.println("checking if table 'student' exist");
        boolean b = admin.tableExists(tableName);
        if (!b) {
            System.out.println("Table student does not exist, create it...");
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
            ColumnFamilyDescriptor info = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("info")).build();
            ColumnFamilyDescriptor basic = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("basic")).build();
            tableDescriptorBuilder.setColumnFamilies(Lists.newArrayList(info, basic));
            TableDescriptor tableDescriptor = tableDescriptorBuilder.build();
            admin.createTable(tableDescriptor);
            System.out.println("Table student is created");
        } else {
            // 插入数据
            System.out.println("Insert data to table student...");
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

            System.out.println("Scan table student...");
            Scan scan = new Scan();
            ResultScanner scanner = table.getScanner(scan);
            for (Result result : scanner) {
                byte[] row = result.getRow();
                System.out.println("----------row key: " + Bytes.toString(row) + "------------------");
                Cell[] cells = result.rawCells();
                for (Cell cell : cells) {
                    System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)) +
                            ":" + Bytes.toString(CellUtil.cloneQualifier(cell)) +
                            "-->" + Bytes.toString(CellUtil.cloneValue(cell)));
                }
            }
            System.out.println("Scan finished.");
        }
    }
}
