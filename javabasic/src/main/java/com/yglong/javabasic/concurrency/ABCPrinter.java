package com.yglong.javabasic.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 用三个线程交替打印ABC：ABCABCABC
 */
public class ABCPrinter {
    static Thread tA, tB, tC;

    public static void main(String[] args) {
        int c = 5;
        tA = new Thread(() -> {
            for (int i = 0; i < c; i++) {
                System.out.print("A");
                LockSupport.unpark(tB);
                LockSupport.park();
            }
        });

        tB = new Thread(() -> {
            for (int i = 0; i < c; i++) {
                LockSupport.park();
                System.out.print("B");
                LockSupport.unpark(tC);
            }
        });

        tC = new Thread(() -> {
            for (int i = 0; i < c; i++) {
                LockSupport.park();
                System.out.print("C");
                LockSupport.unpark(tA);
            }
        });

        tA.start();
        tB.start();
        tC.start();
    }
}
