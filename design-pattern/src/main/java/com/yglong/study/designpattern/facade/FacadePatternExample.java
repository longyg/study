package com.yglong.study.designpattern.facade;

/**
 * 外观模式，也称为门面模式
 * <p>
 * 该模式主要是用于封装复杂的子系统，为众多的子系统接口提供一个统一的简化的接口，从而简化客户端的使用
 */
public class FacadePatternExample {

    public static void main(String[] args) {
        Subsystem1 subsystem1 = new Subsystem1();
        Subsystem2 subsystem2 = new Subsystem2();
        Subsystem3 subsystem3 = new Subsystem3();

        SubsystemFacade subsystemFacade = new SubsystemFacade(subsystem1, subsystem2, subsystem3);
        // 客户端只需要调用这个简化的接口，而无需调用众多的子系统的接口
        subsystemFacade.doWork();
    }
}

class SubsystemFacade {
    private Subsystem1 subsystem1;
    private Subsystem2 subsystem2;
    private Subsystem3 subsystem3;

    public SubsystemFacade(Subsystem1 subsystem1, Subsystem2 subsystem2, Subsystem3 subsystem3) {
        this.subsystem1 = subsystem1;
        this.subsystem2 = subsystem2;
        this.subsystem3 = subsystem3;
    }

    public void doWork() {
        subsystem1.doWork();
        subsystem2.doWork();
        subsystem3.doWork();
    }
}

class Subsystem1 {
    public void doWork() {
        System.out.println("subsystem 1 doing work...");
    }
}

class Subsystem2 {
    public void doWork() {
        System.out.println("subsystem 2 doing work...");
    }
}

class Subsystem3 {
    public void doWork() {
        System.out.println("subsystem 3 doing work...");
    }
}
