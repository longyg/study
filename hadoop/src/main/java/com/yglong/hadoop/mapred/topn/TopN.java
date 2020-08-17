package com.yglong.hadoop.mapred.topn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * 求每个月温度最高的两天
 */
public class TopN {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: TopN <in> <out> [jar_path]");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "TopN");
        job.setJarByClass(TopN.class);
        job.setMapperClass(TMapper.class);
        job.setPartitionerClass(TPartitioner.class);
        job.setSortComparatorClass(TSortComparator.class);
        job.setGroupingComparatorClass(TGroupingComparator.class);
        job.setReducerClass(TReducer.class);
        job.setMapOutputKeyClass(MyKey.class);
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
}
