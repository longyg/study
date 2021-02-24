package com.yglong.study.designpattern.creation.singleton;

/**
 * 单例模式：枚举类的方式
 * 饿汉模式
 */
public enum EnumSingleton {
    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        EnumSingleton instance1 = EnumSingleton.getInstance();
        EnumSingleton instance2 = EnumSingleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
