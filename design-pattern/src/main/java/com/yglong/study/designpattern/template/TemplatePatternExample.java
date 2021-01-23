package com.yglong.study.designpattern.template;

/**
 * 模板方法模式
 * <p>
 * 该模式将固定算法进行封装，而算法中具体步骤的由子类实现
 */
public class TemplatePatternExample {

    public static void main(String[] args) {
        SubClass1 subClass1 = new SubClass1();
        subClass1.setDoTwoFlag(true);
        subClass1.templateMethod();

        SubClass2 subClass2 = new SubClass2();
        subClass2.templateMethod();
    }
}

abstract class AbstractClass {
    protected abstract void doOne();

    protected abstract void doTwo();

    // 模板方法，所有子类都具有相同的逻辑。
    public void templateMethod() {
        doOne();
        if (isDoTwo()) {
            doTwo();
        }
    }

    // 钩子函数，可以控制模板方法的行为
    // 控制是否执行doTwo方法
    protected boolean isDoTwo() {
        return true;
    }
}

class SubClass1 extends AbstractClass {
    private boolean doTwoFlag;

    @Override
    protected void doOne() {
        System.out.println("subclass1 do one");
    }

    @Override
    protected void doTwo() {
        System.out.println("subclass1 do two");
    }

    @Override
    protected boolean isDoTwo() {
        return doTwoFlag;
    }

    public void setDoTwoFlag(boolean flag) {
        this.doTwoFlag = flag;
    }
}

class SubClass2 extends AbstractClass {

    @Override
    protected void doOne() {
        System.out.println("subclass2 do one");
    }

    @Override
    protected void doTwo() {
        System.out.println("subclass2 do two");
    }

    @Override
    protected boolean isDoTwo() {
        return false;
    }
}

