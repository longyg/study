package com.yglong.hbase.mr.migration;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * 将一个HBase表中的数据导入到另外一个表中
 */
public class DataMigration implements Tool  {
    private Configuration conf;
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new DataMigration(), args);
        System.out.println(exitCode);
    }

    @Override
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(conf, "DataMigration");
        job.setJarByClass(DataMigration.class);

        TableMapReduceUtil.initTableMapperJob(
                "student",
                new Scan(),
                MyMapper.class,
                ImmutableBytesWritable.class,
                Put.class,
                job
        );

        TableMapReduceUtil.initTableReducerJob(
                "user",
                null,
                job
        );
        job.setNumReduceTasks(0);

        boolean b = job.waitForCompletion(true);
        return b ? JobStatus.SUCCEEDED : JobStatus.FAILED;
    }

    @Override
    public void setConf(Configuration configuration) {
        this.conf = configuration;
    }

    @Override
    public Configuration getConf() {
        if (null == conf) {
            conf = HBaseConfiguration.create();
//            conf.set("mapreduce.framework.name", "local");
//            conf.set("fs.defaultFS", "file:///");
        }
        return conf;
    }
}
