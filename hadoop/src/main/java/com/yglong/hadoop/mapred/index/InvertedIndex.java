package com.yglong.hadoop.mapred.index;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 倒排索引
 * 注意：该实现对于大文件（会被拆分为多个split的文件）是有问题的。
 */
public class InvertedIndex {

    static class MyMapper extends Mapper<LongWritable, Text, Text, Text> {
        private Text outKey = new Text();
        private Text one = new Text("1");
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // value: Hello mapreduce
            StringTokenizer tokenizer = new StringTokenizer(value.toString());
            FileSplit split = (FileSplit) context.getInputSplit();
            while (tokenizer.hasMoreElements()) {
                // Hello:C:\ylong\workspace\study\hadoop\example_data\index\input\1.txt
                // mapreduce:C:\ylong\workspace\study\hadoop\example_data\index\input\1.txt
                outKey.set(tokenizer.nextToken() + ":" + split.getPath());
                context.write(outKey, one);
            }
        }
    }

    /**
     * 对一个split的map输出进行合并
     */
    static class MyCombiner extends Reducer<Text, Text, Text, Text> {
        private Text outKey = new Text();
        private Text outVal = new Text();
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            // Hello:C:\ylong\workspace\study\hadoop\example_data\index\input\1.txt
            // mapreduce:C:\ylong\workspace\study\hadoop\example_data\index\input\1.txt
            String[] keys = key.toString().split(":");

            String word = keys[0];
            String filepath = keys[keys.length - 1];
            String filename = filepath.substring(filepath.lastIndexOf("/") + 1);
            int sum = 0;
            for (Text val : values) {
                sum += Integer.parseInt(val.toString());
            }
            outKey.set(word);
            outVal.set(filename + ": " + sum);
            // Hello -> 1.txt: 1
            // mapreduce -> 1.txt: 1
            context.write(outKey, outVal);
        }
    }

    /**
     *
     */
    static class MyReducer extends Reducer<Text, Text, Text, Text> {
        private Text outValue = new Text();
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            StringBuilder sb = new StringBuilder();
            for (Text val : values) {
                sb.append(val.toString()).append(", ");
            }
            sb.deleteCharAt(sb.length() - 2);
            outValue.set(sb.toString());
            context.write(key, outValue);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        String[] otherArgs = parser.getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: InvertedIndex <in> <out> [jar_path]");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "InvertedIndex");
        job.setJarByClass(InvertedIndex.class);
        job.setMapperClass(MyMapper.class);
        job.setCombinerClass(MyCombiner.class);
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
