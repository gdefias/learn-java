package FileIO.NIO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
/**
 * Created by Defias on 2016/5/9.
 *
 * 读写Buffer
 */



public class TestNIOBuffer2 {
	private static final int BSIZE = 1024;

	public static void main(String args[]) throws IOException{

		//testBufferToText();

		//testGetData();

		//UseView();

		ViewBuffers();
	}

	public static void testBufferToText() throws IOException {
		FileChannel fc = new FileOutputStream("base/src/data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();

		fc = new FileInputStream("base/src/data2.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		buff.flip();
		// Doesn't work:
		System.out.println(buff.asCharBuffer());
		// Decode using this system's default Charset:
		buff.rewind();
		String encoding = System.getProperty("file.encoding");
		System.out.println("Decoded using " + encoding + ": "
				+ Charset.forName(encoding).decode(buff));
		System.out.println("---------------------------");

		// Or, we could encode with something that will print:
		fc = new FileOutputStream("base/src/data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
		fc.close();
		// Now try reading again:
		fc = new FileInputStream("base/src/data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
		System.out.println("---------------------------");

		// Use a CharBuffer to write through:
		fc = new FileOutputStream("base/src/data2.txt").getChannel();
		buff = ByteBuffer.allocate(24); // More than needed
		buff.asCharBuffer().put("Some text");
		fc.write(buff);
		fc.close();

		// Read and display:
		fc = new FileInputStream("base/src/data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
		System.out.println("---------------------------");
	}

	//从ByteBuffer插入和抽取各种不同类型的数据
	public static void testGetData() {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);

		// Allocation automatically zeroes the ByteBuffer:
		int i = 0;
		while(i++ < bb.limit())
			if(bb.get() != 0)
				System.out.println("nonzero");
		System.out.println("i = " + i);
		bb.rewind();

		// Store and read a char array:
		bb.asCharBuffer().put("Howdy!");
		char c;
		while((c = bb.getChar()) != 0)
			System.out.print(c + " ");
		System.out.println();
		bb.rewind();

		// Store and read a short:
		bb.asShortBuffer().put((short)471142);  //short需要强制类型转换
		System.out.println(bb.getShort());
		bb.rewind();

		// Store and read an int:
		bb.asIntBuffer().put(99471142);
		System.out.println(bb.getInt());
		bb.rewind();

		// Store and read a long:
		bb.asLongBuffer().put(99471142);
		System.out.println(bb.getLong());
		bb.rewind();

		// Store and read a float:
		bb.asFloatBuffer().put(99471142);
		System.out.println(bb.getFloat());
		bb.rewind();

		// Store and read a double:
		bb.asDoubleBuffer().put(99471142);
		System.out.println(bb.getDouble());
		bb.rewind();

		System.out.println("---------------------------");
	}


	//视图缓冲器
	//对视图的任何修改都会映射成对ByteBuffer中数据的修改
	public static void UseView() {
		ByteBuffer b1 = ByteBuffer.allocate(4);
		while(b1.hasRemaining())
			System.out.print(b1.get() + " ");
		System.out.println();
		b1.rewind(); //rewind()将position设回0，所以可以重读Buffer中的所有数据。limit保持不变，仍表示能从Buffer中读取多少个元素


		ByteBuffer bb = ByteBuffer.allocate(1024);
		//获得ByteBuffer的CharBuffer视图
		CharBuffer cbb = bb.asCharBuffer();
		cbb.put("你好帅");
		int len = 3;
		while (len>0) {
			System.out.print(bb.getChar());
			len--;
		}
		System.out.println();
		System.out.println("---------------------------");

		ByteBuffer bb2 = ByteBuffer.allocate(BSIZE);
		IntBuffer ib = bb2.asIntBuffer();
		// Store an array of int:
		ib.put(new int[]{ 11, 42, 47, 99, 143, 811, 1016 });

		// Absolute location read and write:
		System.out.println(ib.get(3));
		ib.put(3, 1811);
		// Setting a new limit before rewinding the buffer.
		ib.flip();
		while(ib.hasRemaining()) {
			int i = ib.get();
			System.out.print(i + " ");
		}

		System.out.println("\n---------------------------");
	}


	//将同一字节序列翻译成不同类型的数据
	public static void ViewBuffers() {
		ByteBuffer bb = ByteBuffer.wrap(new byte[]{ 0, 0, 0, 0, 0, 0, 0, 'a' });
		bb.rewind();
		System.out.print("Byte Buffer ");
		while(bb.hasRemaining())
			System.out.print(bb.position()+ " -> " + bb.get() + ", ");
		System.out.println();

		CharBuffer cb = ((ByteBuffer)bb.rewind()).asCharBuffer();
		System.out.print("Char Buffer ");
		while(cb.hasRemaining())
			System.out.print(cb.position() + " -> " + cb.get() + ", ");
		System.out.println();

		FloatBuffer fb = ((ByteBuffer)bb.rewind()).asFloatBuffer();
		System.out.print("Float Buffer ");
		while(fb.hasRemaining())
			System.out.print(fb.position()+ " -> " + fb.get() + ", ");
		System.out.println();

		IntBuffer ib = ((ByteBuffer)bb.rewind()).asIntBuffer();
		System.out.print("Int Buffer ");
		while(ib.hasRemaining())
			System.out.print(ib.position()+ " -> " + ib.get() + ", ");
		System.out.println();

		LongBuffer lb = ((ByteBuffer)bb.rewind()).asLongBuffer();
		System.out.print("Long Buffer ");
		while(lb.hasRemaining())
			System.out.print(lb.position()+ " -> " + lb.get() + ", ");
		System.out.println();

		ShortBuffer sb = ((ByteBuffer)bb.rewind()).asShortBuffer();
		System.out.print("Short Buffer ");
		while(sb.hasRemaining())
			System.out.print(sb.position()+ " -> " + sb.get() + ", ");
		System.out.println();

		DoubleBuffer db = ((ByteBuffer)bb.rewind()).asDoubleBuffer();
		System.out.print("Double Buffer ");
		while(db.hasRemaining())
			System.out.print(db.position()+ " -> " + db.get() + ", ");
	}
}
