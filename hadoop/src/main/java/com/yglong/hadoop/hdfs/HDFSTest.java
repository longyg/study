package com.yglong.hadoop.hdfs;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;

/**
 * HDFS API 测试
 * 注意：
 *     - 如果要访问 HDFS 集群，需要将集群的配置文件拷贝到classpath中（如src/main/resources目录下）
 *     - 配置文件包括：core-site.xml, hdfs-site.xml
 */
public class HDFSTest {
    public static void main(String[] args) throws Exception {
        uploadFile();
        createFile();
        createDir();
        fileRename();
        deleteFile();
        readFile();
        isFileExists();
        fileLastModified();
        fileLocation();
        getNode();
    }

    static FileSystem getFileSystem() throws Exception {
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(conf);
        return fileSystem;
    }

    public static void uploadFile() throws Exception {
        FileSystem hdfs = getFileSystem();
        Path src = new Path("C:\\ylong\\workspace\\study\\hadoop\\example_data\\hdfs\\test.txt");
        Path dst = new Path("/");
        FileStatus files[] = hdfs.listStatus(dst);
        for (FileStatus file : files) {
            System.out.println(file.getPath());
        }
        System.out.println("----------------------------");
        hdfs.copyFromLocalFile(src, dst);
        files = hdfs.listStatus(dst);
        for (FileStatus file : files) {
            System.out.println(file.getPath());
        }
    }

    public static void createFile() throws Exception {
        FileSystem hdfs = getFileSystem();
        byte[] buff = "Hello Hadoop".getBytes();
        Path dst = new Path("/test_create.txt");
        FSDataOutputStream outputStream = hdfs.create(dst);
        outputStream.write(buff, 0, buff.length);
        outputStream.close();
    }

    public static void createDir() throws Exception {
        FileSystem hdfs = getFileSystem();
        Path dfs = new Path("/TestDir/test");
        boolean isCreated = hdfs.mkdirs(dfs);
        System.out.println("Is Created:" + isCreated);
    }

    public static void fileRename() throws Exception {
        FileSystem hdfs = getFileSystem();
        Path from = new Path("/test_create.txt");
        Path to = new Path("/test_modified.txt");
        boolean isRename = hdfs.rename(from, to);
        System.out.println("Rename result: " + isRename);
    }

    public static void deleteFile() throws Exception {
        FileSystem hdfs = getFileSystem();
        Path del = new Path("/TestDir");
        boolean isDeleted = hdfs.delete(del, true);
        System.out.println("Delete result: " + isDeleted);
    }

    public static void readFile() throws Exception {
        FileSystem hdfs = getFileSystem();
        FSDataInputStream inputStream = hdfs.open(new Path("/test_modified.txt"));
        IOUtils.copyBytes(inputStream, System.out, 1024, false);
        IOUtils.closeStream(inputStream);
    }

    public static void isFileExists() throws Exception {
        FileSystem hdfs = getFileSystem();
        Path f = new Path("/test1");
        boolean isExists = hdfs.exists(f);
        System.out.println("/test1 dir exists or not: " + isExists);
    }

    public static void fileLastModified() throws Exception {
        FileSystem hdfs = getFileSystem();
        Path path = new Path("/test_modified.txt");
        FileStatus fileStatus = hdfs.getFileStatus(path);
        long modTime = fileStatus.getModificationTime();
        System.out.println("Modify time: " + modTime);
    }

    public static void fileLocation() throws Exception {
        FileSystem hdfs = getFileSystem();
        Path f = new Path("/test_modified.txt");
        FileStatus fileStatus = hdfs.getFileStatus(f);
        BlockLocation[] blockLocations = hdfs.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
        int blockLen = blockLocations.length;
        for (int i = 0; i < blockLen; i++) {
            String[] hosts = blockLocations[i].getHosts();
            String hostStr = StringUtils.join(hosts, ", ");
            System.out.println("block_" + i + "_location: " + hostStr);
        }
    }

    public static void getNode() throws Exception {
        FileSystem fs = getFileSystem();
        DistributedFileSystem hdfs = (DistributedFileSystem) fs;
        DatanodeInfo[] datanodeInfos = hdfs.getDataNodeStats();
        for (int i = 0; i < datanodeInfos.length; i++) {
            System.out.println("DataNode_" + i + "_Name: " + datanodeInfos[i].getHostName());
        }
    }
}
