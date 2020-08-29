package com.yglong.hbase.mr.mysql;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class NameCountMapper extends TableMapper<Text, IntWritable> {
    Text outKey = new Text();
    IntWritable outValue = new IntWritable(1);

    @Override
    protected void map(ImmutableBytesWritable key, Result result, Context context) throws IOException, InterruptedException {
        for (Cell cell : result.rawCells()) {
            String name = Bytes.toString(CellUtil.cloneValue(cell));
            outKey.set(name);
            context.write(outKey, outValue);
        }
    }
}
