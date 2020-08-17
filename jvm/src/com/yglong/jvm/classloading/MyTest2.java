package com.yglong.jvm.classloading;

import java.util.UUID;

public class MyTest2 {
    public static void main(String[] args) {
        System.out.println(MyChild2Impl.c);
        System.out.println("--------------");
        System.out.println(MyChild2.b);
        System.out.println("--------------");
        System.out.println(MyParent2.a);

    }
}

interface MyParent2 {
    Thread thread = new Thread() {
        {
            System.out.println("MyParent2 thread");
        }
    };
    int a = UUID.randomUUID().hashCode();
}

interface MyChild2 extends MyParent2 {
    int b = 4;
    Thread thread = new Thread() {
        {
            System.out.println("MyChild2 thread");
        }
    };
}

class MyChild2Impl implements MyChild2 {
    static {
        System.out.println("MyChild2Impl static code");
    }
    public static int c = 5;
}
