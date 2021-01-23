package com.yglong.javabasic.nio.sendfile;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NioSendClient {
    public static void main(String[] args) throws Exception {
        NioSendClient client = new NioSendClient();
        client.sendFile();
    }

    public void sendFile() throws Exception {
        String srcPath = NioSendClient.class.getResource("/src/main/resources/test.txt").getPath();
        System.out.println(srcPath);
        String destFile = "test_receive.txt";
        File srcFile = new File(srcPath);
        if (!srcFile.exists()) {
            throw new Exception("文件不存在：" + srcPath);
        }

        FileChannel fileChannel = new FileInputStream(srcFile).getChannel();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));
        socketChannel.configureBlocking(false);

        while (!socketChannel.finishConnect()) {
        }
        System.out.println("连接服务器成功");

        ByteBuffer fileNameBuffer = StandardCharsets.UTF_8.encode(destFile);
        // 发送文件名称
        socketChannel.write(fileNameBuffer);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 发送文件长度
        buffer.putLong(srcFile.length());
        buffer.flip(); // 切换成读模式
        socketChannel.write(buffer);
//        socketChannel.write(StandardCharsets.UTF_8.encode(Long.toString(srcFile.length())));

        buffer.clear(); // 切换成写模式

        // 发送文件内容
        int length = 0;
        long progress = 0;
        while ((length = fileChannel.read(buffer)) > 0) {
            buffer.flip(); // 切换成读模式
            socketChannel.write(buffer);
            buffer.clear(); // 切换成写模式
            progress += length;
            System.out.println("| " + progress + " | " + (100 * progress / srcFile.length()) + "% |");
        }
        if (length == -1) {
            fileChannel.close();
            socketChannel.shutdownOutput();
            socketChannel.close();
        }
        System.out.println("文件传输成功");
    }
}
