package com.yglong.zookeeper;

import org.apache.zookeeper.*;

public class ZookeeperTest implements Watcher {
    private static final int SESSION_TIMEOUT = 3000;
    public static ZooKeeper zooKeeper;
    public static void main(String[] args) {
        String path = "/mynode";

        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", SESSION_TIMEOUT, new ZookeeperTest());

            zooKeeper.exists(path, true);

            zooKeeper.create(path, "mycontent1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.NodeCreated == watchedEvent.getType()) {
                System.out.println("Node created success.");
                try {
                    zooKeeper.exists(watchedEvent.getPath(), true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
