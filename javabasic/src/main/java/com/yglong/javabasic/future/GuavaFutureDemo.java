package com.yglong.javabasic.future;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaFutureDemo {
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

    static class MainJob implements Runnable {
        boolean waterOk = false;
        boolean washOk = false;
        int gap = SLEEP_GAP / 10;

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(gap);
                    System.out.println("reading book...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (waterOk && washOk) {
                    drinkTea(waterOk, washOk);
                }
            }
        }

        public void drinkTea(Boolean waterOk, Boolean washOk) {
            if (waterOk && washOk) {
                System.out.println("drinking tea");
                this.waterOk = false;
                this.gap = SLEEP_GAP * 100;
            } else if (!waterOk) {
                System.out.println("hot water failed");
            } else {
                System.out.println("wash cup failed");
            }
        }
    }

    public static void main(String[] args) {
        MainJob mainJob = new MainJob();
        Thread mainThread = new Thread(mainJob);
        mainThread.setName("main thread");
        mainThread.start();

        Callable<Boolean> hotWaterJob = new HotWaterJob();
        Callable<Boolean> washJob = new WashJob();

        ExecutorService jPool = Executors.newFixedThreadPool(10);
        ListeningExecutorService gPool = MoreExecutors.listeningDecorator(jPool);
        ListenableFuture<Boolean> hotFuture = gPool.submit(hotWaterJob);
        Futures.addCallback(hotFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    mainJob.waterOk = true;
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("hot water failed");
            }
        }, gPool);

        ListenableFuture<Boolean> washFuture = gPool.submit(washJob);
        Futures.addCallback(washFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    mainJob.washOk = true;
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("wash is failed");
            }
        }, gPool);
    }
}
