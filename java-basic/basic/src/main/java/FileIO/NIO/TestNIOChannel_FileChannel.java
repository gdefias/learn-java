package FileIO.NIO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * Created by Defias on 2017/3/8.
 *
 * FileChannel
 *
 * 一个连接到文件的通道。可以通过文件通道读写文件
 * FileChannel不可以设置为非阻塞模式，他只能在阻塞模式下运行
 *
 * public final FileChannel getChannel() {
 *     synchronized (this) {
 *         if (channel == null) {
 *         channel = FileChannelImpl.open(fd, path, true, rw, this);
 *         }
 *         return channel;
 *     }
 *  }  //由于synchronized ，并发时只有一个线程能够初始化FileChannel
 *
 *
 * FileChannel部分方法：
 * abstract long transferFrom(ReadableByteChannel src, long position, long count)
 * 将字节从给定的可读取字节通道传输到此通道的文件中
 * 从源通道中最多读取count个字节，并将其写入到此通道的文件中从给定position处开始的位置
 * 参数position和count表示目标文件的写入位置和最多写入的数据量
 *
 * abstract long transferTo(long position, long count, WritableByteChannel target)
 * 将字节从此通道的文件传输到给定的可写入字节通道
 * 读取从此通道的文件中给定position处开始的count个字节，并将其写入目标通道
 *
 * abstract FileChannel	truncate(long size)
 * 将此通道的文件截取为给定大小
 *
 * abstract void force(boolean metaData)
 * 强制将所有对此通道的文件更新写入包含该文件的存储设备中
 * 会把所有未写磁盘的数据都强制写入磁盘。这是因为在操作系统中出于性能考虑会把数据放入缓冲区，所以不能保证数据在调用write写入文件通道
 * 后就及时写到磁盘上了，除非手动调用force方法。force方法需要一个布尔参数，代表是否把meta data也一并强制写入
 *
 * abstract long size()
 * 返回此通道的文件的当前大小
 */



public class TestNIOChannel_FileChannel {
    public static void main(String[] args) throws IOException {
        test0();

        //test1();

        //test2();

        //test3
    }

    public static void test0() throws IOException {
        //基本使用
        RandomAccessFile aFile = new RandomAccessFile("base/src/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);  //分配48字节的Buffer

        int bytesRead = inChannel.read(buf);   //读取数据写到Buffer中，返回写了多少数据
        while (bytesRead!= -1) {
            System.out.println("Read " + bytesRead);

            buf.flip();//反转buffer
            while (buf.hasRemaining()) {  //hasRemaining： 是否读已经达到缓冲区的上界
                System.out.print((char) buf.get()); //一次读一个字节
            }
            buf.clear();  //清除buffer

            //继续从Channel读取数据写到Buffer中
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }


    public static void test1() throws IOException {
        //把test.txt文件中的数据拷贝到out.txt中
        FileChannel in = new FileInputStream("base/src/outnio.txt").getChannel();
        FileChannel out = new FileOutputStream("base/src/outnio.txt").getChannel();

        final int BSIZE = 1024;
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        while(in.read(buff) != -1) {   //buff为写模式（从in中读取到然后写入到buff中）
            buff.flip();
            out.write(buff);  //buff为读模式（从buff中读取到然后写入out中）
            buff.clear();
        }
        in.close();
        out.close();

    }

    public static void test2() throws IOException {
        //transferFrom
        RandomAccessFile fromFile = new RandomAccessFile("base/src/fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("base/src/toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
    }


    public static void test3() throws IOException {
        //transferTo
        RandomAccessFile fromFile = new RandomAccessFile("base/src/fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("base/src/toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }
}
