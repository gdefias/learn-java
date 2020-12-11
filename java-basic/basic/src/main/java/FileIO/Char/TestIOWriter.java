package FileIO.Char;

/**
 * Created by Defias on 2016/2/29.
 *
 * 字符（文本）输出流
 */

import java.io.*;

public class TestIOWriter {
	public static void main(String[] args) {
        try{
            //FileWriter
            //FileWriter a = new FileWriter("new.txt");  //根据文件名创建输出流
            FileWriter a = new FileWriter("base/src/new123.txt", true);  //根据文件名创建输出流，第二个参数为true时数据将追加在文件后面
            for(int i=32; i<126; i++) {   //写入ASCⅡ为32~125的字符到文件中
                a.write(i);
            }
            String content = "\nThank you for your fish.\n";
            a.write(content);  //写入字符串
            a.write('A');  //写入字符
            a.write('\n');
            a.write('\n');
            a.close();
            System.out.println("---------------------------");

    		//BufferedWriter
    		File file = new File("base/src/new456.txt");
    		if (!file.exists()) {
    			file.createNewFile();
    		}
    		FileWriter fw = new FileWriter(file.getAbsoluteFile());  //getAbsoluteFile()返回文件绝对路径名
    		BufferedWriter bw = new BufferedWriter(fw);  //创建缓冲区文本输出流
    		//BufferedWriter bw = new BufferedWriter(fw, 1000);  //创建缓冲区文本输出流并设置缓冲区大小
    		bw.write(content);  //写入字符串
    		bw.newLine();  //写入换行符
    		bw.close();
            System.out.println("---------------------------");

            //CharArrayWriter
            CharArrayWriter writer = new CharArrayWriter();
            writer.write('你');
            writer.write('好');
            char[] buff = writer.toCharArray();  //字符数组输出流转为字符数组
            System.out.println(new String(buff));
            writer.close();
            System.out.println("---------------------------");

            //OutputStreamWriter
            OutputStreamWriter writer2 = new OutputStreamWriter(new FileOutputStream("base/src/new789.txt"), "UTF-8");
            writer2.write(content);
            writer2.close();
            System.out.println("---------------------------");


            //StringWriter
            String s = "Hello";
            StringWriter sw = new StringWriter();
            sw.write(s);
            sw.write(" World");
            System.out.println("" + sw.toString());
            System.out.println("---------------------------");


        } catch(IOException e) {
            System.out.println("IO Problem: " + e);
        }

	}
}
