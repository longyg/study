package com.yglong.study.designpattern.behavior.state;

/**
 * 状态模式
 * <p>
 * 通过控制对象状态的变化，从而使对象的产生不同的行为
 */
public class StatePatternExample {
    public static void main(String[] args) {
        CandyMachine candyMachine = new CandyMachine(3);
        candyMachine.insertCoin();
        candyMachine.returnCoin();
        System.out.println("=========");
        candyMachine.insertCoin();
        candyMachine.pushButton();
        System.out.println("=========");
        candyMachine.insertCoin();
        candyMachine.pushButton();
        System.out.println("=========");
        candyMachine.insertCoin();
        candyMachine.pushButton();
        System.out.println("=========");
        candyMachine.insertCoin();
        candyMachine.pushButton();
        candyMachine.returnCoin();
    }
}


// 糖果机
class CandyMachine {
    // 糖果机的各种状态
    private State noCoinState;
    private State hasCoinState;
    private State soldState;
    private State soldOutState;

    // 糖果数量
    private int count;
    // 当前状态
    private State state;

    public CandyMachine(int count) {
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);

        state = soldOutState;

        this.count = count;
        if (this.count > 0) {
            state = noCoinState;
        }
    }

    public void insertCoin() {
        state.insertCoin();
    }

    public void pushButton() {
        if (state.pushButton()) {
            state.dispense();
        }
    }

    public void returnCoin() {
        state.returnCoin();
    }

    public State getNoCoinState() {
        return noCoinState;
    }

    public void setNoCoinState(State noCoinState) {
        this.noCoinState = noCoinState;
    }

    public State getHasCoinState() {
        return hasCoinState;
    }

    public void setHasCoinState(State hasCoinState) {
        this.hasCoinState = hasCoinState;
    }

    public State getSoldState() {
        return soldState;
    }

    public void setSoldState(State soldState) {
        this.soldState = soldState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public void setSoldOutState(State soldOutState) {
        this.soldOutState = soldOutState;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

interface State {
    // 收入硬币
    void insertCoin();

    // 按下按钮
    // 返回：是否成功
    boolean pushButton();

    // 退回硬币
    void returnCoin();

    // 发出糖果
    void dispense();
}

// 未投入硬币状态
class NoCoinState implements State {
    private CandyMachine machine;

    public NoCoinState(CandyMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("you inserted coin, please push button to get candy");
        machine.setState(machine.getHasCoinState());
    }

    @Override
    public boolean pushButton() {
        System.out.println("please insert coin");
        return false;
    }

    @Override
    public void returnCoin() {
        System.out.println("you haven't insert coin, can't return");
    }

    @Override
    public void dispense() {
        System.out.println("please push button to get candy");
    }
}

// 已投入硬币状态
class HasCoinState implements State {
    private CandyMachine machine;

    public HasCoinState(CandyMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("already inserted coin, can't insert again");
    }

    @Override
    public boolean pushButton() {
        System.out.println("you pushed button, will dispense candy to you");
        machine.setState(machine.getSoldState());
        return true;
    }

    @Override
    public void returnCoin() {
        System.out.println("returned coin to you");
        machine.setState(machine.getNoCoinState());
    }

    @Override
    public void dispense() {
        System.out.println("please push button to get candy");
    }
}

// 糖果出售状态
class SoldState implements State {
    private CandyMachine machine;

    public SoldState(CandyMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("solding, can't insert");
    }

    @Override
    public boolean pushButton() {
        System.out.println("solding, can't push again");
        return false;
    }

    @Override
    public void returnCoin() {
        System.out.println("solding, can't return");
    }

    @Override
    public void dispense() {
        System.out.println("dispensed candy to you, please pick it up");
        machine.setCount(machine.getCount() - 1);
        if (machine.getCount() == 0) {
            machine.setState(machine.getSoldOutState());
        } else {
            machine.setState(machine.getNoCoinState());
        }
    }
}

// 糖果售完状态
class SoldOutState implements State {
    private CandyMachine machine;

    public SoldOutState(CandyMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("candy is sold out, returned coin to you");
    }

    @Override
    public boolean pushButton() {
        System.out.println("please insert coin");
        return false;
    }

    @Override
    public void returnCoin() {
        System.out.println("you haven't insert coin, can't return");
    }

    @Override
    public void dispense() {
        System.out.println("please push button to get candy");
    }
}


