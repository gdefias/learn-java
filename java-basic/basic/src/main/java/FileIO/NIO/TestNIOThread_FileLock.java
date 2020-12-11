package FileIO.NIO;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
/**
 * Created by Defias on 2016/5/9.
 *
 *
 * 锁 - FileLock - 进程间并发

 FileChannel API:
 FileLock lock()  获取对此通道的文件的独占锁定

 abstract  FileLock	lock(long position, long size, boolean shared)
 获取此通道的文件给定区域上的锁定
 shared的含义:是否使用共享锁,一些不支持共享锁的操作系统,将自动将共享锁改成排它锁
 共享锁: 共享读操作，但只能一个写
 排它锁: 读写同时只能有其中一个

 FileChannel中的lock()与tryLock()方法都是尝试去获取在某一文件上的独有锁，可以实现进程间操作的互斥
 区别在于: lock()会阻塞（blocking）方法的执行，tryLock()则不会

 如果进程在执行lock()或tryLock()后获取到独有锁（return a FileLock object），那么进程会一直持有该锁直到被释放，即：
 FileLock.release() 或 Channel.close() 或 JVM关闭

 如果进程PA持有独有锁：
    1、进程PB执行lock()获取独有锁，则lock()所在方法会一直阻塞，直到独有锁被进程PA释放
    2、进程PB执行tryLock()获取独有锁，则tryLock()会抛出异常java.io.IOException: fcntl failed: EAGAIN (Try again)异常


 抽象类FileLock  API：
 protected	FileLock(FileChannel channel, long position, long size, boolean shared)   构造方法，初始化此类的一个新实例

 FileChannel  channel()
 返回文件通道，此锁定保持在该通道的文件上

 boolean	isShared()
 判断此锁定是否为共享的

 boolean overlaps(long position, long size)
 判断此锁定是否与给定的锁定区域重叠
 返回值true表示当前锁在区域内,false表示当前锁的区域与参数区域不重叠

 boolean isValid()
 判断此锁定是否有效，当锁获取成功时，此值为true；如果锁被release或者fileChannle被关闭将返回false

 long	position()
 返回文件内锁定区域中第一个字节的位置

 abstract  void	release()
 释放此锁定

 long	size()
 返回锁定区域的大小，以字节为单位
 */


public class TestNIOThread_FileLock {
    static FileChannel fc;

	public static void main(String args[]) throws Exception {
		//test1();

		test2();
	}

	public static void test1() throws Exception {
		FileOutputStream fos = new FileOutputStream("base/src/testnio.txt");
		FileLock fl  = fos.getChannel().tryLock();   //获得一个独占锁，或者在无法获得锁的情况下返回null
		if (fl!=null) {
			System.out.println("Locked File");
			System.out.println(fl.isShared());
			Thread.sleep(3000);  //锁定文件3秒
			fl.release();
			System.out.println("Released Lock");
		}
        fos.close();
        System.out.println("---------------------------");
	}

    //对文件部分区域加锁并且修改文件
	public static void test2() throws Exception {
        final int capacity = 0x800;  //2K
        fc = new RandomAccessFile("base/src/test.txt","rw").getChannel();
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE,0,capacity);
        for (int i=0; i<capacity/2; i++) {
            mbb.put((byte)'a');
        }
        for (int i=capacity/2; i<capacity; i++) {
            mbb.put((byte)'c');
        }
        System.out.println("mbb： " + mbb);

        new ModifierThread(mbb,0, capacity/2);
        new ModifierThread(mbb,capacity/2, capacity);
    }


    public static class ModifierThread extends Thread {
        private ByteBuffer buff;
        private int start,end;

        ModifierThread(ByteBuffer mbb, int start, int end){
            this.start = start;
            this.end = end;
            mbb.limit(end);
            mbb.position(start);
            buff = mbb.slice();  //获得需要处理的缓冲区域，它是相应的文件区域映射
            start();
        }

        public void run() {
            try {
                //加锁
                FileLock fl = fc.lock(start, end,false);
                System.out.println("Locked: "+start+" to "+end);

                //修改数据
                while(buff.position() < buff.limit()-1)
                    buff.put((byte)(buff.get()+1));

                //释放锁
                fl.release();

                System.out.println(buff.toString());
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
