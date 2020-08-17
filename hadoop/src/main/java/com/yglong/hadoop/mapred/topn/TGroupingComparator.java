package com.yglong.hadoop.mapred.topn;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 按年、月分组，相同年、月的数据分为一组
 */
public class TGroupingComparator extends WritableComparator {
    public TGroupingComparator() {
        super(MyKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        MyKey keyA = (MyKey) a;
        MyKey keyB = (MyKey) b;
        int c1 = Integer.compare(keyA.getYear(), keyB.getYear());
        if (c1 == 0) {
            return Integer.compare(keyA.getMonth(), keyB.getMonth());
        }
        return c1;
    }
}
