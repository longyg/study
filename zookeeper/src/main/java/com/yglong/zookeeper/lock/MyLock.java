package com.yglong.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyLock {
    private static final String CONNECTION_STR = "192.168.56.101:2181,192.168.56.102:2181,192.168.56.103:2181";
    private ZooKeeper zookeeper;
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private static final String LOCK_ROOT_PATH = "/Locks";
    private static final String LOCK_NODE = "Lock_";
    private String path;

    private Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeDeleted) {
                synchronized (watcher) {
                    System.out.println("获取锁");
                    notifyAll();
                }
            }
        }
    };

    public MyLock() throws Exception {
        init();
    }

    private void init() throws Exception {
        zookeeper = new ZooKeeper(CONNECTION_STR, 50000, event -> {
            if (event.getType() == Watcher.Event.EventType.None) {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    System.out.println("与Zookeeper服务器建立连接成功");
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        checkAndCreateRootNode();
    }

    // 获取锁
    public void acquire() throws Exception {
        createLockNode();
        attemptLock();
    }

    // 创建锁
    public void createLockNode() throws Exception {
        path = zookeeper.create(LOCK_ROOT_PATH + "/" + LOCK_NODE, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    // 尝试获取锁
    public void attemptLock() throws Exception {
        List<String> locks = zookeeper.getChildren(LOCK_ROOT_PATH, false);
        Collections.sort(locks);
        System.out.println(path);
        System.out.println(locks);
        String sub = path.substring(path.lastIndexOf("/") + 1);
        System.out.println(sub);
        int index = locks.indexOf(sub);
        if (index == 0) {
            System.out.println("获取锁成功");
            return;
        } else if (index == -1 ){
            throw new Exception("获取锁出现异常");
        } else {
            Stat stat = zookeeper.exists(LOCK_ROOT_PATH + "/" + locks.get(index - 1), watcher);
            if (null == stat) {
                attemptLock();
            } else {
                synchronized (watcher) {
                    System.out.println("等待锁");
                    watcher.wait();
                }
            }
        }
    }

    // 释放锁
    public void release() throws Exception {
        if (null != path) {
            System.out.println("释放锁");
            zookeeper.delete(path, -1);
            path = null;
        }
    }

    private void checkAndCreateRootNode() throws Exception {
        Stat stat = zookeeper.exists(LOCK_ROOT_PATH, false);
        if (null == stat) {
            String path = zookeeper.create(LOCK_ROOT_PATH, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            if (null == path) {
                throw new Exception("Create lock root path failed");
            }
        }
    }

    public static void main(String[] args) {
        String path = "/Locks/Lock_001";
        System.out.println(path.replace("/Locks", ""));
        String sub = path.substring(path.indexOf(LOCK_ROOT_PATH));
        System.out.println(sub);
    }
}
