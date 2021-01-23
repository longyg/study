package com.yglong.javabasic.join;

public class JoinDemo {
    public static final int SLEEP_GAP = 50;

    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterThread extends Thread {
        public  HotWaterThread() {
            super("** HotWaterThread");
        }

        @Override
        public void run() {
            try {
                System.out.println("wash shui hu");
                System.out.println("add water");
                System.out.println("hot water");

                Thread.sleep(SLEEP_GAP);
                System.out.println("water is hot");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("hot water finished");
        }
    }

    static class WashThrad extends Thread {
        public  WashThrad() {
            super("** WashThrad");
        }

        @Override
        public void run() {
            try {
                System.out.println("wash cha hu");
                System.out.println("wash cup");
                System.out.println("pick cha ye");

                Thread.sleep(SLEEP_GAP);
                System.out.println("wash done");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("wash finished");
        }
    }

    public static void main(String[] args) {
        Thread hThread = new HotWaterThread();
        Thread wThread = new WashThrad();
        hThread.start();
        wThread.start();

        try {
            hThread.join();
            wThread.join();

            Thread.currentThread().setName("main thread");
            System.out.println("drink");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("finished");
    }
}
