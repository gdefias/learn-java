package FileIO;
/**
 * Created by Defias on 2016/5/6.
 *
 * BufferedInputStream和BufferedOutputStream类可以通过减少磁盘读写次数来提供输入和输出的速度，
 * 所有方法都是从InputStream和OutputStream类继承的，没有引入新的方法
 *
 * 构造方法：
 * +BufferedInputStream(in: InputStream)  从一个InputStream对象创建一个BufferedInputStream
 * +BufferedInputStream(in: InputStream, bufferSize: int) 从一个InputStream对象创建一个BufferedInputStream，并指定缓冲区大小
 * +BufferedOutputStream(out: OutputStream)  从一个OutputStream对象创建一个BufferedOutputStream
 * +BufferedOutputStream(out: OutputStream, bufferSize: int) 从一个OutputStream对象创建一个BufferedOutputStream，并指定缓冲区大小
 */
import java.io.*;

public class TestIOBufferedStream {
	public static void main(String[] args) throws IOException {
        test1();
        test2();
	}

	public static void test1() throws IOException {
		FileOutputStream out1 = new FileOutputStream("base/src/test.txt");
		BufferedOutputStream out2 = new BufferedOutputStream(out1); //装饰文件输出流
		DataOutputStream out = new DataOutputStream(out2); //装饰带缓冲输出流
		out.writeByte(-12);
		out.writeLong(12);
		out.writeChar('1');
		out.writeUTF("好");
		out.close();

		InputStream in1 = new FileInputStream("base/src/test.txt");
		BufferedInputStream in2 = new BufferedInputStream(in1); //装饰文件输入流
		DataInputStream in = new DataInputStream(in2); //装饰缓冲输入流
		System.out.print(in.readByte()+" ");
		System.out.print(in.readLong()+" ");
		System.out.print(in.readChar()+" ");
		System.out.print(in.readUTF()+" ");
		in.close();
	}

	//文件拷贝
	public static void test2() throws IOException {
        String sfname = "base/src/testfile";
        String tfname = "base/src/testfilecopy";

        // Check if file exists
        File sourceFile = new File(sfname);
        if (!sourceFile.exists()) {
            System.out.println("Bytecode file " + sfname + " not exist");
            System.exit(0);
        }

        File targetFile = new File(tfname);
        if (targetFile.exists()) {
            System.out.println("Target file " + tfname + " already exists");
            System.exit(0);
        }

        BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile));
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(targetFile));

        int r; int numberOfBytesCopied = 0;
        while ((r = input.read()) != -1) {   //每次读一个字节
            output.write((byte)r);
            numberOfBytesCopied++;
        }

        input.close();
        output.close();

        System.out.println(numberOfBytesCopied + " bytes copied");
    }


    //读二进制文件
    public static byte[] read(File bFile) throws IOException{
        BufferedInputStream bf = new BufferedInputStream(
                new FileInputStream(bFile));
        try {
            byte[] data = new byte[bf.available()];
            bf.read(data);
            return data;
        } finally {
            bf.close();
        }
    }

    public static byte[] read(String bFile) throws IOException {
        return read(new File(bFile).getAbsoluteFile());
    }
}
