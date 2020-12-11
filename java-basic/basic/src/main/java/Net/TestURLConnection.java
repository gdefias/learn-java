package Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Defias on 2016/2/29.

 URLConnection抽象类

 创建与URL的连接是一个多步骤过程：
     1）通过在URL上调用openConnection方法创建连接对象
     2）设置参数和一般请求属性被操纵
     3）使用connect方法实现与远程对象的实际连接
     4）远程对象变得可用。 可以访问头字段和远程对象的内容
 */


public class TestURLConnection {
    public static void main(String args[]) {
        try{
            String urlName = "http://www.baidu.com/";
            URL url = new URL(urlName);      //由网址创建URL对象
            URLConnection tc = url.openConnection();    //获得URLConnection对象
            tc.connect();      //如果此连接尚未建立，打开与此URL引用的资源的通信链接
            InputStreamReader in = new InputStreamReader(tc.getInputStream());
            BufferedReader dis = new BufferedReader(in);      //采用缓冲式输入
            String inline;
            while((inline = dis.readLine())!=null){
                System.out.println(inline);
                System.out.printf("\n");
            }
            dis.close();     //网上资源使用结束后，数据流及时关闭
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}


