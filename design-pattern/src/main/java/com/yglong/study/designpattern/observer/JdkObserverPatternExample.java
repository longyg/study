package com.yglong.study.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * JDK观察者模式使用
 */
public class JdkObserverPatternExample {
    public static void main(String[] args) {
        MySubject subject = new MySubject();
        subject.addObserver(new MyObserver());

        subject.setData("hello world");
        subject.notifyObservers();

        subject.setData("test");
        subject.notifyObservers();
    }
}

class MySubject extends Observable {
    private String data;

    public void setData(String data) {
        this.data = data;
        setChanged();
    }

    public String getData() {
        return data;
    }
}

class MyObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("Data updated to: " + ((MySubject) o).getData());
    }
}
