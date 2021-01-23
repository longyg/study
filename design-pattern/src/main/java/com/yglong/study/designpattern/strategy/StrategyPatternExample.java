package com.yglong.study.designpattern.strategy;

/**
 * 策略模式
 * <p>
 * 将变化的行为部分抽取出来，由具体的子类实现不同的行为，然后可以为对象动态的设置不同的行为。
 */
public class StrategyPatternExample {
    public static void main(String[] args) {
        King king = new King();
        king.setWeaponBehavior(new Sword());
        king.fight();

        Soldier soldier = new Soldier();
        soldier.setWeaponBehavior(new Gun());
        soldier.fight();
    }
}

interface Character {
    void fight();
}

class King implements Character {
    private WeaponBehavior weaponBehavior;

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

    @Override
    public void fight() {
        System.out.println("King is fighting...");
        weaponBehavior.useWeapon();
    }
}

class Soldier implements Character {

    private WeaponBehavior weaponBehavior;

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

    @Override
    public void fight() {
        System.out.println("Soldier is fighting...");
        weaponBehavior.useWeapon();
    }
}

interface WeaponBehavior {
    void useWeapon();
}

class Sword implements WeaponBehavior {

    @Override
    public void useWeapon() {
        System.out.println("use sword");
    }
}

class Gun implements WeaponBehavior {

    @Override
    public void useWeapon() {
        System.out.println("use gun");
    }
}
