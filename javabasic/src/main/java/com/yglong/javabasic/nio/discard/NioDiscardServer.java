package com.yglong.javabasic.nio.discard;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NioDiscardServer {
    public static void startServer() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8080));
        System.out.println("服务器启动成功");
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("连接建立: " + socketChannel.getRemoteAddress());
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(2048);
                    int length = 0;
                    while ((length = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println("收到：" + new String(buffer.array(), 0, length));
                        buffer.clear();
                    }
                    socketChannel.close();
                }
                iterator.remove();
            }
        }
        serverSocketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }
}
