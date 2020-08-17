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
 * 按手机号分类，不同前缀的结果放到不同文件
 * 如135的在一个文件，158的在另外一个文件
 */
public class MobileTrafficV2 {
    public static class TrafficMapper extends Mapper<Object, Text, Text, Traffic> {
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split("\\s+");
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

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();

        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        String[] otherArgs = parser.getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: MobileTrafficV2 <in> <out> [jar_path]");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "Mobile Traffic Count V2");
        job.setJarByClass(MobileTrafficV2.class);
        job.setMapperClass(TrafficMapper.class);
        // 设置自定义分区器
        job.setPartitionerClass(ProvincePartitioner.class);
        // 设置reduce的个数，与分区个数相同
        job.setNumReduceTasks(4);

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
