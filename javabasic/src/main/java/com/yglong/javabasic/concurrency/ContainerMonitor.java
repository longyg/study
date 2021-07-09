package com.yglong.javabasic.concurrency;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 */
public class ContainerMonitor {
    public static void main(String[] args) {
        Container container = new Container();
        AtomicBoolean t1Runnable = new AtomicBoolean(true);

        Thread t1 = new Thread(() -> {
            synchronized (container) {
                for (int i = 1; i <= 10; i++) {
                    if (t1Runnable.get()) {
                        container.add();
                        System.out.println("add " + i + " element");
                        container.notify();
                        try {
                            container.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        container.notify();
                    }
                }
            }
            System.out.println("T1 exit");
        });

        Thread t2 = new Thread(() -> {
            while (t1Runnable.get()) {
                synchronized (container) {
                    if (container.size() == 5) {
                        System.out.println("container size is full: " + container.size() + ", exit");
                        t1Runnable.set(false);
                    } else {
                        System.out.println("has element: " + container.size());
                    }
                    container.notify();
                    try {
                        container.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("T2 exit");
        });

        t1.start();
        t2.start();
    }


    private static class Container {
        private int size;

        void add() {
            size++;
        }

        int size() {
            return size;
        }
    }
}
