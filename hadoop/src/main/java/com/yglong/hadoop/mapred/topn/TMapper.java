package com.yglong.hadoop.mapred.topn;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TMapper extends Mapper<LongWritable, Text, MyKey, NullWritable> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");

    MyKey outKey = new MyKey();
    NullWritable outValue = NullWritable.get();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // value: 2019-06-04 08:10:55 32
        String[] fields = value.toString().split("\\s+");
        try {
            Date date = dateFormat.parse(fields[0]);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            outKey.setYear(calendar.get(Calendar.YEAR));
            outKey.setMonth(calendar.get(Calendar.MONTH) + 1);
            outKey.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            outKey.setTemperature(Integer.parseInt(fields[1]));
            context.write(outKey, outValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
