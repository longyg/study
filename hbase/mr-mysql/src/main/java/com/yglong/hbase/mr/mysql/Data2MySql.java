package com.yglong.hbase.mr.mysql;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * 统计HBase表中的数据，然后将统计结果保存到MySQL表中
 * 统计表里的每个人出现的总次数
 */
public class Data2MySql implements Tool {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Data2MySql(), args);
    }

    @Override
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance();
        job.setJarByClass(Data2MySql.class);

        TableMapReduceUtil.initTableMapperJob(
                "student",
                new Scan(),
                NameCountMapper.class,
                Text.class,
                IntWritable.class,
                job
        );

        job.setReducerClass(NameCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setOutputFormatClass(MysqlOutputFormat.class);

        return job.waitForCompletion(true)? JobStatus.SUCCEEDED: JobStatus.FAILED;
    }

    @Override
    public void setConf(Configuration configuration) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}
