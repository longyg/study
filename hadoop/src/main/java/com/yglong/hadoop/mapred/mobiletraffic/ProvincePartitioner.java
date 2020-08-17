package com.yglong.hadoop.mapred.mobiletraffic;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;
import java.util.Map;

public class ProvincePartitioner extends Partitioner<Text, Traffic> {
    public static Map<String, Integer> provinceDict = new HashMap<>();
    static {
        provinceDict.put("158", 0);
        provinceDict.put("152", 1);
        provinceDict.put("135", 2);
    }

    /**
     * 分区号必须从0开始连续递增
     * @param text
     * @param traffic
     * @param numPartitions
     * @return
     */
    @Override
    public int getPartition(Text text, Traffic traffic, int numPartitions) {
        String prefix = text.toString().substring(0, 3);
        Integer provinceId = provinceDict.get(prefix);
        return provinceId == null ? 3 : provinceId;
    }
}
