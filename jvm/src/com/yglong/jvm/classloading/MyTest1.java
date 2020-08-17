package com.yglong.jvm.classloading;

public class MyTest1 {
    public static void main(String[] args) {
        System.out.println(MyChild1.b);
        System.out.println("--------------");
        System.out.println(MyChild11.c);
    }
}

class MyParent1 {

    public static int a = 3;

    static {
        System.out.println("MyParent1 static code");
    }
}

class MyChild1 extends MyParent1 {
    static {
        System.out.println("MyChild1 static code");
    }
    public static int b = 4;
}

class MyChild11 extends MyChild1 {
    static {
        System.out.println("MyChild2 static code");
    }
    public static final int c = 5;
}
