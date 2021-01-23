package com.yglong.javabasic.nio.discard;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioDiscardClient {
    public static void startClient() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 8080));
        while (!socketChannel.finishConnect()) {}
        System.out.println("连接服务器成功");
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        byteBuffer.put("hello world".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.shutdownOutput();
        socketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        startClient();
    }
}
