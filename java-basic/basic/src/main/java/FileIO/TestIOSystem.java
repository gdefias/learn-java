package FileIO;

import java.io.*;

/**
 * Created by Defias on 2020/07.
 * Description: 标准IO  System
 *
 * System.out和System.err已经事先被包装成了PrintStream(一个OutputStream)
 * 但System.in却是一个未经包装过的InputStream
 *
 * System提供静态方法对标准输入、输出、错误IO流进行重定向
 * setIn(InputStream)
 * setOut(PrintStream)
 * setErr(PrintStream)
 */
public class TestIOSystem {
    public static void main(String[] args) throws IOException {
        //test1();
        test2();
        //test3();
    }

    public static void test1() throws IOException {
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in));
        String s;
        while((s = stdin.readLine()) != null && s.length()!= 0)
            System.out.println(s);
        // An empty line or Ctrl-Z terminates the program
    }

    //Turn System.out into a PrintWriter
    public static void test2() {
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello, world");
    }


    public static void test3() throws IOException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入数据：");
            String s = in.readLine();
            RandomAccessFile myFile = new RandomAccessFile("base/src/testr.log","rw");
            myFile.seek(myFile.length());  //移动到文件结尾
            myFile.writeBytes(s+"\n");  //写入数据
            myFile.close();
            System.out.println("数据已经追加到文件中");
        }
        catch(IOException e) {
            System.out.println("找不到该文件！");
        }
    }

    public static void test4() throws IOException {
        InputStream standardIn = System.in;
        PrintStream standardOut = System.out;
        PrintStream standardErr = System.err;

        InputStream in = new BufferedInputStream(new FileInputStream("base/src/testR.txt"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("base/src/outR.txt")));
        PrintStream err = new PrintStream(new BufferedOutputStream(new FileOutputStream("base/src/errR.txt")));

        redirect(in,out,err);  //把输入、输出、错误流重定向到in、out、err
        copy();  //拷贝

        in.close();
        out.close();
        err.close();

        redirect(standardIn,standardOut,standardErr);  //把输入、输出、错误流重定向到standardIn、standardOut、standardErr
        copy();  //把从键盘输入的数据输出到控制台
    }



    public static void redirect(InputStream in, PrintStream out, PrintStream err){
        System.setIn(in);
        System.setOut(out);
        System.setErr(err);
    }


    public static void copy() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        String data;
        while((data=br.readLine())!=null && data.length()!=0) {
            System.out.println(data);  //向标准输出流写数据
            System.err.println(data);  //向标准错误输出流写数据
        }
    }
}
