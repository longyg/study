package com.yglong.javabasic.signal;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class SignalTest {
    public static void main(String[] args) {
        SignalHandler signalHandler = new SignalHandlerImpl();
        Signal.handle(new Signal("TERM"), signalHandler);
        Signal.handle(new Signal("INT"), signalHandler);
        new Thread(() -> {
            while(true) {
                System.out.println("thread is running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static class SignalHandlerImpl implements SignalHandler {

        @Override
        public void handle(Signal sig) {
            System.out.println("receive signal: " + sig.getName());
            System.exit(0);
        }
    }
}
