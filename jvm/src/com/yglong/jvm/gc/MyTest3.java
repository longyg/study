package com.yglong.jvm.gc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyTest3 {
    public static void createBusyThread() {
        new Thread(() -> {
            while (true) {
                ;
            }
        }, "testBusyThread").start();
    }

    public static void createLockThread(final Object lock) {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread").start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object lock = new Object();
        createLockThread(lock);
    }
}
