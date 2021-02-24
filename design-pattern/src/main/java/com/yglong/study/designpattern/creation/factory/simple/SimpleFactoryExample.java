package com.yglong.study.designpattern.creation.factory.simple;

/**
 * 简单工厂模式
 * <p>
 * 实现一个静态工厂方法用于创建对象
 */
public class SimpleFactoryExample {

    public static void main(String[] args) throws Exception {
        IProduct productA = ProductFactory.makeProduct(ProductA.class);
        IProduct productB = ProductFactory.makeProduct(ProductB.class);

        productA.doSomething();
        productB.doSomething();
    }
}

class ProductFactory {
    public static IProduct makeProduct(Class<? extends IProduct> clazz) {
        if (null != clazz) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

interface IProduct {
    void doSomething();
}

class ProductA implements IProduct {

    @Override
    public void doSomething() {
        System.out.println("I'm product A");
    }
}

class ProductB implements IProduct {

    @Override
    public void doSomething() {
        System.out.println("I'm product B");
    }
}
