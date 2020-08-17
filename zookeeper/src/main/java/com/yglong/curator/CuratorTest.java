package com.yglong.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorTest {
    private static final String CONNECTION_STR = "192.168.56.101:2181,192.168.56.102:2181,192.168.56.103:2181";

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECTION_STR, retryPolicy);
        client.start();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    InterProcessMutex lock = new InterProcessMutex(client, "/curator/lock");
                    lock.acquire();
                    System.out.println("获取到锁");
                    for (int i1 = 0; i1 < 10; i1++) {
                        System.out.println(i1);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.release();
                    System.out.println("释放了锁");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
