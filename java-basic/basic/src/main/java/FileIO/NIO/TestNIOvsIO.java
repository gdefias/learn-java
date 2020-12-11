package FileIO.NIO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * Created by Defias on 2017/3/7.
 *
 * Java NIO VS IO 性能比较
 */

public class TestNIOvsIO {
    public static void main(String[] args) throws IOException {
        test0();
        //test1();
    }

    //NIO复制文件和传统IO复制文件的性能
    public static void test0() throws IOException {
        File sourse = new File("base/src/yellow-ball.gif");
        File des = new File("base/src/yellow-ball1.gif");
        File nio = new File("base/src/yellow-ball2.gif");

        long time = transferFile(sourse, des);
        System.out.println("普通字节流时间: " + time);

        long timeNio = transferFileWithNIO(sourse, nio);
        System.out.println("NIO时间: " + timeNio);
    }

    //不同方式读取文件并计算文件的CRC的性能对比(文件的CRC32：32位循环冗余校验和)
    public static void test1() throws IOException {
        String filename = "base/src/yellow-ball.gif";
        System.out.println("Input Stream:");
        long start = System.currentTimeMillis();
        long crcValue = checksumInputStream(filename);
        long end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");
        System.out.println("---------------------------");

        System.out.println("Buffered Input Stream:");
        start = System.currentTimeMillis();
        crcValue = checksumBufferedInputStream(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");
        System.out.println("---------------------------");

        System.out.println("Random Access File:");
        start = System.currentTimeMillis();
        crcValue = checksumRandomAccessFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");
        System.out.println("---------------------------");

        System.out.println("Mapped File:");
        start = System.currentTimeMillis();
        crcValue = checksumMappedFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");
        System.out.println("---------------------------");
    }

    public static long transferFile(File source, File des) throws IOException {
        long startTime = System.currentTimeMillis();

        if (!des.exists())
            des.createNewFile();

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des));

        //将数据源读到的内容写入目的地--使用数组
        byte[] bytes = new byte[1024 * 1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long transferFileWithNIO(File source, File des) throws IOException {
        long startTime = System.currentTimeMillis();

        if (!des.exists())
            des.createNewFile();

        RandomAccessFile read = new RandomAccessFile(source, "rw");
        RandomAccessFile write = new RandomAccessFile(des, "rw");

        FileChannel readChannel = read.getChannel();
        FileChannel writeChannel = write.getChannel();


        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);//1M缓冲区

        while (readChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        writeChannel.close();
        readChannel.close();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    //IO: FileInputStream
    public static long checksumInputStream(String filename) throws IOException {
        InputStream in = new FileInputStream(filename);
        CRC32 crc = new CRC32();

        int c;
        while((c = in.read()) != -1)
            crc.update(c);
        return crc.getValue();
    }

    //IO: BufferedInputStream
    public static long checksumBufferedInputStream(String filename) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(filename));
        CRC32 crc = new CRC32();

        int c;
        while((c = in.read()) != -1)
            crc.update(c);
        return crc.getValue();
    }

    //IO: RandomAccessFile
    public static long checksumRandomAccessFile(String filename) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filename, "r");
        long length = file.length();
        CRC32 crc = new CRC32();

        for (long p = 0; p < length; p++) {
            file.seek(p);
            int c = file.readByte();
            crc.update(c);
        }
        return crc.getValue();
    }

    //NIO
    public static long checksumMappedFile(String filename) throws IOException {
        FileInputStream in = new FileInputStream(filename);
        FileChannel channel = in.getChannel();

        CRC32 crc = new CRC32();
        int length = (int) channel.size();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);

        for (int p=0; p<length; p++) {
            int c = buffer.get(p);
            crc.update(c);
        }
        return crc.getValue();
    }
}
