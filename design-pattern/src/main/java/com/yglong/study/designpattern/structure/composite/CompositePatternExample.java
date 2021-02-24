package com.yglong.study.designpattern.structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 * <p>
 * 组合对象和个体对象实现统一的接口，从而可以对它们进行统一的处理
 */
public class CompositePatternExample {
    public static void main(String[] args) {
        MenuComponent root = new Menu("root", 0);

        MenuComponent menu1 = new Menu("menu1", 1);
        MenuComponent menu3 = new Menu("menu3", 1);

        menu1.add(new MenuItem("menuItem1"));
        menu1.add(new MenuItem("menuItem2"));

        Menu menu2 = new Menu("menu2", 2);
        menu2.add(new MenuItem("menuItem 2_1"));
        menu2.add(new MenuItem("menuItem 2_2"));
        menu1.add(menu2);

        root.add(menu1);
        root.add(menu3);

        root.show();
    }
}

// 定义组合对象与个体对象的共同抽象类
abstract class MenuComponent {
    // 共有方法
    abstract void show();

    // 组合对象特有方法
    void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    List<MenuComponent> getChild() {
        throw new UnsupportedOperationException();
    }

    // 个体对象特有方法

}

class Menu extends MenuComponent {
    private String name;
    private int level;
    private List<MenuComponent> menuComponents = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    List<MenuComponent> getChild() {
        return menuComponents;
    }

    @Override
    void show() {
        for (int i = 0; i < this.level; i++) {
            System.out.print("\t");
        }
        System.out.print(" + ");
        System.out.println(name);
        for (MenuComponent menuComponent : menuComponents) {
            for (int i = 0; i < this.level; i++) {
                System.out.print("\t");
            }
            menuComponent.show();
        }
    }
}

class MenuItem extends MenuComponent {
    private String name;

    public MenuItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    void show() {
        System.out.println("\t- " + name);
    }
}


