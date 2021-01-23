package com.yglong.study.designpattern.proxy;

/**
 * 代理模式
 * <p>
 * 该模式实现一个代理对象，包装被代理对象，从而可以在被代理对象之上提供额外功能。
 * <p>
 * 该示例为静态代理的方式，即需要我们手动实现代理类。
 * <p>
 * 而动态代理方式，不需要自定定义代理类，而是由JDK API或者CGLIB动态产生代理类
 * <p>
 * <p>
 * 代理模式与装饰器模式的区别：
 * <p>
 * 1、装饰器模式强调的是增强自身，在被装饰之后你能够在被增强的类上使用增强后的功能。增强后你还是你，只不过能力更强了而已；代理模式强调要让别人帮你去做一些本身与你业务没有太多关系的职责（记录日志、设置缓存）。代理模式是为了实现对象的控制，因为被代理的对象往往难以直接获得或者是其内部不想暴露出来。
 * <p>
 * 2、装饰模式是以对客户端透明的方式扩展对象的功能，是继承方案的一个替代方案；代理模式则是给一个对象提供一个代理对象，并由代理对象来控制对原有对象的引用；
 * <p>
 * 3、装饰模式是为装饰的对象增强功能；而代理模式对代理的对象施加控制，但不对对象本身的功能进行增强；
 */
public class ProxyPatternExample {

    public static void main(String[] args) {
        Subject subject = new MySubject();
        Subject proxy = new Proxy(subject);
        proxy.request();
    }
}

interface Subject {
    void request();
}

class MySubject implements Subject {

    @Override
    public void request() {
        System.out.println("my subject do request");
    }
}

class Proxy implements Subject {
    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        this.before();
        subject.request();
        this.after();
    }

    private void before() {
        System.out.println("before request");
    }

    private void after() {
        System.out.println("after request");
    }
}