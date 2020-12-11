package FileIO;

import java.io.*;

/**
 * Created by Defias on 2016/2/29.
 *
 * java.io.FileDescriptor
 * 文件描述符: 表示打开的文件、套接字等
 * 该类不能实例化，其中包含三个静态成员：in、out和err，分别对应于标准输入流、标准输出和标准错误
 *
 * ByteArrayInputStream
 * 字节数组输入流： 从字节数组中读取字节流数据
 *
 * ByteArrayOutputStream
 * 字节数组输出流：以数组的形式获取写入到该输出流中
 *
 * StringBufferInputStream
 * 已过时了，InputStream的子类，读取的字节由字符串的内容提供的输入流
 */


public class TestIOByteArrayStream {
	public static void main(String[] args) throws IOException {
        //testFile();
        //testPicFile();
        //testByteArray();
        testStringBuffer();
        //testSequence();
	}

	public static void testFile() {
        try {
            //接收输入并写到文件
            FileInputStream a = new FileInputStream(FileDescriptor.in);  //从标准输入创建二进制文件输入流
            FileOutputStream b = new FileOutputStream("base/src/testfile");  //创建二进制文件输出流
            System.out.println("请输入字符，以#号结束：");
            char ch;
            while((ch=(char)a.read()) != '#') {  //读
                b.write(ch);  //写
            }
            a.close();
            b.close();
            System.out.println();

            //显示文件的所有内容
            FileInputStream c = new FileInputStream("base/src/testfile");
            FileOutputStream d = new FileOutputStream(FileDescriptor.out);
            int data;
            while(c.available()>0) {
                data = c.read();
                d.write(data);
            }
            c.close();
            d.close();
            System.out.println("---------------------------");
        }
        catch(FileNotFoundException e){
            System.out.println("找不到该文件！");
        }
        catch(IOException e){}
    }

    public static void testPicFile() {
        try {
            //对二进制图形文件（.jpg）备份
            FileInputStream ga = new FileInputStream("base/src/test.jpg");
            FileOutputStream gb = new FileOutputStream("base/src/test_a.jpg");
            System.out.println("文件的大小为："+ga.available());
            byte[] gc = new byte[ga.available()];
            ga.read(gc);  //将图形文件读入到数组
            gb.write(gc);  //将数组中的数据写入到新文件中
            System.out.println("文件已经被更名复制！");
            ga.close();
            gb.close();
        }
        catch(FileNotFoundException e){
            System.out.println("找不到该文件！");
        }
        catch(IOException e){}
    }


    public static void testByteArray() {
        try {
            //ByteArrayInputStream
            byte[] buff = new byte[]{2,15,37,-1,-9,9};
            ByteArrayInputStream in = new ByteArrayInputStream(buff,1,4);
            int data;
            data = in.read();
            while(data != -1){
                System.out.print(data+" ");
                data = in.read();
            }
            in.close();

            //ByteArrayOutputStream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write("你好abc".getBytes("UTF-8"));  //写  public byte[] getBytes(String charsetName) throws UnsupportedEncodingException
            byte[] bufff = out.toByteArray(); //获得字节数组
            out.close();

            ByteArrayInputStream inn = new ByteArrayInputStream(bufff);
            int len = inn.available();
            byte[] buffIn = new byte[len];
            inn.read(buffIn);  //读
            inn.close();
            System.out.println(new String(buffIn, "UTF-8"));
            System.out.println("---------------------------");

        }
        catch(FileNotFoundException e){
            System.out.println("找不到该文件！");
        }
        catch(IOException e){}

    }


    public static void testByteArray2() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out,true);  //通过PrintStream写字符串
        ps.print("好");
        ps.close();

        byte[] buff = out.toByteArray(); //获得字节数组
        System.out.println("采用本地操作系统的默认字符编码：");
        readBuff(buff);

        out = new ByteArrayOutputStream();
        DataOutputStream ds = new DataOutputStream(out);  //通过DataOutputStream写字符串
        ds.writeUTF("好");
        ds.close();

        buff = out.toByteArray(); //获得字节数组
        System.out.println("采用适用于Java语言的UTF-8字符编码：");
        readBuff(buff);

    }

    private static void readBuff(byte[] buff) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(buff);
        int data;
        while ((data = in.read()) != -1)
            System.out.print(data + " ");
        System.out.println();
        in.close();
    }

    public static void testStringBuffer() {
        try {
            //StringBufferInputStream
            StringBufferInputStream in2 = new StringBufferInputStream("abcd1好朋友电");
            int data;
            while((data = in2.read())!=-1)
                System.out.print (data+" ");
            in2.close();
            System.out.println("---------------------------");

        }
        catch(FileNotFoundException e){
            System.out.println("找不到该文件！");
        }
        catch(IOException e){}

    }
}
