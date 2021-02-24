package com.yglong.study.designpattern.creation.singleton;

/**
 * 单例模式：静态内部类的方式
 */
public class Singleton03 {
    private Singleton03() {}

    public static Singleton03 getInstance() {
        return Holder.INSTANCE;
    }

    // 静态内部类会在第一次使用时才会加载，因此这种方式是懒汉模式
    private static class Holder {
        private static final Singleton03 INSTANCE = new Singleton03();
    }

    public static void main(String[] args) {
        Singleton03 instance1 = Singleton03.getInstance();
        Singleton03 instance2 = Singleton03.getInstance();
        System.out.println(instance1 == instance2);
    }
}
