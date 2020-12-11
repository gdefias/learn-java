package FileIO.NIO;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;
/**
 * Created by Defias on 2016/5/9.
 *
 * Buffer操作

 Buffer的实现：
 ByteBuffer   ---和通道FileChannel直接交互的只有ByteBuffer
 CharBuffer
 IntBuffer
 DoubleBuffer
 FloatBuffer
 LongBuffer
 ShortBuffer
 MappedBytesBuffer  一般用于和内存映射


 Buffer的常用方法：
 static ByteBuffer	allocate(int capacity)  分配一个新的字节缓冲区（JVM中） capacity--新缓冲区的容量，以字节为单位

 static ByteBuffer	allocateDirect(int capacity)  分配新的直接字节缓冲区（JVM之外，也就是系统级的内存分配，
 使用allocateDirect要注意内存溢出问题）

 flip()：反转缓冲区，将缓冲区从写模式切换到读模式，它将limit设置为当前position，然后将position设置为0，如果已定义了标记，则丢弃该标记
 public final Buffer flip() {
     limit = position;
     position = 0;
     mark = -1;
     return this;
 }

 clear()：重设/清空缓冲区以便接收更多的字节，它将limit设置为与capacity相同，设置position为0
 public final Buffer clear() {
     position = 0;
     limit = capacity;
     mark = -1;
     return this;
 }

 rewind()：读写模式下都可用，它只单纯的将当前位置置0，同时取消mark标记，仅此而已
 public final Buffer rewind() {
     position = 0;
     mark = -1;
     return this;
 }

 mark()：在此缓冲区的位置设置标记
 标记为当前的position（mark=position）

 reset():将此缓冲区的位置重置为以前标记的位置
 恢复mark的位置（position=mark）

 equals()：判断两个buffer是否相等，允许不同的Buffer对象进行比较
 相等需满足：类型相同、且buffer中剩余字节数相同、且所有剩余字节相等
 从上面的三个条件可以看出，equals只比较buffer中的部分内容，并不会去比较每一个元素，实际上是比较两个缓冲区中每个缓冲区position到
 limit之间(不包括limit)的缓冲值

 compareTo()：
 compareTo比较两个buffer，限制相同类型的Buffer对象进行比较



 ByteBuffer API:
 abstract  ByteBuffer compact()  压缩此缓冲区（可选操作）
 将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，设置成capacity
 现在Buffer准备好写数据了，但是不会覆盖未读的数据

 static ByteBuffer	wrap(byte[] array)  将byte数组包装到缓冲区中

 static ByteBuffer	wrap(byte[] array, int offset, int length)  将byte数组包装到缓冲区中

 abstract  ByteBuffer	slice()  创建新的字节缓冲区，其内容是此缓冲区内容的共享子序列

 byte[]	array() 返回实现此缓冲区的byte数组（可选操作）

 int arrayOffset()   返回此缓冲区中的第一个元素在缓冲区的底层实现数组中的偏移量（可选操作）
 */


public class TestNIOBuffer {
	public static void main(String args[]) throws IOException {
        //test0();
        test00();
        //test1();
        //test2();
	}

	//字符集转换
    public static void test0() throws IOException{
        final int BSIZE=1024;

        String DEFAULT_CHARSET = Charset.defaultCharset().name();
        System.out.println(DEFAULT_CHARSET);

        //通过包装的方法创建的缓冲区保留了被包装数组内保存的数据
        ByteBuffer bb = ByteBuffer.wrap("你好".getBytes("UTF-8"));
        CharBuffer cb = bb.asCharBuffer();
        System.out.println(cb);

        bb = ByteBuffer.wrap("你好".getBytes("UTF-16BE"));
        cb = bb.asCharBuffer();
        System.out.println(cb);

        bb=ByteBuffer.wrap("你好".getBytes("UTF-8"));
        Charset cs = Charset.forName("UTF-8");
        cb = cs.decode(bb);
        System.out.println(cb);

        cs = Charset.forName("GBK");
        bb = cs.encode("你好");  //转换为当前字符编码
        cb = cs.decode(bb);  //当前字符编码转换为Unicode字符编码
        for(int i=0;i<cb.limit();i++)
            System.out.print(cb.get());

        System.out.print("\n----------------------------------\n");
    }

    public static void test00() {
        SortedMap<String,Charset> charSets = Charset.availableCharsets();
        Iterator<String> it = charSets.keySet().iterator();

        while(it.hasNext()) {
            String csName = it.next();
            System.out.print(csName);
            Iterator aliases = charSets.get(csName).aliases().iterator();
            if(aliases.hasNext())
                System.out.print(": ");

            while(aliases.hasNext()) {
                System.out.print(aliases.next());
                if(aliases.hasNext())
                    System.out.print(", ");
            }
            System.out.println();
        }
    }

    //ByteBuffer
    public static void test1() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 看一下初始时4个核心变量的值
        System.out.println("初始时-->limit--->"+byteBuffer.limit());
        System.out.println("初始时-->position--->"+byteBuffer.position());
        System.out.println("初始时-->capacity--->"+byteBuffer.capacity());
        System.out.println("初始时-->mark--->" + byteBuffer.mark());

        System.out.println("--------------------------------------");

        // 添加一些数据到缓冲区中
        String s = "Java3y";
        byteBuffer.put(s.getBytes());

        // 看一下初始时4个核心变量的值
        System.out.println("put完之后-->limit--->"+byteBuffer.limit());
        System.out.println("put完之后-->position--->"+byteBuffer.position());
        System.out.println("put完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("put完之后-->mark--->" + byteBuffer.mark());
    }

    //IntBuffer
    public static void test2() throws IOException {
        IntBuffer intBuffer = IntBuffer.allocate(2);  //分配2字节内存给Buffer
        System.out.println("\tCapacity: " + intBuffer.capacity());
        System.out.println("\tPosition: " + intBuffer.position());
        System.out.println("\tLimit: " + intBuffer.limit());
        sleep();

        intBuffer.put(12345678);  //put方法将int值写入Buffer
        intBuffer.put(2);
        System.out.println("\tCapacity: " + intBuffer.capacity());
        System.out.println("\tPosition: " + intBuffer.position());
        System.out.println("\tLimit: " + intBuffer.limit());
        sleep();

        intBuffer.flip();  //flip方法将buffer转换为读模式
        System.out.println("\tCapacity: " + intBuffer.capacity());
        System.out.println("\tPosition: " + intBuffer.position());
        System.out.println("\tLimit: " + intBuffer.limit());
        sleep();

        //每当调用一次get方法读取数据时, buffer的读指针都会向前移动一个单位长度(在这里是一个int长度)
        System.out.println(intBuffer.get());
        System.out.println(intBuffer.get());
        System.out.println("\tCapacity: " + intBuffer.capacity());
        System.out.println("\tPosition: " + intBuffer.position());
        System.out.println("\tLimit: " + intBuffer.limit());
        System.out.println("----------------");

        IntBuffer intBuffer2 = IntBuffer.allocate(2);
        intBuffer2.put(1);
        intBuffer2.put(2);
        System.out.println("position: " + intBuffer2.position());

        //rewind()方法可以重置position的值为0, 因此可以重新读取/写入Buffer了
        //如果是读模式,则重置的是读模式的position, 如果是写模式, 则重置的是写模式的position
        intBuffer2.rewind();
        System.out.println("position: " + intBuffer2.position());
        intBuffer2.put(1);
        intBuffer2.put(2);
        System.out.println("position: " + intBuffer2.position());


        intBuffer2.flip();
        System.out.println("position: " + intBuffer2.position());
        intBuffer2.get();
        intBuffer2.get();
        System.out.println("position: " + intBuffer2.position());

        intBuffer2.rewind();
        System.out.println("position: " + intBuffer2.position());
        System.out.println("----------------");


        IntBuffer intBuffer3 = IntBuffer.allocate(2);
        intBuffer3.put(11111);
        intBuffer3.put(22222);
        intBuffer3.flip();
        System.out.println(intBuffer3.get());
        System.out.println("position: " + intBuffer3.position());

        //通过调用Buffer.mark()将当前的position的值保存起来, 随后可以通过调用Buffer.reset()方法将position的值回复回来
        intBuffer3.mark();
        System.out.println(intBuffer3.get());

        System.out.println("position: " + intBuffer3.position());
        intBuffer3.reset();
        System.out.println("position: " + intBuffer3.position());
        System.out.println(intBuffer3.get());
        System.out.println("----------------");


        IntBuffer intBuffer4 = IntBuffer.allocate(2);
        intBuffer4.flip();
        System.out.println("position: " + intBuffer4.position());
        System.out.println("limit: " + intBuffer4.limit());
        System.out.println("capacity: " + intBuffer4.capacity());

        //这里不能读, 因为limit == position == 0, 没有数据，读取会报错java.nio.BufferUnderflowException
        //System.out.println(intBuffer.get());

        intBuffer4.clear();
        System.out.println("position: " + intBuffer4.position());
        System.out.println("limit: " + intBuffer4.limit());
        System.out.println("capacity: " + intBuffer4.capacity());

        //这里可以读取数据了, 因为clear后, limit == capacity == 2, position == 0,即使我们没有写入任何的数据到buffer中.
        System.out.println(intBuffer4.get()); //读取到0
        System.out.println(intBuffer4.get()); //读取到0
        System.out.println("----------------");
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
