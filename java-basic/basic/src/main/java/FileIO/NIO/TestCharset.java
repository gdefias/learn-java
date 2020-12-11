package FileIO.NIO;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

/**
 * Created by Defias on 2020/08.
 * Description: Charset
 */

public class TestCharset {
    public static void main(String[] args) throws CharacterCodingException,UnsupportedEncodingException {
//        test0();
        test1();
        test2();

    }

    public static void test0()  {
        // 获取Java支持的全部字符集
        SortedMap<String,Charset> map = Charset.availableCharsets();
        for (String alias : map.keySet()) {
            // 输出字符集的别名
            System.out.println(alias);
        }

    }

    public static void test1() throws UnsupportedEncodingException {
        Charset charset = Charset.forName("utf8");
        System.out.println(charset.name());

        //返回一个包含该字符的别名，字符集的别名是不可变的
        Set<String> set = charset.aliases();
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("---编码---");
        ByteBuffer buffer = charset.encode("abcdef");
        System.out.println(buffer);

        System.out.println("缓冲区剩余的元素数: "+buffer.remaining());
        while(buffer.hasRemaining()) {
            System.out.print((char)buffer.get());
        }

        System.out.println("\n---解码---");
        byte[] bytedata = "abcdef".getBytes("utf8");
        System.out.println(bytedata);

        // 创建一个ByteBuffer对象
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put(bytedata);
        byteBuffer.flip();

        System.out.println(charset.decode(byteBuffer));

        System.out.println("\n-----------------------------\n");
    }

    //编码器和解码器
    public static void test2() throws CharacterCodingException,UnsupportedEncodingException {
        System.out.println("---编码---");
        // 创建简体中文对应的Charset
        Charset charset = Charset.forName("GBK");

        // 获取charset对象对应的编码器
        CharsetEncoder charsetEncoder = charset.newEncoder();

        // 创建一个CharBuffer对象
        CharBuffer charBuffer = CharBuffer.allocate(20);
        charBuffer.put("CCTV-电视台");
        charBuffer.flip();

        // 将CharBuffer中的字符序列转换成字节序列
        ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);

        // 循环访问ByteBuffer中的每个字节
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.print(byteBuffer.get(i) + " ");
        }

        System.out.println("\n---解码---");
        // 获取charset对象对应的编码器
        CharsetDecoder charsetDecoder = charset.newDecoder();

        // 创建一个ByteBuffer对象
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(50);
        byteBuffer2.put("CCTV-电视台".getBytes("GBK"));
        byteBuffer2.flip();

        // 将ByteBuffer的数据解码成字符序列
        System.out.println(charsetDecoder.decode(byteBuffer));
    }
}
