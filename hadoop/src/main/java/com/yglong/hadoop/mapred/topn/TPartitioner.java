package com.yglong.hadoop.mapred.topn;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 可以按年，或者按月分区
 * 保证同年同月的数据分到一个分区即可
 */
public class TPartitioner extends Partitioner<MyKey, NullWritable> {
    @Override
    public int getPartition(MyKey myKey, NullWritable nullWritable, int numPartitions) {
        return (Integer.hashCode(myKey.getYear()) & Integer.MAX_VALUE) % numPartitions;
    }
}
