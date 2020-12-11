package FileIO.NIO;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
/**
 * Created by Defias on 2016/5/9.
 *
 内存映射文件 -- MappedByteBuffer
 用于创建和修改那些因为太大而不能放入内存的文件,
 映射文件的一块区域，完全把它当作非常大的数组来访问

 FileChannel API:
 abstract  MappedByteBuffer	map(FileChannel.MapMode mode, long position, long size)
 将此通道的文件区域直接映射到内存中

 FileChannel 嵌套类:
 static class FileChannel.MapMode  文件映射模式的类型安全的枚举
 static FileChannel.MapMode READ_ONLY   结果缓冲区是只读的，试图写会发生异常
 static FileChannel.MapMode READ_WRIT   结果缓冲区可写，改变会及时写回文件中，多个程序同时对同一个文件进行映射的具体行为依赖于
 									    操作系统
 static FileChannel.MapMode PRIVATE    结果缓冲区可写，但是改变是私有的，仅仅对缓冲区有效，不会写入文件


 MappedByteBuffer
 public abstract class MappedByteBuffer extends  ByteBuffer

 API:
 MappedByteBuffer	force()
 将此缓冲区所做的内容更改强制写入包含映射文件的存储设备中

 boolean isLoaded()
 判断此缓冲区的内容是否位于物理内存中

 MappedByteBuffer load()
 将此缓冲区内容加载到物理内存中
 */


public class TestNIOMappedByteBuffer {
	static int length = 0x000FFFF;

	public static void main(String args[])throws IOException {
		test1();
	}

	public static void test1() throws IOException {
		MappedByteBuffer out = new RandomAccessFile("base/src/testmap.dat", "rw").getChannel()
						.map(FileChannel.MapMode.READ_WRITE, 0, length);
		for(int i = 0; i < length; i++)
			out.put((byte)'x');
		System.out.println("Finished writing");
		for(int i = length/2; i < length/2 + 6; i++)
			System.out.print((char)out.get(i));
		System.out.println("---------------------------");

		FileChannel mf = new RandomAccessFile("base/src/testnio2.txt","rw").getChannel();
		MappedByteBuffer mb = mf.map(FileChannel.MapMode.READ_WRITE, 0, length);
		mb.put("你好帅".getBytes("GBK"));
		mb.flip();
		//System.out.println(Charset.forName("GBK").decode(mb));

		ByteBuffer buff = ByteBuffer.allocate(6);
		mf.read(buff);
		buff.flip();
		System.out.println(Charset.forName("GBK").decode(buff));
	}
}
