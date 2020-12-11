package FileIO;
import FileIO.Char.TestIOReader;
/**
 * Created by Defias on 2017/2/24.
 *
 *
 * 过滤器数据流：使用过滤器类可以读取整数值、双精度值和字符串，而不是字节或字符
 *
 * FilterInputStream和FilterOutputStream类是过滤数据的基类
 * 需要处理基本数值类型时就用其派生类DataInputStream和DataOutputStream来过滤字节
 *
 *
 * * DataInputStream
 * * 从数据流读取字节，并将它们转换为合适的基本类型或字符串。扩展FilterInputStream类并实现DataInput接口
 * * DataInputStream(InputStream in);  //通过指定的输入流创建DataInputStream输入流
 * *
 * * DataOutputStream
 * * 将基本类型值或字符串转换为字节，并将字节输出到数据流。扩展FilterOutputStream类并实现DataOutput接口
 * * DataOutputStream(OutputStream out);  //通过指定的输出流创建DataOutputStream输出流
 *
 *
 * java.io.DataInput接口
 * -----------------------------------------------------
 * +readBoolean(): boolean     从输入流中读取一个boolean值
 * +readByte(): byte         从输入流中读取一个byte值
 * +readChar(): char        从输入流中读取一个字符
 * +readFloat(): float      从输入流中读取一个float值
 * +readDouble(): double    从输入流中读取一个double值
 * +readInt(): int      从输入流中读取一个int值
 * +readLong(): long     从输入流中读取一个long值
 * +readShort(): short      从输入流中读取一个short值
 * +readLine(): String      从输入流中读取一行字符
 * +readUTF(): String      以UTF格式读取一个字符串
 *
 * java.io.DataOutput接口
 * -----------------------------------------------------
 * +writeBoolean(b: boolean): void     向输出流中写一个boolean值
 * +writeByte(v: int): void        向输出流中写参数v的8位低位比特
 * +writeBytes(s: String): void    向输出流中写一个字符串中字符的低位字节
 * +writeChar(c: char): void       向输出流中写一个字符（由两个字节组成）
 * +writeChars(s: String): void    向输出流中依次写一个字符串s中的每个字符，每个字符2个字节
 * +writeFloat(v: float): void     向输出流中写一个float值
 * +writeDouble(v: double): void   向输出流中写一个double值
 * +writeInt(v: int): void     向输出流中写一个int值
 * +writeLong(v: long): void       向输出流中写一个long值
 * +writeShort(v: short): void     向输出流中写一个short值
 * +writeUTF(s: String): void      以UTF格式写一个字符串s
 *
 */
import java.io.*;

public class TestIODataStream {
    public static void main(String[] args) throws IOException {
      //test1();
      test2();
      //test3();
      //test4();
    }

    public static void test1() throws IOException {
        DataOutputStream output = new DataOutputStream(new FileOutputStream("base/src/temp.dat"));
        // Write student test scores to the file
        output.writeUTF("John");
        output.writeDouble(85.51);
        output.writeUTF("Jim");
        output.writeDouble(185.51);
        output.writeUTF("George");
        output.writeDouble(105.251);
        output.close();
        System.out.print("----------------------------------\n");

        DataInputStream input = new DataInputStream(new FileInputStream("base/src/temp.dat"));
        System.out.println(input.readUTF() + " " + input.readDouble());
        System.out.println(input.readUTF() + " " + input.readDouble());
        System.out.println(input.readUTF() + " " + input.readDouble());
        System.out.print("----------------------------------\n");


        DataInputStream in = new DataInputStream(
                new ByteArrayInputStream(
                        TestIOReader.read("base/src/temp.dat").getBytes()));
        while(in.available() != 0) //下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
            System.out.print((char)in.readByte());  //一次一个字节的读取字符

    }


    public static void test2() throws IOException {
        //写入
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("base/src/Data.txt")));
        out.writeDouble(3.14159);
        out.writeUTF("That was pi");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        out.close();

        //读出
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("base/src/Data.txt")));
        System.out.println(in.readDouble());
        // Only readUTF() will recover the
        // Java-UTF String properly:
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
    }

    //检测文件末尾
    public static void test3() {
        try {
            DataOutputStream output = new DataOutputStream(new FileOutputStream("base/src/test.dat"));
            output.writeDouble(4.5);
            output.writeDouble(43.25);
            output.writeDouble(3.2);
            output.close();

            DataInputStream input = new DataInputStream(new FileInputStream("base/src/test.dat"));
            while (true) {
                System.out.println(input.readDouble());
            }
        }
        catch (EOFException ex) {  //如果到达文件的末尾还继续从中读取数据，会发生EOFException异常，可以使用该异常检查是否达到文件末尾
            System.out.println("All data read");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //将三个int型数字10，0，-100 写入数据文件中
    public static void test4() {
        String fileName= "base/src/tests.bin";
        int value1=10,value2=0,value3=-100;
        try {
            //将DataOutputStream与FileOutputStream连接输出不同类型的数据
            DataOutputStream a = new DataOutputStream(new FileOutputStream(fileName));
            a.writeInt(value1);
            a.writeInt(value2);
            a.writeInt(value3);
            a.close();
        }
        catch(IOException i) {
            System.out.println("出现错误!"+fileName);
        }

        //读取数据文件tests.bin中的三个int型数字，求和并显示
        int sum=0;
        try {
            DataInputStream b = new DataInputStream(new FileInputStream(fileName));
            sum+=b.readInt();
            sum+=b.readInt();
            sum+=b.readInt();
            System.out.println("三个数的和为："+sum);
            b.close();
        }
        catch(IOException e){
            System.out.println("出现错误！"+fileName);
        }
    }
}
