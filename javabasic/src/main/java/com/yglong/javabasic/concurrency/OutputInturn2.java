package com.yglong.javabasic.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * 用两个线程交替输出：1A2B3C4D5E6F7G
 */
public class OutputInturn2 {
    private static final String alpha = "ABCDEFG";
    private static Thread alphaThread, numThread;


    public static void main(String[] args) {
        alphaThread = new Thread(() -> {
            for (int i = 0; i < alpha.length(); i++) {
                LockSupport.park();
                System.out.print(alpha.charAt(i));
                LockSupport.unpark(numThread);
            }
        });

        numThread = new Thread(() -> {
            for (int i = 1; i <= 7; i++) {
                System.out.print(i);
                LockSupport.unpark(alphaThread);
                LockSupport.park();
            }
        });

        alphaThread.start();
        numThread.start();
    }
}
