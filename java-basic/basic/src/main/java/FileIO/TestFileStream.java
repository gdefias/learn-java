package FileIO;

/**
 * Created by Defias on 2017/2/24.

 * FileInputStream与FileOutputStream
 * FileInputStream和FileOutputStream类的所有方法都是从InputStream和OutputStream类继承的，没有引入新的方法
 *
 * FileInputStream的构造方法：
 * +FileInputStream(file: File)   从一个File对象创建一个FileInputStream
 * +FileInputStream(filename: String)    从一个文件名创建一个FileInputStream
 * （如果用一个不存在的文件创建FileInputStream对象，将发生异常：java.io.FileNotFoundException）
 *
 *
 * FileOutputStream的构造方法：
 * +FileOutputStream(file: File)   从一个File对象创建一个FileOutputStream
 * +FileOutputStream(filename: String)    从一个文件名创建一个FileOutputStream
 * +FileOutputStream(file: File, append: boolean)   从一个File对象创建一个FileOutputStream，如果append为true，数据将追加到已经存在的文件中
 * +FileOutputStream(filename: String, append: boolean)    从一个文件名创建一个FileOutputStream，如果append为true，数据将追加到已经存在的文件中
 * */

import java.io.*;

public class TestFileStream {
    public static void main(String[] args) throws IOException {
        FileOutputStream output = new FileOutputStream("base/src/temp.dat");

        for (int i = 1; i <= 10; i++)
            output.write(i);

        output.close();

        FileInputStream input = new FileInputStream("base/src/temp.dat");

        int value;
        while ((value = input.read()) != -1)  //读到-1意味着文件结束
            System.out.print(value + " ");

        input.close();
    }

}
