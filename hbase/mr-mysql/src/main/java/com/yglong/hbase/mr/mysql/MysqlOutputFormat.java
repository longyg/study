package com.yglong.hbase.mr.mysql;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlOutputFormat extends OutputFormat<Text, IntWritable> {

    static class MySqlRecordWriter extends RecordWriter<Text, IntWritable> {
        private static final String MYSQL_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
        private static final String MYSQL_URL = "jdbc:mysql://bigdata01:3306/bigdata?useUnicode=true&characterEncoding=UTF-8";
        private static final String MYSQL_USERNAME = "root";
        private static final String MYSQL_PASSWORD = "root";
        private Connection connection;

        public MySqlRecordWriter() {
            try {
                Class.forName(MYSQL_DRIVER_CLASS);
                connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void write(Text text, IntWritable intWritable) throws IOException, InterruptedException {
            String sql = "insert into statistic (name, count) values (?, ?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, text.toString());
                preparedStatement.setInt(2, intWritable.get());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (null != preparedStatement) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

        }
    }

    @Override
    public RecordWriter<Text, IntWritable> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new MySqlRecordWriter();
    }

    @Override
    public void checkOutputSpecs(JobContext jobContext) throws IOException, InterruptedException {

    }

    private FileOutputCommitter committer = null;

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        if (committer == null) {
            Path output = getOutputPath(taskAttemptContext);
            committer = new FileOutputCommitter(output, taskAttemptContext);
        }
        return committer;
    }

    public static Path getOutputPath(JobContext jobContext) {
        String name = jobContext.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null ? null : new Path(name);
    }
}
