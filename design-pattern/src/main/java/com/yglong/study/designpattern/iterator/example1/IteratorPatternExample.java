package com.yglong.study.designpattern.iterator.example1;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式
 * <p>
 * 使用统一的迭代集合元素的的迭代器，避免暴露集合的具体内部实现
 * <p>
 * 使用自定义的Iterator接口
 */
public class IteratorPatternExample {
    public static void main(String[] args) {
        Menu breakfastMenu = new BreakfastMenu();
        breakfastMenu.add(new MenuItem("egg", "egg", 0.5));
        breakfastMenu.add(new MenuItem("milk", "milk", 3));

        Menu dinnerMenu = new DinnerMenu();
        dinnerMenu.add(new MenuItem("rice", "rice", 1));
        dinnerMenu.add(new MenuItem("veg", "veg", 2));

        Waiters waiter = new Waiters(dinnerMenu, breakfastMenu);
        waiter.printMenu();
    }
}

class Waiters {
    private Menu dinnerMenu;
    private Menu breakfastMenu;

    public Waiters(Menu dinnerMenu, Menu breakfastMenu) {
        this.dinnerMenu = dinnerMenu;
        this.breakfastMenu = breakfastMenu;
    }

    public void printMenu() {
        Iterator<MenuItem> dinnerMenuIterator = dinnerMenu.createIterator();
        printMenu(dinnerMenuIterator);
        Iterator<MenuItem> breakfastMenuIterator = breakfastMenu.createIterator();
        printMenu(breakfastMenuIterator);
    }

    private void printMenu(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            iterator.next().show();
        }
    }
}

interface Iterator<T> {
    boolean hasNext();

    T next();
}

class ArrayMenuIterator<T> implements Iterator<T> {
    private T[] items;
    private int position = 0;

    public ArrayMenuIterator(T[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }

    @Override
    public T next() {
        return items[position++];
    }
}

class ListMenuIterator<T> implements Iterator<T> {
    private List<T> items;
    private int position;

    public ListMenuIterator(List<T> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.size() && items.get(position) != null;
    }

    @Override
    public T next() {
        return items.get(position++);
    }
}

interface Menu {
    Iterator<MenuItem> createIterator();

    void add(MenuItem menuItem);
}

// 晚餐菜单
class DinnerMenu implements Menu {
    private int position = 0;
    private MenuItem[] menuItems = new MenuItem[10];

    @Override
    public Iterator<MenuItem> createIterator() {
        return new ArrayMenuIterator<>(menuItems);
    }

    @Override
    public void add(MenuItem menuItem) {
        if (position >= menuItems.length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            menuItems[position++] = menuItem;
        }
    }
}

// 早餐菜单
class BreakfastMenu implements Menu {
    private List<MenuItem> menuItems = new ArrayList<>();

    @Override
    public Iterator<MenuItem> createIterator() {
        return new ListMenuIterator<>(menuItems);
    }

    @Override
    public void add(MenuItem menuItem) {
        menuItems.add(menuItem);
    }
}

// 菜单项
class MenuItem {
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void show() {
        System.out.println(this);
    }
}
