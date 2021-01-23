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

public class EchoServerReactor implements Runnable {
    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public EchoServerReactor() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new AcceptorHandler());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select(); // 会阻塞
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    SelectionKey sk = it.next();
                    dispatch(sk);
                }
                selected.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey sk) {
        Runnable handler = (Runnable) sk.attachment();
        if (handler != null) {
            handler.run();
        }
    }

    class AcceptorHandler implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    new EchoHandler(selector, socketChannel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class EchoHandler implements Runnable {
        SocketChannel channel;
        SelectionKey sk;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int RECEIVING = 0, SENDING = 1;
        int state = RECEIVING;

        EchoHandler(Selector selector, SocketChannel socketChannel) throws IOException {
            channel = socketChannel;
            channel.configureBlocking(false);
            sk = channel.register(selector, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        @Override
        public void run() {
            try {
                if (state == SENDING) {
                    channel.write(buffer);
                    buffer.clear();
                    sk.interestOps(SelectionKey.OP_READ);
                    state = RECEIVING;
                } else if (state == RECEIVING) {
                    int length = 0;
                    while ((length = channel.read(buffer)) > 0) {
                        System.out.println("收到客户端 >> " + new String(buffer.array(), 0, length));
                    }
                    buffer.flip();
                    sk.interestOps(SelectionKey.OP_WRITE);
                    state = SENDING;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerReactor()).start();
    }
}
