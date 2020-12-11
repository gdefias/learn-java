package FileIO.NIO;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
/**
 * Created by Defias on 2016/5/9.
 *
 * 读写文件操作
 */



public class TestNIOFileRW {
	public static void main(String args[]) throws IOException {
		final int BSIZE=1024;

		//向文件中写数据
		FileChannel fc = new FileOutputStream("base/src/testnio.txt").getChannel();
		fc.write(ByteBuffer.wrap("你好,".getBytes()));
		fc.close();

		//向文件末尾添加数据
		fc = new RandomAccessFile("base/src/testnio.txt","rw").getChannel();
		fc.position(fc.size());  //定位到文件末尾
		fc.write(ByteBuffer.wrap("朋友!".getBytes()));
		fc.close();

		//读数据
		fc = new FileInputStream("base/src/testnio.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);  //把文件中的数据读入到ByteBuffer中
		buff.flip();
		Charset cs= Charset.defaultCharset(); //获得本地平台的字符编码
		System.out.println(cs.decode(buff)); //转换为Unicode编码

		fc.close();
	}
}
