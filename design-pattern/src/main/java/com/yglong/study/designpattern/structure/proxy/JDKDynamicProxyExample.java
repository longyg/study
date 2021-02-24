package com.yglong.study.designpattern.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用JDK提供的动态代理API
 * 需要代理的类必须实现一个接口
 */
public class JDKDynamicProxyExample {
    public static void main(String[] args) {
        IPerson person = new Person();

        //静态代理方式，创建Person对象的代理对象
        StaticPersonProxy staticPersonProxy = new StaticPersonProxy(person);
        staticPersonProxy.sayHello("yglong");
        staticPersonProxy.eat();
        System.out.println("========================");

        //动态代理方式，创建Person对象的代理对象
        IPerson dynamicPersonProxy = (IPerson) DynamicProxyHandler.getInstance(person);
        dynamicPersonProxy.sayHello("yglong");
        dynamicPersonProxy.eat();

        System.out.println("========================");
        Student student = new Student();
        // 使用动态代理，就无需再为Student类自定义代理类了
        IPerson studentProxy = (IPerson) DynamicProxyHandler.getInstance(student);
        studentProxy.sayHello("yglong");
        studentProxy.eat();
    }
}

interface IPerson {
    void sayHello(String name);

    void eat();
}

class Person implements IPerson {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name);
    }

    @Override
    public void eat() {
        System.out.println("I'm eating food...");
    }
}

class Student implements IPerson {

    @Override
    public void sayHello(String name) {
        System.out.println("Student: Hello, " + name);
    }

    @Override
    public void eat() {
        System.out.println("Student: eating...");
    }
}

/**
 * 静态代理类
 */
class StaticPersonProxy implements IPerson {
    private IPerson person;

    public StaticPersonProxy(IPerson person) {
        this.person = person;
    }

    @Override
    public void sayHello(String name) {
        System.out.println("before"); // 这部分代码是重复的，使用动态代理可以消除这样的重复代码
        person.sayHello(name);
        System.out.println("after");
    }

    @Override
    public void eat() {
        System.out.println("before");
        person.eat();
        System.out.println("after");
    }
}

/**
 * 动态代理处理器
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object target;

    public DynamicProxyHandler(Object target) {
        this.target = target;
    }

    public static Object getInstance(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new DynamicProxyHandler(target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(this.target, args);
        System.out.println("after");
        return result;
    }
}