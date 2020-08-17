package com.yglong.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZkApiTest {
    private static final String CONNECTION_STR = "192.168.56.101:2181,192.168.56.102:2181,192.168.56.103:2181";
    private ZooKeeper zooKeeper;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Before
    public void before() {
        try {
            zooKeeper = new ZooKeeper(CONNECTION_STR, 50000, watchedEvent -> {
                if (watchedEvent.getType() == EventType.None) {
                    if (watchedEvent.getState() == KeeperState.SyncConnected) {
                        System.out.println("建立连接成功");
                        countDownLatch.countDown();
                    } else if (watchedEvent.getState() == KeeperState.AuthFailed) {
                        System.out.println("认证失败");
                    } else if (watchedEvent.getState() == KeeperState.Disconnected) {
                        System.out.println("断开连接");
                    } else if (watchedEvent.getState() == KeeperState.Expired) {
                        System.out.println("过期");
                    } else if (watchedEvent.getState() == KeeperState.Closed) {
                        System.out.println("客户端关闭");
                    }
                } else if (watchedEvent.getType() == EventType.NodeCreated) {
                    System.out.println("节点创建: " + watchedEvent.getPath());
                } else if (watchedEvent.getType() == EventType.NodeChildrenChanged) {
                    System.out.println("节点子节点变化: " + watchedEvent.getPath());
                } else if (watchedEvent.getType() == EventType.NodeDataChanged) {
                    System.out.println("节点数据变化：" + watchedEvent.getPath());
                } else if (watchedEvent.getType() == EventType.NodeDeleted) {
                    System.out.println("节点删除：" + watchedEvent.getPath());
                }
            });
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create1() {
        try {
            Stat stat = new Stat();
            String string = zooKeeper.create("/create", "node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, stat);
            System.out.println("Created: " + string);
            System.out.println(stat.getVersion());
            System.out.println(stat.getCzxid());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create2() {
        try {
            zooKeeper.create("/create/node1", "node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.Create2Callback() {
                @Override
                public void processResult(int rc, String path, Object ctx, String name, Stat stat) {
                    System.out.println(rc);
                    System.out.println(path);
                    System.out.println(ctx);
                    System.out.println(name);
                    System.out.println(stat.getVersion());
                }
            }, "This is context");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void set1() {
        try {
            Stat stat = zooKeeper.setData("/create", "create".getBytes(), -1);
            System.out.println("Updated:" + stat.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void set2() {
        try {
            zooKeeper.setData("/create/node1", "node11".getBytes(), -1, new AsyncCallback.StatCallback() {
                @Override
                public void processResult(int rc, String path, Object ctx, Stat stat) {
                    System.out.println(rc);
                    System.out.println(path);
                    System.out.println(ctx);
                    System.out.println(stat.getVersion());
                }
            }, "this is set context");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete1() {
        try {
            zooKeeper.delete("/create/node1", -1);
            System.out.println("Deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

     @Test
     public void get() {
        try {
            Stat stat = new Stat();
            byte[] data = zooKeeper.getData("/create/node1", true, stat);
            System.out.println(data);
            System.out.println(stat.getVersion());
            Thread.sleep(200000);
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

     @Test
     public void exist1() {
        try {
            Stat stat = zooKeeper.exists("/create", true);
            System.out.println(stat);
            Thread.sleep(200000);
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

     @Test
     public void exist2() {
        try {
            zooKeeper.exists("/create", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() == EventType.NodeCreated) {
                        System.out.println("节点创建: " + event.getPath());
                    } else if (event.getType() == EventType.NodeChildrenChanged) {
                        System.out.println("节点子节点变化: " + event.getPath());
                    } else if (event.getType() == EventType.NodeDataChanged) {
                        System.out.println("节点数据变化：" + event.getPath());
                    } else if (event.getType() == EventType.NodeDeleted) {
                        System.out.println("节点删除：" + event.getPath());
                    }
                    try {
                        zooKeeper.exists("/create", this);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, new AsyncCallback.StatCallback() {
                @Override
                public void processResult(int rc, String path, Object ctx, Stat stat) {
                    System.out.println(rc);
                    System.out.println(path);
                    System.out.println(ctx);
                    System.out.println(stat.getVersion());
                }
            }, "this is exist context");
            Thread.sleep(200000);
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

     @Test
     public void getChild() {
        try {
            List<String> children = zooKeeper.getChildren("/create", true);
            for (String child : children) {
                System.out.println(child);
            }
            Thread.sleep(200000);
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}
