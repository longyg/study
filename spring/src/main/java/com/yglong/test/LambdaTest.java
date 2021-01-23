package com.yglong.test;

public class LambdaTest {
    public static void main(String[] args) {
        IFactory factory = IFactory.DEFAULT;
        IProduct web = factory.create("web");
        web.doWork();
    }
}

interface IFactory {

    IFactory DEFAULT = MyProduct::new;

    IProduct create(String type);
}

interface IProduct {
    void doWork();
}

class MyProduct implements IProduct {
    private String type;

    public MyProduct(String type) {
        this.type = type;
    }

    @Override
    public void doWork() {
        System.out.println("do work: " + type);
    }
}
