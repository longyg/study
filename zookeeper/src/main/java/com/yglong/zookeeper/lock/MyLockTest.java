package com.yglong.zookeeper.lock;

public class MyLockTest {

    public static void main(String[] args) throws Exception {
        MyLock lock = new MyLock();
        lock.acquire();
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            Thread.sleep(2000);
        }
        lock.release();
    }
}
