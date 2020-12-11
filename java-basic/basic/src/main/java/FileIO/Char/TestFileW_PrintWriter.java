package FileIO.Char;

/**
 * Created by Defias on 2017/2/24.
 *
 * PrintWriter  向文本文件写入数据
 java.io.PrintWriter
 -------------------------------------------------
 字段：
 protected Writer out    此PrintWriter的底层字符输出流

 构造器：
 PrintWriter(File file)     使用指定文件创建不具有自动行刷新的新PrintWriter
 PrintWriter(File file, String csn) 创建具有指定文件和字符集且不带自动刷行新的新 PrintWriter
 PrintWriter(OutputStream out)  根据现有的 OutputStream 创建不带自动行刷新的新 PrintWriter
 PrintWriter(OutputStream out, boolean autoFlush)   通过现有的 OutputStream 创建新的 PrintWriter
 PrintWriter(String fileName)   创建具有指定文件名称且不带自动行刷新的新 PrintWriter
 PrintWriter(String fileName, String csn)   创建具有指定文件名称和字符集且不带自动行刷新的新 PrintWriter
 PrintWriter(Writer out)    创建不带自动行刷新的新 PrintWriter
 PrintWriter(Writer out, boolean autoFlush)     创建新 PrintWriter

 部分方法：
 +print(s: String): void    将字符串写入文件中
 +print(c: char): void    将字符写入文件中
 +print(cArray: char[]): void    将字符数组写入文件中
 +print(i: int): void    将一个int值写入文件中
 +print(l: long): void    将一个long值写入文件中
 +print(f: float): void   将一个float值写入文件中
 +print(d: double): void   将一个double值写入文件中
 +print(b: boolean): void   将一个boolean值写入文件中
 void	write(char[] buf)   写入字符数组
 void	write(char[] buf, int off, int len) 写入字符数组的某一部分
 void	write(int c)    写入单个字符
 void	write(String s) 写入字符串
 void	write(String s, int off, int len)   写入字符串的某一部分
 */

import java.io.*;

public class TestFileW_PrintWriter {
    public static void main(String[] args) throws IOException {
//        test1();
        test2();
//        test3();
    }

    //文件读写（拷贝）
    public static void test1() throws IOException {
        String infile = "base/src/scores.txt";
        String outfile = "base/src/scores001.txt";
        BufferedReader in = new BufferedReader(
                new StringReader(TestIOReader.read(infile)));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(outfile)));

        int lineCount = 1;
        String s;
        while((s = in.readLine()) != null )
            out.println(lineCount++ + ": " + s);
        out.close();

        System.out.println(TestIOReader.read(outfile));
    }

    //更好的版本：PrintWriter中添加了辅助构造器，不必在每次创建PrintWriter对象时进行所有的装饰工作
    public static void test2() throws IOException {
        String infile = "base/src/scores.txt";
        String outfile = "base/src/scores001.txt";

        BufferedReader in = new BufferedReader(
                new StringReader(TestIOReader.read(infile)));

        PrintWriter out = new PrintWriter(outfile);
        int lineCount = 1;
        String s;
        while((s = in.readLine()) != null )
            out.println(lineCount++ + ": " + s);
        out.close();

        System.out.println(TestIOReader.read(outfile));
    }


    public static void test3() throws IOException {
        File file = new File("base/src/scores.txt");
        if (file.exists()) {
            System.out.println("File already exists");
            System.exit(0);
        }

        //使用java7中的异常处理机制try-with-resources语法自动关闭资源 括号中资源必须是AutoCloseable的子类型
        try (PrintWriter output = new PrintWriter(file)) {
            output.print("John T Smith, ");
            output.println(90);
            output.print("Eric K Jones123, ");
            output.println(85);
        }

        // Close the file
        //output.close();
    }
}
