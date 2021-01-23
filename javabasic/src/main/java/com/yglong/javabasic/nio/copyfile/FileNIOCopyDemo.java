package com.yglong.javabasic.nio.copyfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNIOCopyDemo {

    public static void main(String[] args) throws Exception {
        nioCopyFileDemo();
    }

    public static void nioCopyFileDemo() throws Exception {
        nioCopyFile(FileNIOCopyDemo.class.getResource("/src/main/resources/test.txt").getPath(),
                FileNIOCopyDemo.class.getResource("/").getPath() + "test_copy.txt");

    }

    public static void nioCopyFile(String src, String dest) throws Exception {
        File srcFile = new File(src);
        File destFile = new File(dest);

        if (!srcFile.exists()) {
            throw new Exception("src file does not exist: " + src);
        }

        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        long startTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(srcFile);
        FileChannel inChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream(destFile);
        FileChannel outChannel = fos.getChannel();

        int length = -1;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while ((length = inChannel.read(buffer)) != -1) {
            System.out.println("读取字节数：" + length);
            buffer.flip();  // 把buffer切换为读取模式
            int outlength = 0;

            while ((outlength = outChannel.write(buffer)) != 0) {
                System.out.println("写入字节数：" + outlength);
            }
            buffer.clear(); // 把buffer切换为写入模式
        }
        outChannel.force(true);

        outChannel.close();
        fos.close();
        inChannel.close();
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("文件复制消耗的毫秒数：" + (endTime - startTime));
    }
}

