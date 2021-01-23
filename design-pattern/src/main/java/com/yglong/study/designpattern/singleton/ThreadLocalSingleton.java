package com.yglong.study.designpattern.singleton;

/**
 * 线程单例：，利用ThreadLocal，保证同一个线程只有一个实例，但不同线程之间的实例不同
 */
public class ThreadLocalSingleton {

    private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance = ThreadLocal.withInitial(ThreadLocalSingleton::new);

    private ThreadLocalSingleton() {}

    public static ThreadLocalSingleton getInstance() {
        return threadLocalInstance.get();
    }

    public static void main(String[] args) {
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());

        new Thread(() -> {
            System.out.println(Thread.currentThread() + ": " + ThreadLocalSingleton.getInstance());
            System.out.println(Thread.currentThread() + ": " + ThreadLocalSingleton.getInstance());
        }).start();
        new Thread(() -> {
            System.out.println(Thread.currentThread() + ": " + ThreadLocalSingleton.getInstance());
            System.out.println(Thread.currentThread() + ": " + ThreadLocalSingleton.getInstance());
        }).start();
    }
}
