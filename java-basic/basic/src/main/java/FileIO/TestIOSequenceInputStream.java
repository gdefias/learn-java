package FileIO;

import java.io.*;

/**
 * Created by Defias on 2020/07.
 * Description:  SequenceInputStream  合并流
 *
 * 原型：public SequenceInputStream(Enumeration<? extends InputStream> e)
 *
 * 通过记住参数来初始化新创建的SequenceInputStream，该参数必须是生成运行时类型为InputStream对象的Enumeration型参数。将按顺序
 * 读取由该枚举生成的输入流，以提供从此SequenceInputStream读取的字节。在用尽枚举中的每个输入流之后，将通过调用该流的close方法将其关闭
 */
public class TestIOSequenceInputStream {


    public static void testSequence() {
        try {
            //SequenceInputStream
            InputStream s1 = new ByteArrayInputStream("1".getBytes());
            InputStream s2 = new ByteArrayInputStream("2".getBytes());
            InputStream Sin = new SequenceInputStream(s1,s2);  //可以传入多个输入流
            int data;
            while((data=Sin.read()) != -1)
                System.out.print(data+" ");
            System.out.println();
            Sin.close();
            System.out.println("---------------------------");

        }
        catch(FileNotFoundException e){
            System.out.println("找不到该文件！");
        }
        catch(IOException e){}

    }

}
