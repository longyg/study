package com.yglong.hbase.test;

import com.yglong.hbase.util.HBaseUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseTest2 {
    public static void main(String[] args) throws Exception {
        HBaseUtil.createConnection();

        try {
            HBaseUtil.createTable("user", "info", "basic");
        } catch (Exception e) {
            e.printStackTrace();
        }
        HBaseUtil.insertData("user", "10001", "basic", "name", "zhangsan");
        HBaseUtil.insertData("user", "10001", "basic", "password", "xxx");
        HBaseUtil.insertData("user", "10001", "info", "email", "xxx@xxx.org");
        HBaseUtil.insertData("user", "10002", "info", "mobile", "122222222");
        HBaseUtil.insertData("user", "10002", "basic", "name", "lisi");

        ResultScanner results = HBaseUtil.getAllData("user");
        for (Result result : results) {
            String rowKey = Bytes.toString(result.getRow());
            for (Cell cell : result.rawCells()) {
                String family = Bytes.toString(CellUtil.cloneFamily(cell));
                String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                System.out.println(rowKey + "\t\t" + family + ":" + qualifier + "-->" + value);
            }
        }

        //关闭连接
        HBaseUtil.close();
    }
}
