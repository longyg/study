package com.yglong.hadoop.mapred.topn;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TReducer extends Reducer<MyKey, NullWritable, Text, NullWritable> {
    Text outKey = new Text();
    NullWritable outValue = NullWritable.get();

    @Override
    protected void reduce(MyKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        int index = 0;
        int flag = 0;
        for (NullWritable value : values) {
            // 输出第一条
            if (index == 0) {
                outKey.set(key.getYear() + "-" + key.getMonth() + "-" + key.getDay() + "\t" + key.getTemperature());
                context.write(outKey, outValue);
                flag = key.getDay();
                index++;
            }
            // 输出第二条，与第一条不同日期的记录
            if (flag != key.getDay()) {
                outKey.set(key.getYear() + "-" + key.getMonth() + "-" + key.getDay() + "\t" + key.getTemperature());
                context.write(outKey, outValue);
                break;
            }
        }
    }
}
