package com.yglong.study.designpattern.structure.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 * <p>
 * 使用共享对象可有效地支持大量的细粒度的对象。
 * <p>
 * 使用对象池来保存共享对象
 * <p>
 * 该模式实现对象共享，即具有相同属性的对象在系统中被共享，避免创建新的对象，从而减少内存资源的消耗。
 */
public class FlyweightPatternExample {

    public static void main(String[] args) {
        FlyweightFactory factory = FlyweightFactory.getInstance();
        Flyweight red = factory.getFlyweight("red");
        Flyweight blue = factory.getFlyweight("blue");
        Flyweight red1 = factory.getFlyweight("red");
        System.out.println(red);
        System.out.println(blue);
        System.out.println(red1);

        red.doOperation("colin");
        red1.doOperation("smith");
        blue.doOperation("colin");
    }
}

class FlyweightFactory {
    private Map<String, Flyweight> pool = new HashMap<>();

    public static FlyweightFactory getInstance() {
        return new FlyweightFactory();
    }

    public Flyweight getFlyweight(String internalState) {

        if (!pool.containsKey(internalState)) {
            Flyweight flyweight = new ConcreteFlyweight(internalState);
            pool.put(internalState, flyweight);
        }
        return pool.get(internalState);
    }
}

abstract class Flyweight {
    public abstract void doOperation(String externalState);
}

class ConcreteFlyweight extends Flyweight {
    private String internalState;

    public ConcreteFlyweight(String internalState) {
        this.internalState = internalState;
    }


    @Override
    public void doOperation(String externalState) {
        System.out.println("Concreate " + internalState + " flyweight doing operation for: " + externalState);
    }
}
