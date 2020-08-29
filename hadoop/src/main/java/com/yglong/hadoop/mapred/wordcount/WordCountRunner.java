package com.yglong.hadoop.mapred.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountRunner implements Tool {
    private Configuration conf;

    public static void main(String[] args) throws Exception {
        int result = ToolRunner.run(new WordCountRunner(), args);
        System.out.println(result);
    }

    @Override
    public int run(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: wordcount <in> <out> [jar_path]");
            System.exit(2);
        }
        Job job = Job.getInstance(conf, "WordCount");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(WordCount.TokenizerMapper.class);
        job.setCombinerClass(WordCount.IntSumReducer.class);
        job.setReducerClass(WordCount.IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        Path infile = new Path(args[0]);
        FileInputFormat.addInputPath(job, infile);

        Path outfile = new Path(args[1]);
        if (outfile.getFileSystem(conf).exists(outfile)) {
            outfile.getFileSystem(conf).delete(outfile, true);
        }
        FileOutputFormat.setOutputPath(job, outfile);
        boolean b = job.waitForCompletion(true);
        return b ? JobStatus.SUCCEEDED : JobStatus.FAILED;
    }

    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    @Override
    public Configuration getConf() {
        if (null == conf) {
            conf = new Configuration();
//            conf.set("mapreduce.framework.name", "local");
//            conf.set("fs.defaultFS", "file:///");
        }
        return conf;
    }
}
