package com.yglong.hadoop.mapred.merge;

import com.yglong.hadoop.mapred.topk.TopK;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * Not implemented
 */
@Deprecated
public class MergeSmallFiles {
    static class MyMapper extends Mapper<NullWritable, BytesWritable, NullWritable, BytesWritable> {
        private Text filenameKey;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            InputSplit split = context.getInputSplit();
            Path path = ((FileSplit) split).getPath();
            filenameKey = new Text(path.toString());
        }

        @Override
        protected void map(NullWritable key, BytesWritable value, Context context) throws IOException, InterruptedException {
            context.write(NullWritable.get(), value);
        }
    }

    static class MyReducer extends Reducer<NullWritable, BytesWritable, NullWritable, Text> {
        @Override
        protected void reduce(NullWritable key, Iterable<BytesWritable> values, Context context) throws IOException, InterruptedException {
            for (BytesWritable val : values) {
                context.write(key, new Text(val.toString()));
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
        job.setJarByClass(MergeSmallFiles.class);
        job.setInputFormatClass(MyInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(BytesWritable.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

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
