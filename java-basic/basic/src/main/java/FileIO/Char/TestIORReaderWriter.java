package FileIO.Char;

/**
 * Created by Defias on 2016/5/6.
 *
 *  字符（文本）输入输出流
 *
 */

import java.io.*;

public class TestIORReaderWriter {
    public static void main(String args[]) throws IOException {
        TestIORReaderWriter util = new TestIORReaderWriter ();

        //按照本地平台的字符编码读
        util.readFile("base/src/tests.txt");

        //把test.txt文件中的字符内容拷贝到out.txt中，out.txt采用UTF-8编码
        util.copyFile("base/src/tests.txt",null, "out.txt","UTF-8");

        //按照本地平台的字符编码读
        util.readFile("base/src/out.txt");

        //按照UTF-8字符编码读
        util.readFile("base/src/out.txt","UTF-8");
    }

    //重载方法
	public void readFile(String fileName) throws IOException {
		readFile(fileName,null);
	}

	//读
	public void readFile(String fileName, String charsetName) throws IOException {
        if(fileName==null)
            return;
		InputStream in = new FileInputStream(fileName);
		InputStreamReader reader;
		if(charsetName == null)
			reader = new InputStreamReader(in);
		else
			reader = new InputStreamReader(in, charsetName);

		BufferedReader br = new BufferedReader(reader);
		String data;
		while((data=br.readLine()) != null)  //逐行读取数据
			System.out.println(data);
		br.close();
	}

	//拷贝
	public void copyFile(String from, String charsetFrom,String to,String charsetTo) throws IOException {
        if(from==null || to==null)
            return;
		InputStream in = new FileInputStream(from);
		InputStreamReader reader;
		if(charsetFrom==null)
			reader = new InputStreamReader(in);
		else
			reader = new InputStreamReader(in, charsetFrom);
		BufferedReader br = new BufferedReader(reader);  //文本缓冲输入流

        OutputStream out = new FileOutputStream(to);
        OutputStreamWriter writer;
        if(charsetTo==null)
		    writer = new OutputStreamWriter(out,charsetTo);
        else
            writer = new OutputStreamWriter(out);
		BufferedWriter bw = new BufferedWriter(writer);  //文本缓冲输出流
        PrintWriter pw = new PrintWriter(bw,true);  //文本文件输出流（追加）

		String data;
		while((data = br.readLine()) != null)  //读
			pw.println(data);  //写

		br.close();
		pw.close();
	}
}