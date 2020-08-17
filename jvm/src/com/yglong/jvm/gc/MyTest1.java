package com.yglong.jvm.gc;

/**
 * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+PrintCommandLineFlags
 */
public class MyTest1 {
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        System.out.println("-------------");
        allocation1 = new byte[2 * _1MB];
        System.out.println(allocation1);
        System.out.println("-------------");
        allocation2 = new byte[2 * _1MB];
        System.out.println(allocation2);
        System.out.println("-------------");
        allocation3 = new byte[2 * _1MB];
        System.out.println(allocation3);
        System.out.println("-------------");
        allocation4 = new byte[4 * _1MB];
        System.out.println(allocation4);
    }

    public static void main(String[] args) {
        testAllocation();
    }
}
