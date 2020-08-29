package com.yglong.hadoop.mapred.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 单词出现次数统计
 *
 * 可以在本地和集群执行
 */
public class WordCount {

    public static class TokenizerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            Text keyOut = null;
            IntWritable valueOut = new IntWritable(1);
            StringTokenizer tokenizer = new StringTokenizer(value.toString());
            while(tokenizer.hasMoreTokens()) {
                keyOut = new Text(tokenizer.nextToken());
                context.write(keyOut, valueOut);
            }
        }
    }

    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: wordcount <in> <out> [jar_path]");
            System.exit(2);
        }

        // 如果从windows本地提交MR程序到集群运行，必须设置mapreduce.app-submission.cross-platform为true，支持跨平台
//        conf.set("mapreduce.app-submission.cross-platform", "true");

        // 如果在windows本地执行MR程序，必须将mapreduce.framework.name设置为local
//        conf.set("mapreduce.framework.name", "local");
        // 有些版本也需要将fs.defaultFS设置为file:///，让MR程序查找本地路径，而不是HDFS路径
        // 经测试，hadoop-3.2.1必须设置
//        conf.set("fs.defaultFS", "file:///");

        /**
         * 注意：
         * conf中的参数都可以在运行程序时以参数形式提供，如：
         *   -Dmapreduce.app-submission.cross-platform=true
         *   -Dmapreduce.framework.name=local
         * 这样可以避免在代码中写死
         */

        Job job = Job.getInstance(conf, "word count");
        if (otherArgs.length == 3) {
            // 如果从windows本地提交MR程序到集群运行，必须设置jar包
            // 在本地运行，或者用hadoop jar命令运行时，不需要设置jar包
            job.setJar(otherArgs[2]);
        }
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        Path infile = new Path(otherArgs[0]);
        FileInputFormat.addInputPath(job, infile);

        Path outfile = new Path(otherArgs[1]);
        if (outfile.getFileSystem(conf).exists(outfile)) {
            outfile.getFileSystem(conf).delete(outfile, true);
        }
        FileOutputFormat.setOutputPath(job, outfile);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
