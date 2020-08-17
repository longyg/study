package com.yglong.hadoop.mapred.transaction;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * 获取每一个订单中金额最大的交易
 */
public class Transaction {

    static class TransactionMapper extends Mapper<Object, Text, OrderBean, NullWritable> {
        OrderBean orderBean = new OrderBean();

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split("\\s+");
            orderBean.setOrderId(new Text(fields[0]));
            orderBean.setAmount(new DoubleWritable(Double.parseDouble(fields[2])));
            context.write(orderBean, NullWritable.get());
        }
    }

    static class TransactionReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable> {
        @Override
        protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key, NullWritable.get());
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
//        job.setPartitionerClass(OrderPartitioner.class);
//        job.setNumReduceTasks(2);
        job.setGroupingComparatorClass(MyGroupingComparator.class);
        job.setOutputKeyClass(OrderBean.class);
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
