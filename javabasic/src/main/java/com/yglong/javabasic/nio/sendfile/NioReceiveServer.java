package com.yglong.javabasic.nio.sendfile;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NioReceiveServer {

    static class Client {
        String filename;
        long fileLength;
        long startTime;
        InetSocketAddress remoteAddress;
        FileChannel outChannel;
    }

    private ByteBuffer buffer = ByteBuffer.allocate(2048);
    private Map<SelectableChannel, Client> clientMap = new HashMap<>();
    private String RECEIVE_PATH = "/";

    public void startServer() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");

//        File directory = new File(NioReceiveServer.class.getResource(RECEIVE_PATH).getPath());
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//        String fullname = directory.getAbsolutePath() + File.separator + "test_receive.txt";
//        FileChannel fileChannel = new FileOutputStream(new File(fullname)).getChannel();

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = server.accept();
                    if (null == socketChannel) continue;
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    Client client = new Client();
                    client.remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
                    clientMap.put(socketChannel, client);
                    System.out.println("建立连接：" + socketChannel.getRemoteAddress());
                } else if (key.isReadable()) {
//                    SocketChannel socketChannel = (SocketChannel) key.channel();
//                    buffer.clear();
//                    int length = 0;
//                    while ((length = socketChannel.read(buffer)) > 0) {
//                        buffer.flip();
//                        System.out.println("接收到：" + length);
//                        fileChannel.write(buffer);
//                        buffer.clear();
//                    }
                    processData(key);
                }
                iterator.remove();
            }
        }
    }

    private void processData(SelectionKey key) throws IOException {
        Client client = clientMap.get(key.channel());
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int num = 0;
        buffer.clear(); // 清空buffer，切换为写入模式
        while ((num = socketChannel.read(buffer)) > 0) {
            System.out.println("收到数据:" + num);
            buffer.flip(); // 切换为读取模式
            if (null == client.filename) {
                // 根据发送过来的文件名，创建文件通道并保存
                String filename = StandardCharsets.UTF_8.decode(buffer).toString();
                File directory = new File(NioReceiveServer.class.getResource(RECEIVE_PATH).getPath());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                client.filename = filename;
                String fullname = directory.getAbsolutePath() + File.separator + filename;
                System.out.println("NIO 传输目标文件：" + fullname);
                File file = new File(fullname);
                client.outChannel = new FileOutputStream(file).getChannel();
            } else if (0 == client.fileLength) {
                // 记录发送的文件长度
                client.fileLength = buffer.getLong();
//                client.fileLength = Long.parseLong(StandardCharsets.UTF_8.decode(buffer).toString());
                client.startTime = System.currentTimeMillis();
                System.out.println("NIO 传输开始，文件大小为：" + client.fileLength);


                // 有可能存在粘包
                System.out.println(buffer.position() + " - " + buffer.capacity() + " - " + buffer.limit());
                if (buffer.position() != buffer.limit()) {
                    System.out.println("收到文件内容：\n" + new String(buffer.array(), buffer.position(), num));
                    System.out.println("写入文件...");
                    client.outChannel.write(buffer);
                }
                System.out.println(buffer.position() + " - " + buffer.capacity() + " - " + buffer.limit());

            } else {
                // 将发送的内容写入文件
                System.out.println("收到：" + new String(buffer.array(), 0, num));
                System.out.println("写入文件...");
                client.outChannel.write(buffer);
            }
            buffer.clear(); // 清空buffer，切换为写入模式
        }
        key.cancel();

        if (num == -1) {
            client.outChannel.close();
            System.out.println("传输完毕");
            key.cancel();
            System.out.println("文件接收成功, 文件名：" + client.filename);
            System.out.println("大小：" + client.fileLength);
            long endTime = System.currentTimeMillis();
            System.out.println("NIO IO传输毫秒数：" + (endTime - client.startTime));
        }
    }

    public static void main(String[] args) throws IOException {
        new NioReceiveServer().startServer();
    }
}
