package FileIO;
import java.io.*;
/**
 * Created by Defias on 2016/2/29.
 *
 * 随机访问文件
 *
 * RandomAccessFile类（自我独立的类）实现了DataInput和DataOutput接口、并且增加了支持随机访问的方法
 *
 * Java.io包提供了RandomAccessFile类(Object类的直接子类)用于随机文件的创建和访问。使用这个类，可以跳转到文件的任意位置读写数据。
 * 程序可以在随机文件中插入数据，而不会破坏该文件的其他数据
 *
 * public class RandomAccessFile extends Object implements  DataOutput, DataInput, Closeable
 *
 * 构造方法：
 * public RandomAccessFile(String name, String mode)	使用指定的文件名字符串和模式创建RandomAccessFile流
 * public RandomAccessFile (File file, String mode)	使用指定的File对象和模式创建RandomAccessFile流
 *
 * 两种模式：
 * r  数据流是只读的
 * rw  数据流是可读写的
 *
 * 常用方法：
 * void close()  关闭流并且释放相关资源
 * long getFilePointer()  返回以字节计算的从文件开始的偏移量，下一个read或write将从该位置进行
 * long length()			返回文件中的字节数
 * void seek(long pos)	移动文件位置指示器，pos指定从文件开头的偏离字节数，下一个read或write将从该位置进行
 * void setLength(long newLength)  为该文件设置一个新的长度
 * int skipBytes(int n)	跳过n个字节，返回数为实际跳过的字节数
 * int read()			从文件中读取一个字节的数据，字节的高24位为0，若遇到文件结尾，返回-1
 * int read(byte[] b)    从文件中读取b.length个字节数据到一个字节数组中
 * int read(byte[] b, int off, int len)  从文件中读取len个字节数据到一个字节数组中，off为偏移量
 * final byte readByte()	从文件中读取带符号的字节值
 * final char readChar()	从文件中读取一个Unicode字符
 * void write(byte[] b)  从指定的字节数组中写b.length个字节到该文件中
 * void write(byte[] b, int off, int len)  从指定的字节数组中写len个字节到该文件中，off为偏移量
 * final void writeChar(inte c)	写入一个字符（两个字节组成）
 */



public class TestIORandomAccessFile {
	static String file = "base/src/testrf";

	public static void main(String args[]) throws IOException{
		test0();
		//test1();
		//test2();
	}

	public static void test0() throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "rw");
		for(int i = 0; i < 7; i++)
			rf.writeDouble(i*1.414);
		rf.writeUTF("The end of the file");
		rf.close();
		display();

		rf = new RandomAccessFile(file, "rw");
		rf.seek(5*8);
		rf.writeDouble(47.0001);
		rf.close();
		display();
	}

	public static void test1() throws IOException {
		RandomAccessFile inout = new RandomAccessFile("base/src/inout.dat", "rw");
		// Clear the file to destroy the old contents if exists
		inout.setLength(0);

		for (int i = 0; i < 200; i++)
			inout.writeInt(i);

		System.out.println("Current file length is " + inout.length());
		inout.seek(0); // Move the file pointer to the beginning
		System.out.println("The first number is " + inout.readInt());


		inout.seek(1 * 4); // Move the file pointer to the second number
		System.out.println("The second number is " + inout.readInt());


		inout.seek(9 * 4); // Move the file pointer to the tenth number
		System.out.println("The tenth number is " + inout.readInt());

		// Modify the eleventh number
		inout.writeInt(555);

		// Append a new number
		inout.seek(inout.length()); // Move the file pointer to the end
		inout.writeInt(999);
		System.out.println("The new length is " + inout.length());

		inout.seek(10 * 4); // Move the file pointer to the eleventh number
		System.out.println("The eleventh number is " + inout.readInt());

		inout.close();
	}


	static void display() throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "r");
		for(int i = 0; i < 7; i++)
			System.out.println(
					"Value " + i + ": " + rf.readDouble());
		System.out.println(rf.readUTF());
		rf.close();
	}
}