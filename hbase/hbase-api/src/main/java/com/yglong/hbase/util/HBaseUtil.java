package com.yglong.hbase.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.shaded.com.google.common.collect.Lists;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;

public class HBaseUtil {
    private static final ThreadLocal<Connection> connHolder = new ThreadLocal<>();

    public static void createConnection() throws IOException {
        Connection conn = connHolder.get();
        if (null == conn) {
            Configuration conf = HBaseConfiguration.create();
            conn = ConnectionFactory.createConnection(conf);
            connHolder.set(conn);
        }
    }

    /**
     * Check if table exists
     * @param tableName
     * @return
     * @throws IOException
     */
    public static boolean tableExists(String tableName) throws IOException {
        Connection conn = connHolder.get();
        Admin admin = conn.getAdmin();
        boolean exists = admin.tableExists(TableName.valueOf(tableName));
        admin.close();
        return exists;
    }

    public static void createTable(String tableNameStr, String... families) throws IOException {
        Connection connection = connHolder.get();
        Admin admin = connection.getAdmin();
        TableName tableName = TableName.valueOf(tableNameStr);
        if (admin.tableExists(tableName)) {
            admin.close();
            throw new RuntimeException("Table already exists: " + tableNameStr);
        }
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
        ArrayList<ColumnFamilyDescriptor> columnFamilyDescriptors = Lists.newArrayList();
        for (String family : families) {
            ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(family)).build();
            columnFamilyDescriptors.add(columnFamilyDescriptor);
        }
        tableDescriptorBuilder.setColumnFamilies(columnFamilyDescriptors);
        TableDescriptor tableDescriptor = tableDescriptorBuilder.build();
        admin.createTable(tableDescriptor);
        admin.close();
    }

    public static void deleteTable(String tableName) throws Exception {
        Connection connection = connHolder.get();
        Admin admin = connection.getAdmin();
        TableName table = TableName.valueOf(tableName);
        if (!admin.tableExists(table)) {
            admin.close();
            return;
        }
        if (!admin.isTableDisabled(table)) {
            admin.disableTable(table);
        }
        if (!admin.isTableDisabled(table)) {
            admin.close();
            throw new RuntimeException("Unable to disable table: " + tableName);
        }
        admin.deleteTable(table);
        admin.close();
    }

    public static void insertData(String tableNameStr, String rowKey, String family, String qualifier, String value) throws IOException {
        Connection connection = connHolder.get();
        Admin admin = connection.getAdmin();
        TableName tableName = TableName.valueOf(tableNameStr);
        if (!admin.tableExists(tableName)) {
            admin.close();
            throw new RuntimeException("Table does not exists: " + tableNameStr);
        }
        Table table = connection.getTable(tableName);
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
        table.put(put);
        table.close();
        admin.close();
    }

    public static void deleteData(String tableNameStr, String rowKey) throws IOException {
        Connection connection = connHolder.get();
        TableName tableName = TableName.valueOf(tableNameStr);
        Admin admin = connection.getAdmin();
        if (!admin.tableExists(tableName)) {
            admin.close();
            throw new RuntimeException("Table does not exists: " + tableNameStr);
        }
        Table table = connection.getTable(tableName);
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);

        table.close();
        admin.close();
    }

    public static void truncate(String tableName) throws Exception {
        Connection connection = connHolder.get();
        Admin admin = connection.getAdmin();
        TableName table = TableName.valueOf(tableName);
        if (!admin.tableExists(table)) {
            admin.close();
            return;
        }
        if (!admin.isTableDisabled(table)) {
            admin.disableTable(table);
        }
        if (!admin.isTableDisabled(table)) {
            admin.close();
            throw new RuntimeException("Unable to disable table: " + tableName);
        }
        admin.truncateTable(TableName.valueOf(tableName), true);
        admin.enableTable(table);
        admin.close();
    }

    public static ResultScanner getAllData(String tableNameStr) throws Exception {
        Connection connection = connHolder.get();
        TableName tableName = TableName.valueOf(tableNameStr);
        Admin admin = connection.getAdmin();
        if (!admin.tableExists(tableName)) {
            admin.close();
            throw new RuntimeException("Table does not exists: " + tableNameStr);
        }
        Table table = connection.getTable(tableName);
        ResultScanner scanner = table.getScanner(new Scan());

        table.close();
        admin.close();

        return scanner;
    }

    public static void close() throws IOException {
        Connection connection = connHolder.get();
        if (null != connection) {
            connection.close();
            connHolder.remove();
        }
    }
}
