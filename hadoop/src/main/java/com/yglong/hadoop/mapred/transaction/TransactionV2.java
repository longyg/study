package com.yglong.hadoop.mapred.transaction;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

public class TransactionV2 {
    static class TransactionMapper extends Mapper<Object, Text, Text, DoubleWritable> {
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split("\\s+");
            context.write(new Text(fields[0]), new DoubleWritable(Double.parseDouble(fields[2])));
        }
    }

    static class TransactionReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
        @Override
        protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
            double max = Double.MIN_VALUE;
            for (DoubleWritable value : values) {
                if (value.get() > max) {
                    max = value.get();
                }
            }
            context.write(key, new DoubleWritable(max));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        String[] otherArgs = parser.getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: Transaction <in> <out> [jar_path]");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "Transaction");
        job.setJarByClass(Transaction.class);
        job.setMapperClass(TransactionMapper.class);
        job.setReducerClass(TransactionReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

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
