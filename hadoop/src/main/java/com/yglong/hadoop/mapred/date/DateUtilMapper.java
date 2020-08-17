package com.yglong.hadoop.mapred.date;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class DateUtilMapper extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(",");
        String content = "";
        for (int i = 0; i < words.length; i++) {
            if (i == 3) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(words[i].trim());
                content = content + sdf.format(Long.parseLong(words[i].trim() + "000")) + "\t";
            } else if (i == words.length - 1) {
                content = content + words[i].trim() + "";
            } else {
                content = content + words[i].trim() + "\t";
            }
        }

        context.write(new Text(content), new Text(""));
    }
}
