package com.yglong.hadoop.mapred.mobiletraffic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * 手机流量统计
 * 输入文件格式：
 *  - 每行为一条手机流量记录，包括三个字段：
 *    手机号        上行流量      下行流量
 *    15828536543   400          1300
 *    15828536543   100          300
 *
 * 输出文件格式：
 *  - 要求统计的是每个手机用户的流量总量，结果格式为：
 *    手机号        上行流量总量      下行流量总量     上下行流量总量
 *    15828536543   500              1600           2100
 */
public class MobileTraffic {

    public static class TrafficMapper extends Mapper<Object, Text, Text, Traffic> {
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split("\\s+");
            System.out.println(fields[0] + "," + fields[1]);
            Text mobileNumber = new Text(fields[0]);
            long up = Long.parseLong(fields[1]);
            long down = Long.parseLong(fields[2]);
            Traffic traffic = new Traffic(up, down);
            context.write(mobileNumber, traffic);
        }
    }

    public static class TrafficReducer extends Reducer<Text, Traffic, Text, Traffic> {
        @Override
        protected void reduce(Text key, Iterable<Traffic> values, Context context) throws IOException, InterruptedException {
            long up = 0;
            long down = 0;
            for (Traffic value : values) {
                up += value.getUp();
                down += value.getDown();
            }
            context.write(key, new Traffic(up, down));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        String[] otherArgs = parser.getRemainingArgs();

        if (otherArgs.length < 2) {
            System.err.println("Usage: MobileTraffic <in> <out> [jar_path]");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "Mobile Traffic Count");
        job.setJarByClass(MobileTraffic.class);
        job.setMapperClass(TrafficMapper.class);
        job.setCombinerClass(TrafficReducer.class);
        job.setReducerClass(TrafficReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Traffic.class);

        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));

        Path out = new Path(otherArgs[1]);
        if (out.getFileSystem(conf).exists(out)) {
            out.getFileSystem(conf).delete(out, true);
        }

        if (otherArgs.length == 3) {
            job.setJar(otherArgs[2]);
        }
        FileOutputFormat.setOutputPath(job, out);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}


