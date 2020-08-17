package com.yglong.hadoop.mapred.merge;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 合并小文件
 * 对于小文件（不会被拆分成多个split），可以保证合并后行的顺序与合并前一致。
 * 但是对于大文件（被拆分成多个split），则不能保证，因为lineNumber会为每个split重新编号。
 */
public class MergeSmallFiles2 {

    static class MyMapper extends Mapper<LongWritable, Text, TKey, Text> {
        TKey outKey = new TKey();
        Text outValue = new Text();

        int lineNumber = 0;

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            Path path = inputSplit.getPath();
            outKey.setFilename(new Text(path.getName()));
            outKey.setLineNumber(new IntWritable(lineNumber));
            outValue.set(value.toString());
            context.write(outKey, outValue);
            lineNumber++;
        }
    }

    static class MyReducer extends Reducer<TKey, Text, Text, NullWritable> {
        Text outKey = new Text();
        NullWritable outValue = NullWritable.get();
        @Override
        protected void reduce(TKey key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            StringBuilder sb = new StringBuilder();
            for (Text val : values) {
                sb.append(val.toString()).append("\n");
            }
            sb.deleteCharAt(sb.length() - 1);
            outKey.set(sb.toString());
            context.write(outKey, outValue);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: MergeSmallFiles2 <in> <out> [jar_path]");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "MergeSmallFiles2");
        job.setJarByClass(MergeSmallFiles2.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setMapOutputKeyClass(TKey.class);
        job.setMapOutputValueClass(Text.class);
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
        private Text filename;
        private IntWritable lineNumber;

        public Text getFilename() {
            return filename;
        }

        public void setFilename(Text filename) {
            this.filename = filename;
        }

        public IntWritable getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(IntWritable lineNumber) {
            this.lineNumber = lineNumber;
        }

        @Override
        public int compareTo(TKey o) {
            System.out.println("Key self compartor invoked");
            int c1 = this.filename.compareTo(o.filename);
            if (c1 == 0) {
                return this.lineNumber.compareTo(o.lineNumber);
            }
            return c1;
        }

        @Override
        public void write(DataOutput out) throws IOException {
            out.writeUTF(this.filename.toString());
            out.writeInt(this.lineNumber.get());
        }

        @Override
        public void readFields(DataInput in) throws IOException {
            this.filename = new Text(in.readUTF());
            this.lineNumber = new IntWritable(in.readInt());
        }
    }
}
