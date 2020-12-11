package FileIO.Char;

/**
 * Created by Defias on 2016/2/29.
 *
 * 判断输入的绝对路径是代表一个文件还是一个目录。若是文件输出此文件的绝对路径，并判断此文件的文件属性（是否可读写或隐藏）;若是目录则输出
 * 该目录下所有文件（不包括隐藏文件）
 */

import java.io.*;

public class TestIsFileOrDir {
	public static void main(String args[]) throws IOException {

        String FilePath;
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader a = new BufferedReader(in);
		System.out.println("请输入一个绝对路径：");
		FilePath = a.readLine();  //FilePath接收输入值
		System.out.println("FilePath：" + FilePath);

		File FileO = new File(FilePath);
		if (FileO.isDirectory()) {  //如果是目录
			System.out.println((FileO.getName())+"为一个目录：");
			System.out.println("---------------------------");

            File[] FileList = FileO.listFiles();  //将目录下所有文件存入数组
			for(int i=0; i<FileList.length; i++){
				if(FileList[i].isHidden() == false) {  //判断是否为隐藏文件
					System.out.println(FileList[i].getName());  //输出非隐藏文件
				}
			}
		}
		else if(FileO.isFile()) {  //如果是文件
			System.out.println((FileO.getName())+"为一个文件：");
			System.out.println("---------------------------");
			System.out.println("绝对路径为："+FileO.getAbsolutePath());   //文件绝对路径
			System.out.println(FileO.canRead()?"可读取":"不可读取");  //文件是否可读
			System.out.println(FileO.canWrite()?"可修改":"不可修改");  //文件是否可写
			System.out.println(FileO.isHidden()?"为隐藏文件":"非隐藏文件");   //文件是否为隐藏文件
		} else {
			System.out.println("Don't Know");
		}
	}
}
