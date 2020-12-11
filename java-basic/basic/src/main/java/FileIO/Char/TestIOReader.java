package FileIO.Char;

/**
 * Created by Defias on 2016/2/29.
 *
 * 字符（文本）输入流
 */

import java.io.*;

public class TestIOReader {
	public static void main(String[] args) throws IOException {
		try {
			//FileReader
			char[] a = new char[100];
			FileReader b = new FileReader("./base/src/new.txt");
			int num = b.read(a); //将数据读入到数组a中，并返回字符数（回车或换行作为两个字符对待）
			String str = new String(a,0,num); //将字符数组转换成字符串
			System.out.println("读取的字符个数为："+num+",内容为：");
			System.out.println(str);
			b.close();
            System.out.print("----------------------------------1\n");

			//BufferedReader
			FileReader f = new FileReader("base/src/new.txt");
			BufferedReader br = new BufferedReader(f);  //创建缓冲区文本输入流
			//BufferedReader br = new BufferedReader(f, 1000); //创建输入流并设置缓冲区大小
			int count = 0;  //计算读取的行数
			String line;
			while ((line=br.readLine()) != null) { //每次读取1行
				count++;
				System.out.println(line);
			}
			System.out.println("共读取了"+count+"行");
			br.close();
            System.out.print("----------------------------------2\n");

			//BufferedReader and StringBuilder
			System.out.println(read("base/src/new.txt"));
			System.out.print("----------------------------------3\n");

			//CharArrayReader
			char[] buff = new char[]{'a','你','好','1'};
			CharArrayReader reader = new CharArrayReader(buff);
			int data;
			while((data=reader.read()) != -1)
				System.out.print((char)data);
			reader.close();
			System.out.print("\n----------------------------------4\n");

			//StringReader
			StringReader reader2 = new StringReader("abcd1好");
			while((data=reader2.read()) != -1)  //每次读一个字符
				System.out.print((char)data);
			reader2.close();
			System.out.print("\n----------------------------------5\n");

			//InputStreamReader
			InputStreamReader reader3 = new InputStreamReader(new FileInputStream("base/src/new.txt"), "UTF-8");
            while((data=reader3.read()) != -1)
			    System.out.print((char)data);
			System.out.print("\n----------------------------------6\n");

            //LineNumberReader
            readFromFile("./base/src/myFile.txt");

		} catch(IOException e) {
			System.out.println("IO Problem: " + e);
		}
	}

	//LineNumberReader是BufferedReader的子类，用来按行读取文本文件
    public static void readFromFile(String filename) {
        LineNumberReader lineNumberReader = null;
        try {
            //构造LineNumberReader实例
            lineNumberReader = new LineNumberReader(new FileReader(filename));
            String line = null;
            while ((line = lineNumberReader.readLine()) != null) {
                System.out.println("Line " + lineNumberReader.getLineNumber() + ": " + line);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //关闭lineNumberReader
            try {
                if (lineNumberReader != null) {
                    lineNumberReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

	public static String read(String filename) throws IOException {
		BufferedReader in = new BufferedReader(
				new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while((s = in.readLine())!= null)
			sb.append(s + "\n");
		in.close();
		return sb.toString();
	}

}
