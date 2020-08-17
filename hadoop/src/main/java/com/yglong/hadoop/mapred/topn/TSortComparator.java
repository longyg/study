package com.yglong.hadoop.mapred.topn;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 按年、月、温度排序，且温度降序
 */
public class TSortComparator extends WritableComparator {
    public TSortComparator() {
        super(MyKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        MyKey keyA = (MyKey) a;
        MyKey keyB = (MyKey) b;
        int c1 = Integer.compare(keyA.getYear(), keyB.getYear());
        if (c1 == 0) {
            int c2 = Integer.compare(keyA.getMonth(), keyB.getMonth());
            if (c2 == 0) {
                return -Integer.compare(keyA.getTemperature(), keyB.getTemperature());
            }
            return c2;
        }
        return c1;
    }
}
