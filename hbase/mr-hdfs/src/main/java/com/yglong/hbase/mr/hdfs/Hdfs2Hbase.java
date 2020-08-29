package com.yglong.hbase.mr.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * 将HDFS里的文件数据导入到HBase表中
 */
public class Hdfs2Hbase implements Tool {

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Hdfs2Hbase(), args);
    }

    @Override
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance();

        FileInputFormat.addInputPath(job, new Path(strings[0]));

        job.setJarByClass(Hdfs2Hbase.class);
        job.setMapperClass(HdfsMapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);

        TableMapReduceUtil.initTableReducerJob(
                "student",
                null,
                job
        );
        job.setNumReduceTasks(0);

        return job.waitForCompletion(true) ? JobStatus.SUCCEEDED : JobStatus.FAILED;
    }

    @Override
    public void setConf(Configuration configuration) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}
