package com.yglong.study.designpattern.creation.factory.abstractfactory;

/**
 * 抽象工厂模式
 * 产品族：是指可以由一个工厂生产的所有产品，如电视机，空调，洗衣机
 * 产品等级结构：某一类产品，构成抽象与具体的继承关系，如抽象类电视机，可以有具体的不同品牌的电视机，如海尔电视机，TCL电视机
 *
 * 本例中，
 * 产品族有：电视机，空调，洗衣机
 * 工厂有：海尔，TCL
 */
public class AbstractFactoryExample {
    public static void main(String[] args) {
        IFactory haierFactory = new HaierFactory();
        IFactory tclFactory = new TCLFactory();

        ITelevision haierTelevision = haierFactory.makeTelevision();
        IAirConditioner haierAirConditioner = haierFactory.makeAirConditioner();
        IWashingMachine haierWahingMachine = haierFactory.makeWashingMachine();
        haierTelevision.watch();
        haierAirConditioner.makeCold();
        haierWahingMachine.wash();

        ITelevision tclTelevision = tclFactory.makeTelevision();
        IAirConditioner tclAirConditioner = tclFactory.makeAirConditioner();
        IWashingMachine tclWashingMachine = tclFactory.makeWashingMachine();
        tclTelevision.watch();
        tclAirConditioner.makeCold();
        tclWashingMachine.wash();
    }
}


interface ITelevision {
    void watch();
}

class HaierTelevision implements ITelevision {

    @Override
    public void watch() {
        System.out.println("watch hai'er television");
    }
}

class TCLTelevision implements ITelevision {

    @Override
    public void watch() {
        System.out.println("watch TLC television");
    }
}

interface IAirConditioner {
    void makeCold();
}

class HaierAirConditioner implements IAirConditioner {

    @Override
    public void makeCold() {
        System.out.println("Haier air conditioner make cold");
    }
}

class TCLAirConditioner implements IAirConditioner {
    @Override
    public void makeCold() {
        System.out.println("TCL air conditioner make cold");
    }
}

interface IWashingMachine {
    void wash();
}

class HaierWashingMachine implements IWashingMachine {

    @Override
    public void wash() {
        System.out.println("Washing with haier washing machine");
    }
}

class TCLWashingMachine implements IWashingMachine {

    @Override
    public void wash() {
        System.out.println("Washing with TCL washing machine");
    }
}

// 抽象工厂
// 一个工厂可以制作一个产品族的所有产品
interface IFactory {
    ITelevision makeTelevision();
    IAirConditioner makeAirConditioner();
    IWashingMachine makeWashingMachine();
}

// 海尔工厂：可以生产海尔品牌的产品
class HaierFactory implements IFactory {

    @Override
    public ITelevision makeTelevision() {
        return new HaierTelevision();
    }

    @Override
    public IAirConditioner makeAirConditioner() {
        return new HaierAirConditioner();
    }

    @Override
    public IWashingMachine makeWashingMachine() {
        return new HaierWashingMachine();
    }
}

// TCL工厂: 可以生产TCL品牌的产品
class TCLFactory implements IFactory {

    @Override
    public ITelevision makeTelevision() {
        return new TCLTelevision();
    }

    @Override
    public IAirConditioner makeAirConditioner() {
        return new TCLAirConditioner();
    }

    @Override
    public IWashingMachine makeWashingMachine() {
        return new TCLWashingMachine();
    }
}
