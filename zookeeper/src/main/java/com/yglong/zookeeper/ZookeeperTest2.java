package com.yglong.zookeeper;

import org.apache.zookeeper.*;

public class ZookeeperTest2 implements Watcher {
    private static final int SESSION_TIMEOUT = 3000;
    public static ZooKeeper zooKeeper;
    public static void main(String[] args) {
        String path = "/zknode10";

        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", SESSION_TIMEOUT, new ZookeeperTest2());

            zooKeeper.exists(path, true);

            zooKeeper.create(path, "mycontent1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            Thread.sleep(3000);

            byte[] bytes1 = zooKeeper.getData(path, null, null);
            String result1 = new String(bytes1);
            System.out.println("Result: " + result1);

            zooKeeper.setData(path, "testSetData000".getBytes(), -1);

            byte[] bytes2 = zooKeeper.getData(path, null, null);
            String result2 = new String(bytes2);
            System.out.println("Result after modified: " + result2);

            Thread.sleep(3000);

            zooKeeper.delete(path, -1);
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.NodeCreated == event.getType()) {
                System.out.println("Node created success.");
                try {
                    zooKeeper.exists(event.getPath(), true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (Event.EventType.NodeDeleted == event.getType()) {
                try {
                    zooKeeper.exists(event.getPath(), true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Node deleted success.");
            } else if (Event.EventType.NodeDataChanged == event.getType()) {
                try {
                    zooKeeper.exists(event.getPath(), true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Node changed success.");
            }
        }
    }
}
