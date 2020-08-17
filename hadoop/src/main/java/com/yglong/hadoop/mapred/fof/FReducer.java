package com.yglong.hadoop.mapred.fof;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    IntWritable outValue = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        // A-B  1
        // A-B  0
        // A-B  1
        int sum = 0;
        for (IntWritable value : values) {
            if (value.get() == 0) {
                    sum = 0;
                    break;
            } else {
                sum += value.get();
            }
        }
        if (sum > 0) {
            outValue.set(sum);
            context.write(key, outValue);
        }
    }
}
