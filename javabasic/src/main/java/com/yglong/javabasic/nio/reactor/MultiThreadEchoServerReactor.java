package com.yglong.javabasic.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadEchoServerReactor {
    ServerSocketChannel serverSocketChannel;
    AtomicInteger next = new AtomicInteger(0);
    Selector[] selectors = new Selector[3];
    SubReactor[] subReactors = null;

    MultiThreadEchoServerReactor() throws IOException {
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        SelectionKey sk = serverSocketChannel.register(selectors[0], SelectionKey.OP_ACCEPT);
        sk.attach(new AcceptorHandler());
        SubReactor subReactor1 = new SubReactor(selectors[0]);
        SubReactor subReactor2 = new SubReactor(selectors[1]);
        subReactors = new SubReactor[]{subReactor1, subReactor2};
    }

    public void startService() {
        for (SubReactor subReactor : subReactors) {
            new Thread(subReactor).start();
        }
    }

    class SubReactor implements Runnable {
        Selector selector;
        SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    // 这里必须要使用非阻塞的select方法，否则后面的客户端通道无法注册到selector上。
                    // 因为select和register方法都需要获取同一把锁：synchronized(this.publicKeys)
                    // 如果使用阻塞的select方法，在select线程等待的时候，这个锁不会被释放，导致执行register方法的线程无法获取锁。
                    // 因此需要使用非阻塞方法，让调用register方法的线程有机会获取到锁
                    if (selector.select(1000) > 0) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> it = selectionKeys.iterator();
                        while (it.hasNext()) {
                            SelectionKey sk = it.next();
                            dispath(sk);
                        }
                        selectionKeys.clear();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void dispath(SelectionKey sk) {
            Runnable handler = (Runnable) sk.attachment();
            if (null != handler) {
                handler.run();
            }
        }
    }

    class AcceptorHandler implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (null != socketChannel) {
                    System.out.println("连接建立：" + socketChannel.getRemoteAddress());
                    new MultiThreadEchoHandler(selectors[next.get()], socketChannel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (next.incrementAndGet() == selectors.length - 1) {
                next.set(0);
            }
        }
    }

    class MultiThreadEchoHandler implements Runnable {
        SocketChannel channel;
        SelectionKey sk;
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        int RECEIVING = 0, SENDING = 1;
        int state = RECEIVING;

        ExecutorService pool = Executors.newFixedThreadPool(4);

        MultiThreadEchoHandler(Selector selector, SocketChannel channel) throws IOException {
            System.out.println("注册：" + selector +   channel);
            this.channel = channel;
            this.channel.configureBlocking(false);
            sk = this.channel.register(selector, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
            System.out.println("registered");
        }

        @Override
        public void run() {
            pool.execute(new AsyncTask());
        }

        public synchronized void asyncRun() {
            try {
                if (state == SENDING) {
                    channel.write(buffer);
                    buffer.clear();
                    sk.interestOps(SelectionKey.OP_WRITE);
                    state = RECEIVING;
                } else if (state == RECEIVING) {
                    int length = 0;
                    while ((length = channel.read(buffer)) > 0) {
                        System.out.println(new String(buffer.array(), 0, length));
                    }
                    buffer.flip();
                    sk.interestOps(SelectionKey.OP_READ);
                    state = SENDING;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        class AsyncTask implements Runnable {

            @Override
            public void run() {
                MultiThreadEchoHandler.this.asyncRun();
            }
        }
    }



    public static void main(String[] args) throws IOException {
        new MultiThreadEchoServerReactor().startService();
    }
}


