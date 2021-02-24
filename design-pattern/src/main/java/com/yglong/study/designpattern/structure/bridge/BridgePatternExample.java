package com.yglong.study.designpattern.structure.bridge;

/**
 * 桥接模式
 * <p>
 * 如果某个类存在两个独立的变化维度，通过桥接模式可以将这两个维度分离出来，使两者可以独立扩展，
 * 使其复合单一职责原则
 * <p>
 * 该模式可以避免多层继承，从而减少具体类的数量
 * <p>
 * 本例中，电脑有两个可变化维度：
 * <ul>
 * <li>
 * 电脑类型：如台式机，笔记本
 * </li>
 * <li>
 * 电脑品牌：如联想，戴尔
 * </li>
 * </ul>
 * <p>
 * 如果采用继承结构，将产生很多子类，而且每个类会包含两个维度的信息，如：
 * <ul>
 * <li>
 * LenovoLaptop: 联想笔记本
 * </li>
 * <li>
 * LenovoDesktop: 联想台式机
 * </li>
 * <li>
 * DellLaptop: 戴尔笔记本
 * </li>
 * <li>
 * DellDesktop: 戴尔台式机
 * </li>
 * </ul>
 * <p>
 * 使用桥接模式，将两个维度独立抽象出来，从而将其分开，然后使用组合方式从而组合出多种具体对象。
 */
public class BridgePatternExample {
    public static void main(String[] args) {
        Computer lenovoLaptop = new Laptop(new Lenovo());
        lenovoLaptop.sale();

        Computer dellLaptop = new Laptop(new Dell());
        dellLaptop.sale();

        Computer lenovoDesktop = new Desktop(new Lenovo());
        lenovoDesktop.sale();

        Computer dellDesktop = new Desktop(new Dell());
        dellDesktop.sale();
    }
}

// 电脑
interface Computer {
    void sale();
}

// 抽象电脑
abstract class AbstractComputer implements Computer {
    protected Brand brand;

    public AbstractComputer(Brand brand) {
        this.brand = brand;
    }
}

// 笔记本电脑
class Laptop extends AbstractComputer {
    public Laptop(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        System.out.println("出售: " + brand.getBrand() + "笔记本");
    }
}

// 台式机
class Desktop extends AbstractComputer {

    public Desktop(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        System.out.println("出售：" + brand.getBrand() + "台式机");
    }
}

// 品牌
interface Brand {
    String getBrand();
}

// 联想
class Lenovo implements Brand {

    @Override
    public String getBrand() {
        return "联想";
    }
}

// 戴尔
class Dell implements Brand {

    @Override
    public String getBrand() {
        return "戴尔";
    }
}

