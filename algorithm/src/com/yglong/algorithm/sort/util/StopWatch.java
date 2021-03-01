package com.yglong.algorithm.sort.util;


public class StopWatch {
    private final long currentTime;
    public StopWatch() {
        currentTime = System.currentTimeMillis();
    }

    public long end() {
        return System.currentTimeMillis() - currentTime;
    }
}
