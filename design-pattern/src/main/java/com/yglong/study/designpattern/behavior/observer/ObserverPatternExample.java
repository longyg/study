package com.yglong.study.designpattern.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * <p>
 * 实时将对象状态的变化通知给其观察者
 */
public class ObserverPatternExample {
    public static void main(String[] args) {
        WeatherDisplay display = new WeatherDisplay();
        Weather weather = new Weather();
        weather.registerObserver(display);
        weather.setTemperature(35);
        weather.weatherChanged();

        weather.setTemperature(30);
        weather.weatherChanged();
    }
}

interface Observable {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

class Weather implements Observable {
    private List<Observer> observers;

    private int temperature;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public Weather() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void weatherChanged() {
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }
}

interface Observer {
    void update(Observable observable);
}

class WeatherDisplay implements Observer {

    @Override
    public void update(Observable observable) {
        System.out.println("Display new temperature: " + ((Weather) observable).getTemperature());
    }
}


