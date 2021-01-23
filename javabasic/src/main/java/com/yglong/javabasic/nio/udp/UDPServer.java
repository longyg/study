package com.yglong.javabasic.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class UDPServer {
    public void receive() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(new InetSocketAddress("localhost", 8080));
        System.out.println("UDP服务器启动成功");
        Selector selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            SelectionKey key = iterator.next();
            if (key.isReadable()) {
                SocketAddress client = datagramChannel.receive(buffer);
                buffer.flip();
                System.out.println("收到" + client + ": " + new String(buffer.array(), 0, buffer.limit()));
                buffer.clear();
            }
            iterator.remove();
        }
        selector.close();
        datagramChannel.close();
    }

    public static void main(String[] args) throws IOException {
        new UDPServer().receive();
    }
}
