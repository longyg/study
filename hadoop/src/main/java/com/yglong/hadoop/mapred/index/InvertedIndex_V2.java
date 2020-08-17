package com.yglong.hadoop.mapred.index;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.*;

/**
 * 倒排索引
 * 支持大文件
 */
public class InvertedIndex_V2 {
    static class MyMapper extends Mapper<LongWritable, Text, Text, Text> {
        private final Text outKey = new Text();
        private final Text one = new Text("1");
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // Hello mapreduce
            // Hello world
            StringTokenizer tokenizer = new StringTokenizer(value.toString());
            FileSplit split = (FileSplit) context.getInputSplit();
            while (tokenizer.hasMoreElements()) {
                // Hello:1.txt -> 1
                // mapreduce:1.txt -> 1
                // Hello:1.txt -> 1
                // word:1.txt -> 1
                outKey.set(tokenizer.nextToken() + ":" + split.getPath().getName());
                context.write(outKey, one);
            }
        }
    }

    /**
     * 对一个split的map输出进行合并
     */
    static class MyCombiner extends Reducer<Text, Text, Text, Text> {
        private final Text outVal = new Text();
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            // Hello:1.txt -> 1
            // mapreduce:1.txt -> 1
            // Hello:1.txt -> 1
            // word:1.txt -> 1
            int sum = 0;
            for (Text val : values) {
                sum += Integer.parseInt(val.toString());
            }
            outVal.set("" + sum);
            // Hello:1.txt -> 2
            // mapreduce:1.txt -> 1
            // word:1.txt -> 1
            context.write(key, outVal);
        }
    }

    /**
     * 相同的单词（不管是来自一个文件或不同文件）作为一组
     */
    static class MyGroupingComparator extends WritableComparator {
        public MyGroupingComparator() {
            super(Text.class, true);
        }

        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            Text keyA = (Text) a;
            Text keyB = (Text) b;
            String wa = keyA.toString().split(":")[0];
            String wb = keyB.toString().split(":")[0];
            return wa.compareTo(wb);
        }
    }

    /**
     *
     */
    static class MyReducer extends Reducer<Text, Text, Text, Text> {
        private final Text outKey = new Text();
        private final Text outValue = new Text();
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            // 来自一个map：
            // Hello:1.txt -> 2

            // 来自第二个map（第一个文件的另外一个split）：
            // Hello:1.txt -> 3

            // 来自第三个map：
            // Hello:2.txt -> 4
            
            outKey.set(key.toString().split(":")[0]);
            
            Map<String, Integer> fileMap = new HashMap<>();

            for (Text value : values) {
                String filename = key.toString().split(":")[1];
                if (fileMap.containsKey(filename)) {
                    Integer number = fileMap.get(filename);
                    number += Integer.parseInt(values.toString());
                    fileMap.put(filename, number);
                } else {
                    fileMap.put(filename, Integer.parseInt(value.toString()));
                }
            }

            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Integer> entry : fileMap.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
            }
            String value = sb.substring(0, sb.length() - 2);
            outValue.set(value);
            context.write(outKey, outValue);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        String[] otherArgs = parser.getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: InvertedIndex_V2 <in> <out> [jar_path]");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "InvertedIndex_V2");
        job.setJarByClass(InvertedIndex_V2.class);
        job.setMapperClass(MyMapper.class);
        job.setGroupingComparatorClass(MyGroupingComparator.class);
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

