package com.yglong.javabasic.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * 用两个线程交替输出：1A2B3C4D5E6F7G
 */
public class OutputInturn {
    private static Object lock = new Object();
    private static final String alpha = "ABCDEFG";
    private static CountDownLatch latch = new CountDownLatch(1);

    private static class AlphaThread extends Thread {
        @Override
        public void run() {
            try {
                latch.await(); // 线程等待，让另一个线程先获得lock锁，从而保证数字打印在前
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < alpha.length(); i++) {
                synchronized (lock) {
                    try {
                        System.out.println(alpha.charAt(i));
                        lock.notify(); // 每打印一个字母，唤醒数字打印线程
                        lock.wait(); // 唤醒数字打印线程后，等待并释放锁，让数字打印线程能够获得锁并执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class NumThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 7; i++) {
                synchronized (lock) {
                    latch.countDown(); // 保证 NumThread 获取到锁之前，AlphaThread一直等待
                    try {
                        System.out.println(i);
                        lock.notify(); // 每打印一个数字，唤醒字母打印线程
                        lock.wait(); // 打印一次数字后，等待并释放锁，让字母打印线程能够获得锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            /*
             * 因为AlphaThread后执行完，它最后会执行wait()，释放锁并一直处于等待，
             * 因此需要在这个线程中获取锁，并唤醒AlphaThread使其运行完成并退出，否则整个程序会一直挂起
             */
            synchronized (lock) {
                lock.notify();
            }
        }
    }

    public static void main(String[] args) {
        new AlphaThread().start();
        new NumThread().start();
    }
}
