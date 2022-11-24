package com.yglong.study.designpattern.creation.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 */
public class BuilderPatternExample2 {

    public static void main(String[] args) {
        Director director = new Director();
        director.getABenzModel().run();
        System.out.println("=============================");
        director.getBBenzModel().run();
        System.out.println("=============================");
        director.getABMWModel().run();
        System.out.println("=============================");
        director.getBBMWzModel().run();
    }
}

class Director {
    private List<String> sequence = new ArrayList<>();

    public BenzCarModel getABenzModel() {
        this.sequence.clear();
        this.sequence.add("start");
        this.sequence.add("stop");
        this.sequence.add("alarm");
        this.sequence.add("boom");
        return (BenzCarModel) BenzCarModel.builder().sequence(this.sequence).build();
    }

    public BenzCarModel getBBenzModel() {
        this.sequence.clear();
        this.sequence.add("boom");
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");
        return (BenzCarModel) BenzCarModel.builder().sequence(this.sequence).build();
    }

    public BMWCarModel getABMWModel() {
        this.sequence.clear();
        this.sequence.add("start");
        this.sequence.add("stop");
        this.sequence.add("alarm");
        this.sequence.add("boom");
        return (BMWCarModel) BMWCarModel.builder().sequence(this.sequence).build();
    }

    public BMWCarModel getBBMWzModel() {
        this.sequence.clear();
        this.sequence.add("boom");
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");
        return (BMWCarModel) BMWCarModel.builder().sequence(this.sequence).build();
    }
}

// 抽象建造者
abstract class CarBuilder {
    protected CarModel carModel;

    public CarBuilder sequence(List<String> sequence) {
        this.carModel.setSequence(sequence);
        return this;
    }

    protected CarModel build() {
        return carModel;
    }
}

// 具体建造者
class BMWCarBuilder extends CarBuilder {
    public BMWCarBuilder() {
        carModel = new BMWCarModel();
    }
}

// 具体建造者
class BenzCarBuilder extends CarBuilder {
    public BenzCarBuilder() {
        carModel = new BenzCarModel();
    }
}

abstract class CarModel {
    protected List<String> sequence = new ArrayList<>();

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    // 模板方法
    public void run() {
        sequence.forEach((actionName) -> {
            switch (actionName) {
                case "start":
                    this.start();
                    break;
                case "stop":
                    this.stop();
                    break;
                case "alarm":
                    this.alarm();
                    break;
                case "boom":
                    this.engineBoom();
                    break;
                default:
                    break;
            }
        });
    }

    public final void setSequence(List<String> sequence) {
        this.sequence = sequence;
    }
}

class BMWCarModel extends CarModel {

    public static BMWCarBuilder builder() {
        return new BMWCarBuilder();
    }

    @Override
    protected void start() {
        System.out.println("BMW starting...");
    }

    @Override
    protected void stop() {
        System.out.println("BMW stopped.");
    }

    @Override
    protected void alarm() {
        System.out.println("BMW alarming...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("BMW engine booming...");
    }
}

class BenzCarModel extends CarModel {

    public static BenzCarBuilder builder() {
        return new BenzCarBuilder();
    }

    @Override
    protected void start() {
        System.out.println("Benz starting...");
    }

    @Override
    protected void stop() {
        System.out.println("Benz stopped.");
    }

    @Override
    protected void alarm() {
        System.out.println("Benz alarming...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("Benz engine booming...");
    }
}
