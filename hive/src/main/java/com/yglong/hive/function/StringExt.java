package com.yglong.hive.function;

import org.apache.hadoop.hive.ql.exec.UDF;

public class StringExt extends UDF {
    public String evaluate(String name) {
        return "Hello " + name;
    }
}
