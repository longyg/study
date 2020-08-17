package com.yglong.hadoop.mapred.duplicate;

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
 * 去除多个文件之间的重复行，并合并到一个输出文件
 */
public class RemoveDuplicate {
    static class MyMapper extends Mapper<Object, Text, Text, Text> {
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            context.write(new Text(value.toString()), new Text(""));
        }
    }

    static class MyReducer extends Reducer<Text, Text, Text, Text> {
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            context.write(key, new Text(""));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        String[] otherArgs = parser.getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: RemoveDuplicate <in> <out> [jar_path]");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "RemoveDuplicate");
        job.setJarByClass(RemoveDuplicate.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

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
