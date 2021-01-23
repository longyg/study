package com.yglong.javabasic.future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class JavaFutureDemo {

    public static final int SLEEP_GAP = 500;

    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("wash shuihu");
                System.out.println("add water");
                System.out.println("fire");
                Thread.sleep(SLEEP_GAP);
                System.out.println("water is hot");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    static class WashJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("wash chahu");
                System.out.println("wash cup");
                System.out.println("pick chaye");
                Thread.sleep(SLEEP_GAP);
                System.out.println("wash is done");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public static void drinkTea(boolean waterOk, boolean cupOk) {
        if (waterOk && cupOk) {
            System.out.println("drink tea");
        } else if (!waterOk) {
            System.out.println("hot water failed");
        } else if (!cupOk) {
            System.out.println("wash cup failed");
        }
    }

    public static void main(String[] args) {
        Callable<Boolean> hJob = new HotWaterJob();
        FutureTask<Boolean> hTask = new FutureTask<>(hJob);

        Thread hThread = new Thread(hTask, "** hot_water-Thread");

        Callable<Boolean> washJob = new WashJob();
        FutureTask<Boolean> washTask = new FutureTask<>(washJob);

        Thread washThread = new Thread(washTask, "$$ wash-Thread");

        hThread.start();
        washThread.start();

        Thread.currentThread().setName("main thread");
        try {
            Boolean waterOk = hTask.get();
            Boolean washOk = washTask.get();
            drinkTea(waterOk, washOk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(getCurrentThreadName() + " finished.");
    }
}
