package com.yglong.hadoop.mapred.fof;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    Text outKey = new Text();
    IntWritable outValue = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //value: A:B,C,D,F,E,O
        String[] strs = value.toString().split(":");
        String person = strs[0];
        String[] friends = strs[1].split(",");

        // A的直接好友
        for (int i = 0; i < friends.length; i++) {
            outKey.set(getFof(person, friends[i]));
            outValue.set(0);
            context.write(outKey, outValue);
        }
        // A的直接好友之间都是间接好友，他们都有共同好友，就是A
        for (int i = 0; i < friends.length - 1; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                outKey.set(getFof(friends[i], friends[j]));
                outValue.set(1);
                context.write(outKey, outValue);
            }
        }
    }

    private String getFof(String p1, String p2) {
        int c = p1.compareTo(p2);
        if (c < 0) {
            return p1 + "-" + p2;
        } else {
            return p2 + "-" + p1;
        }
    }
}
