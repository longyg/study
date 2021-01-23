package com.yglong.study.designpattern.singleton;

/**
 * 单例模式：懒汉模式
 */
public class Singleton02 {
    // 必须加volatile，保证线程之间的可见性
    private static volatile Singleton02 instance;

    private Singleton02() {}

    // 双重检查，保证线程安全
    public static Singleton02 getInstance() {
        if (null == instance) { // 第一次检查
            synchronized (Singleton02.class) {
                if (null == instance) { // 第二次检查
                    instance = new Singleton02();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton02 instance1 = Singleton02.getInstance();
        Singleton02 instance2 = Singleton02.getInstance();
        System.out.println(instance1 == instance2);
    }
}
