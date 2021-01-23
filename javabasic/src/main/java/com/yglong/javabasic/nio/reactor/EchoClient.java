package com.yglong.javabasic.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class EchoClient {

    public void send() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(8080));
        while (!socketChannel.finishConnect()) {
        }

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        startEchoThread(socketChannel, buffer);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            buffer.put(next.getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        socketChannel.close();
    }

    public void startEchoThread(SocketChannel channel, ByteBuffer buffer) {
        new Thread(() -> {
            try {
                while (true) {
                    int length = 0;
                    while ((length = channel.read(buffer)) > 0) {
                        System.out.println("服务端返回 >> " + new String(buffer.array(), 0, length));
                        buffer.clear();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        new EchoClient().send();
    }
}
