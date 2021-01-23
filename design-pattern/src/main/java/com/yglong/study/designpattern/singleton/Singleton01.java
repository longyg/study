package com.yglong.study.designpattern.singleton;

import java.io.Serializable;

/**
 * 单例模式：饿汉模式
 */
public class Singleton01 implements Serializable {

    private static final Singleton01 INSTANCE = new Singleton01();

    private Singleton01() {}

    public static Singleton01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Singleton01 instance1 = Singleton01.getInstance();
        Singleton01 instance2 = Singleton01.getInstance();
        System.out.println(instance1 == instance2);
    }

    // 防止反序列化产生新的对象
    private Object readResolve() {
        return INSTANCE;
    }
}
