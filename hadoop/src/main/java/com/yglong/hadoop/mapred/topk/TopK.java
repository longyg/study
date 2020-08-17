package com.yglong.hadoop.mapred.topk;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 利用TreeMap自动排序的功能，在内存中保存每个map task中筛选出的前K个
 * 在Reduce中也使用一个TreeMap保存各分组中的前K个
 */
public class TopK {
    private static final int K = 3;

    private static TreeMap<Long, String> getDescSortTreeMap() {
        return new TreeMap<>(Comparator.reverseOrder());
    }

    static class MyMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
        private TreeMap<Long, String> kvMap = getDescSortTreeMap();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split("\\s+");
            if (fields.length < 2) return;
            kvMap.put(Long.parseLong(fields[1]), fields[0]);
            if (kvMap.size() > K) {
                kvMap.remove(kvMap.lastKey());
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            for (Long num : kvMap.keySet()) {
                context.write(new LongWritable(num), new Text(kvMap.get(num)));
            }
        }
    }

    static class MyReducer extends Reducer<LongWritable, Text, Text, LongWritable> {
        private TreeMap<Long, String> kvMap = getDescSortTreeMap();

        @Override
        protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            StringBuilder sb = new StringBuilder();
            for (Text val : values) {
                sb.append(val.toString());
            }
            kvMap.put(key.get(), sb.toString());
            if (kvMap.size() > K) {
                kvMap.remove(kvMap.lastKey());
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            for (Long num : kvMap.keySet()) {
                context.write(new Text(kvMap.get(num)), new LongWritable(num));
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        String[] otherArgs = parser.getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: TopK <in> <out> [jar_path]");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "TopK");
        job.setJarByClass(TopK.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

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
