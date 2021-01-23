package com.yglong.study.designpattern.decorator;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.swing.*;

/**
 * 装饰者模式
 * <p>
 * 该模式用于装饰一个组件，从而增强其功能
 */
public class DecoratorPatternExample {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        ConcreteDecoratorA decoratorA = new ConcreteDecoratorA(component);
        ConcreteDecoratorB decoratorB = new ConcreteDecoratorB(decoratorA);
        decoratorB.operation();
    }
}


abstract class Component {
    abstract void operation();
}

class ConcreteComponent extends Component {

    @Override
    public void operation() {
        System.out.println("Concrete component doing operation...");
    }
}

class Decorator extends Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    void operation() {
        component.operation();
    }
}

class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        addBehaviorA();
        component.operation();
    }

    private void addBehaviorA() {
        System.out.println("Concrete decorator A doing operation...");
    }
}

class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        addBehaviorB();
        component.operation();
    }

    private void addBehaviorB() {
        System.out.println("Concrete decorator B doing operation...");
    }
}
