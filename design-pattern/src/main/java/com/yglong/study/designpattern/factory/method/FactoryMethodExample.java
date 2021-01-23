package com.yglong.study.designpattern.factory.method;

/**
 * 工厂方法模式
 * <p>
 * 使用不同的工厂子类创建不同的具体产品
 */
public class FactoryMethodExample {
    public static void main(String[] args) {
        IFactory productAFactory = new ProductAFactory();
        IFactory productBFactory = new ProductBFactory();
        IProduct productA = productAFactory.makeProduct();
        IProduct productB = productBFactory.makeProduct();

        productA.doSomething();
        productB.doSomething();
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

interface IFactory {
    IProduct makeProduct();
}

class ProductAFactory implements IFactory {

    @Override
    public IProduct makeProduct() {
        return new ProductA();
    }
}

class ProductBFactory implements IFactory {

    @Override
    public IProduct makeProduct() {
        return new ProductB();
    }
}




