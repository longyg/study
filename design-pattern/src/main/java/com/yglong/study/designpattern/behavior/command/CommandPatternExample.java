package com.yglong.study.designpattern.behavior.command;

/**
 * 命令模式
 * <p>
 * 将不同动作的执行封装成不同的命令
 */
public class CommandPatternExample {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        Light light = new Light();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        remoteControl.setOnCommands(0, lightOnCommand, lightOffCommand);

        remoteControl.onButtonWasPressed(0);
        remoteControl.offButtonWasPressed(0);

        remoteControl.onButtonWasPressed(1);
        remoteControl.offButtonWasPressed(1);
    }
}

class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private static final Command No_Command = new NoCommand();

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        for (int i = 0; i < 7; i++) {
            onCommands[i] = No_Command;
            offCommands[i] = No_Command;
        }
    }

    public void setOnCommands(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPressed(int slot) {
        onCommands[slot].execute();
    }

    public void offButtonWasPressed(int slot) {
        offCommands[slot].execute();
    }
}

interface Command {
    void execute();
}

class NoCommand implements Command {
    @Override
    public void execute() {

    }
}

class Light {
    void on() {
        System.out.println("Light is on");
    }

    ;

    void off() {
        System.out.println("Light is off");
    }
}

class LightOnCommand implements Command {
    // 接收者
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}


