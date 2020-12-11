package FileIO.NIO;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * Created by Defias on 2020/07.
 * Description: ByteBuffer 字节存放次序
 *
 * ByteBuffer 是一高位优先的形式存放数据的，并且数据在网上传输时也常常使用高位优先的形式
 *
 * 通过ByteBuffer的order方法可改变字节排序方式
 *  ByteOrder.BIG_ENDIAN
 *  ByteOrder.LITTLE_ENDIAN
 *
 */
public class TestNIOBuffer3 {

    public static void main(String[] args) {
        //test0();
        test1();
    }


    public static void test0() {
        ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));

        bb.rewind();
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));

        bb.rewind();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
    }

    public static void test1() {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        System.out.println(cb.rewind());

        symmetricScramble(cb);
        System.out.println(cb.rewind());

        symmetricScramble(cb);
        System.out.println(cb.rewind());
    }


    //交换相邻字符
    private static void symmetricScramble(CharBuffer buffer){
        while(buffer.hasRemaining()) {
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }
}
