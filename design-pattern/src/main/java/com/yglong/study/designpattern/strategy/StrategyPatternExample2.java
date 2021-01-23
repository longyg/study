package com.yglong.study.designpattern.strategy;

public class StrategyPatternExample2 {
    public static void main(String[] args) {
        Cat[] cats = new Cat[]{new Cat(5, 5), new Cat(1, 1), new Cat(3, 3), new Cat(6, 6)};

        Sorter sorter = new Sorter();
        sorter.sort(cats, new CatHeightComparator());

        for (Cat cat : cats) {
            System.out.println(cat);
        }
        System.out.println("=============================");
        sorter.sort(cats, new CatWeightComparator());
        for (Cat cat : cats) {
            System.out.println(cat);
        }

        System.out.println("=============================");
        sorter.sort(cats);
        for (Cat cat : cats) {
            System.out.println(cat);
        }
    }
}

class Sorter {
    public <T> void sort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (comparator.compare(array[i], array[j]) > 0) {
                    T tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
    }

    public <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    T tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
    }
}

class Cat implements Comparable<Cat> {
    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    private int weight;
    private int height;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int compareTo(Cat obj) {
        return this.getWeight() - obj.getWeight();
    }
}

interface Comparable<T> {
    int compareTo(T obj);
}


interface Comparator<T> {
    int compare(T o1, T o2);
}

class CatWeightComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o1, Cat o2) {
        return o2.getWeight() - o1.getWeight();
    }
}

class CatHeightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.getHeight() - o2.getHeight();
    }
}








