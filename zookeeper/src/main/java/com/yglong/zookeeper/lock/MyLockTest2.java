package com.yglong.zookeeper.lock;

public class MyLockTest2 {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    MyLock lock = new MyLock();
                    lock.acquire();
                    for (int i1 = 0; i1 < 10; i1++) {
                        System.out.println(i1);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.release();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
