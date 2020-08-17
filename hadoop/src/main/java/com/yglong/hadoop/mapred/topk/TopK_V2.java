package com.yglong.hadoop.mapred.topk;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;

/**
 * 自定义Key的方式
 */
public class TopK_V2 {

    private static final int K = 3;

    static class MyMapper extends Mapper<LongWritable, Text, TKey, NullWritable> {
        TKey tKey = new TKey();
        NullWritable tValue = NullWritable.get();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split("\\s+");
            tKey.setLabel(fields[0]);
            tKey.setValue(Long.parseLong(fields[1]));
            context.write(tKey, tValue);
        }
    }

    static class MyReducer extends Reducer<TKey, NullWritable, Text, NullWritable> {
        Text outKey = new Text();
        NullWritable outValue = NullWritable.get();
        @Override
        protected void reduce(TKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            int flag = 0;
            Iterator<NullWritable> iterator = values.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                if (flag < K) {
                    outKey.set(key.getLabel() + "\t" + key.getValue());
                    context.write(outKey, outValue);
                    flag++;
                } else {
                    break;
                }
            }
        }
    }

    static class MyPartitioner extends Partitioner<TKey, NullWritable> {

        @Override
        public int getPartition(TKey tKey, NullWritable nullWritable, int numPartitions) {
            return 0;
        }
    }

    static class MyGroupingComparator extends WritableComparator {
        public MyGroupingComparator() {
            // 非常重要，否则会报错
            // createInstances必须设为true
            super(TKey.class, true);
        }

        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: TopK_V2 <in> <out> [jar_path]");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "TopK_V2");
        job.setJarByClass(TopK_V2.class);
        job.setMapperClass(MyMapper.class);
        job.setPartitionerClass(MyPartitioner.class);
        job.setGroupingComparatorClass(MyGroupingComparator.class);
        job.setReducerClass(MyReducer.class);
        job.setMapOutputKeyClass(TKey.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

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

    static class TKey implements WritableComparable<TKey> {
        private String label;
        private long value;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        @Override
        public int compareTo(TKey o) {
            return Long.compare(o.value, this.value);
        }

        @Override
        public void write(DataOutput out) throws IOException {
            out.writeUTF(label);
            out.writeLong(value);
        }

        @Override
        public void readFields(DataInput in) throws IOException {
            this.label = in.readUTF();
            this.value = in.readLong();
        }
    }
}
