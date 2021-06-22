package com.yglong.jvm.basic;

public class InnerClassTest {
    private static int counter = 0;

    private String name;

    private void say() {
        Inner inner = new Inner();
        inner.say();
    }

    private class Inner {
        void say() {
            System.out.println(name);
        }
    }

    private static class StaticInner {
        void say() {
            System.out.println(++counter);
        }
    }

    public static void main(String[] args) {
        InnerClassTest test = new InnerClassTest();
        test.name = "yglong";
        test.say();

        StaticInner staticInner = new StaticInner();
        staticInner.say();
    }
}
