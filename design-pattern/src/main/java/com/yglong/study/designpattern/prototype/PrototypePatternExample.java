package com.yglong.study.designpattern.prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式
 * <p>
 * 以已有对象为原型，复制其产生新的对象，从而避免重新创建对象
 */
public class PrototypePatternExample {
    public static void main(String[] args) throws Exception {
        Student tom = new Student();
        tom.setName("Tom");
        tom.addHobbit("read book");
        tom.addHobbit("play game");
        tom.doWork();

        Student lucy = tom.deepClone();
        lucy.setName("Lucy");
        System.out.println(tom);
        System.out.println(lucy);
        lucy.addHobbit("learn");
        lucy.doWork();
        tom.doWork();

    }
}

interface Person {
    void doWork();
}

// 原型模式需要实现Cloneable接口
class Student implements Person, Cloneable, Serializable {
    private String name;

    private List<String> hobbits = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void addHobbit(String hobbit) {
        hobbits.add(hobbit);
    }

    // 原型模式需要实现clone方法。
    // 但这是浅拷贝
    @Override
    protected Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }

    // 实现深拷贝方法
    public Student deepClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (Student) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void doWork() {
        System.out.println(this.name + " is doing work...");
        this.hobbits.forEach(System.out::println);
    }
}
