package com.yglong.javabasic.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class UDPClient {
    public void send() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);
        System.out.println("UDP客户端启动成功");
        System.out.println("请输入内容:");
        while (scanner.hasNext()) {
            String next = scanner.next();
            buffer.put((System.currentTimeMillis() + " >> " + next).getBytes());
            buffer.flip();
            datagramChannel.send(buffer, new InetSocketAddress("localhost", 8080));
            buffer.clear();
        }
        datagramChannel.close();
    }

    public static void main(String[] args) throws IOException {
        new UDPClient().send();
    }
}
