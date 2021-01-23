package com.yglong.study.designpattern.adapter;

/**
 * 适配器模式
 * <p>
 * 该模式用于改变接口，从而适配使用者的接口
 */
public class AdapterPatternExample {
    public static void main(String[] args) {
        RealAdaptee realAdaptee = new RealAdaptee();
        Target adapter = new Adapter(realAdaptee);

        invokeTarget(adapter);
    }

    // 该客户端需要Target类型的对象，因此不能直接使用RealAdaptee
    // 可以使用适配器将RealAdaptee对象进行接口转换，从而适配这个客户端
    private static void invokeTarget(Target target) {
        target.operation1();
        target.operation2();
    }
}

// 目标接口
interface Target {
    void operation1();

    void operation2();
}

// 原始接口
interface Adaptee {
    void doOperation1();

    void doOperation2();
}

// 原始接口的具体实现类
class RealAdaptee implements Adaptee {

    @Override
    public void doOperation1() {
        System.out.println("doing operation1...");
    }

    @Override
    public void doOperation2() {
        System.out.println("doing operation2...");
    }
}

// 适配器，将原始接口适配成目标接口
class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }


    @Override
    public void operation1() {
        adaptee.doOperation1();
    }

    @Override
    public void operation2() {
        adaptee.doOperation2();
    }
}


