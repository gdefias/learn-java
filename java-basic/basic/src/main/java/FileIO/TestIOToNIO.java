package FileIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Defias on 2020/07.
 * Description: 从java IO 到 java NIO
 *
 * 旧的IO类库中有三个类被修改了，用以产生NIO的FileChannel：
 * FileInputStream
 * FileOutputStream
 * RandomAccessFile
 *
 */
public class TestIOToNIO {
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws Exception {
        test1();

    }

    //文件读写
    public static void test1() throws Exception {
        FileChannel fc = new FileOutputStream("base/src/Data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();

        fc = new RandomAccessFile("base/src/Data2.txt", "rw").getChannel();
        fc.position(fc.size()); // Move to the end
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();

        fc = new FileInputStream("base/src/Data2.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        while(buff.hasRemaining())
            System.out.print((char)buff.get());
    }

    //文件拷贝
    public static void test2() throws Exception {
        FileChannel in = new FileInputStream("base/src/Data2.txt").getChannel();
        FileChannel out = new FileOutputStream("base/src/Data22.txt").getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        while(in.read(buffer) != -1) {
            buffer.flip(); // Prepare for writing
            out.write(buffer);
            buffer.clear();  // Prepare for reading
        }
    }

    //文件拷贝更好的方式
    public static void test3() throws Exception {
        FileChannel in = new FileInputStream("base/src/Data2.txt").getChannel();
        FileChannel out = new FileOutputStream("base/src/Data22.txt").getChannel();

        in.transferTo(0, in.size(), out);
        // Or:
        // out.transferFrom(in, 0, in.size());
    }
}
