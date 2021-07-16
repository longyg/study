package com.yglong.javabasic.concurrency;

import java.util.concurrent.Semaphore;

/**
 * 用两个线程交替输出：1A2B3C4D5E6F7G
 */
public class OutputInturn3 {
    private static final String alpha = "ABCDEFG";
    private static Thread alphaThread, numThread;
    private static Semaphore alphaSp = new Semaphore(0);
    private static Semaphore numSp = new Semaphore(1);


    public static void main(String[] args) {
        alphaThread = new Thread(() -> {
            for (int i = 0; i < alpha.length(); i++) {
                try {
                    alphaSp.acquire();
                    System.out.print(alpha.charAt(i));
                    numSp.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        numThread = new Thread(() -> {
            for (int i = 1; i <= 7; i++) {
                try {
                    numSp.acquire();
                    System.out.print(i);
                    alphaSp.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        alphaThread.start();
        numThread.start();
    }
}
