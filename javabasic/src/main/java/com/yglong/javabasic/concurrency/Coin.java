package com.yglong.javabasic.concurrency;

import java.util.Random;

/**
 * 编写一个Java程序,该程序将启动4个线程,其中3个是掷硬币线程,1个是主线程。
 * <p>
 * 每个掷硬币线程将连续掷出若干次硬币(10次以内,次数随机生成);主线程将打印出正面出现的总次数以及正面出现的概率。
 */
public class Coin {
    private static int total;
    private static int count;
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            Thread t = new TossThread();
            threads[i] = t;
        }
        for (int i = 0; i < 3; i++) {
            threads[i].start();
        }
        for (int i = 0; i < 3; i++) {
            threads[i].join();
        }
        System.out.println("count: " + count);
        System.out.println("probable: " + ((double) count / total));
    }

    private static class TossThread extends Thread {

        @Override
        public void run() {
            synchronized (lock) {
                Random r = new Random();
                int i = r.nextInt(10);
                for (int j = 0; j < i; j++) {
                    if (r.nextBoolean()) {
                        count++;
                    }
                    total++;
                    System.out.println("Thread" + Thread.currentThread() + " total: " + total + ", count: " + count);
                }
            }
        }
    }
}
