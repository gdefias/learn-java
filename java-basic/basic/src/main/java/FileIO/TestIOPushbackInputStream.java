package FileIO;

/**
 * Created by Defias on 2017/3/8.
 *
 * PushbackInputStream  回推流
 *
 * 缓存的新应用之一就是回推（pushback）的实现。回推用于输入流，以允许读取字节，然后再将它们返回（回推）到流中
 * PushbackInputStream类实现了这一思想，提供了一种机制，可以“偷窥”来自输入流的内容而不对它们进行破坏
 *
 *
 * 在JAVA IO中所有的数据都是采用顺序的读取方式，即对于一个输入流来讲都是采用从头到尾的顺序读取的，如果在输入流中某个不需要的内容被读取进来，
 * 则只能通过程序将这些不需要的内容处理掉，为了解决这样的处理问题，在JAVA中提供了一种回退输入流（PushbackInputStream、PushbackReader），
 * 可以把读取进来的某些数据重新回退到输入流的缓冲区之中
 *
 * 注：PushbackInputStream对象会使得InputStream对象（用于创建PushbackInputStream对象）的mark()或reset()方法无效。对于准备使用mark()或reset()方法的任何流
 * 来说，都应当使用markSupported()方法进行检查
 *
 *
 * PushbackInputStream类的常用方法
 1、public PushbackInputStream(InputStream in) 构造方法 将输入流放入到回退流之中
 2、public int read() throws IOException   普通方法 读取数据
 3、public int read(byte[] b,int off,int len) throws IOException 普通方法 读取指定范围的数据
 4、public void unread(int b) throws IOException 普通方法 回退一个数据到缓冲区前面
 5、public void unread(byte[] b) throws IOException 普通方法 回退一组数据到缓冲区前面
 6、public void unread(byte[] b,int off,int len) throws IOException 普通方法 回退指定范围的一组数据到缓冲区前面
 */
/*

* */

import java.io.ByteArrayInputStream;
import java.io.PushbackInputStream;

public class TestIOPushbackInputStream {
    public static void main(String args[]) throws Exception {
        test0();
    }

    public static void test0() throws Exception {
        String str = "www.baidu.com" ;      //定义字符串
        PushbackInputStream push = null ;       //定义回退流对象
        ByteArrayInputStream bai = null ;       //定义内存输入流
        bai = new ByteArrayInputStream(str.getBytes()) ;    //实例化内存输入流
        push = new PushbackInputStream(bai) ;   //从内存中读取数据
        System.out.print("读取之后的数据为：") ;
        int temp = 0 ;
        while((temp=push.read()) != -1) {  //读取内容
            System.out.print((char)temp) ;  //输出内容
            if(temp=='.'){  //判断是否读取到了“.”
                push.unread(temp) ; //放回到缓冲区之中
                temp = push.read() ;    //再读一遍
                System.out.print("（退回"+(char)temp+"）") ;
            }
        }
    }

    public static void test1() throws Exception {
        String s = "abcdefg";
        /*
         * PushbackInputStream pbin = new PushbackInputStream(in)
         * 这个构造函数创建的对象一次只能回推一个字节
         */
        try (ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
             PushbackInputStream pbin = new PushbackInputStream(in)) {
            int n;
            while ((n = pbin.read()) != -1) {
                System.out.println((char) n);
                if('b' == n)
                    pbin.unread('U');
            }
        }
    }

    public static void test2() throws Exception {
        String s = "abcdef";
        /*
         * PushbackInputStream pbin = new PushbackInputStream(in,3)
         * 这个构造函数创建的对象一次可以回推一个缓存
         */
        try (ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
             PushbackInputStream pbin = new PushbackInputStream(in, 3)) {
            int n;
            byte[] buffer = new byte[3];
            while ((n = pbin.read(buffer)) != -1) {
                System.out.println(new String(buffer));
                System.out.println(n);
                if(new String(buffer).equals("abc"))
                    pbin.unread(new byte[]{'M','N','O'});
                buffer = new byte[3];
            }
        }
    }

    public static void test3() throws Exception {
        String s = "abcdef";
        /*
         * PushbackInputStream pbin = new PushbackInputStream(in,4)
         * 这个构造函数创建的对象一次可以回推一个缓存
         */
        try (ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
             PushbackInputStream pbin = new PushbackInputStream(in, 4)) {
            int n;
            byte[] buffer = new byte[4];
            while ((n = pbin.read(buffer)) != -1) {
                System.out.println(new String(buffer));
                System.out.println(n);
                //取回推缓存中的一部分数据
                if(new String(buffer).equals("abcd"))
                    pbin.unread(buffer,2,2);
                buffer = new byte[4];
            }
        }
    }
}
