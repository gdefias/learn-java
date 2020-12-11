package jsoup;

/**
 * Created by Defias on 2017/3/15.
 *
 jsoup是一款Java的HTML解析器，可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，可通过DOM，CSS以及类似于JQuery的操作方法来取出和操作数据。

 jsoup的主要功能如下：
 从一个URL，文件或字符串中解析HTML；
 使用DOM或CSS选择器来查找、取出数据；
 可操作HTML元素、属性、文本；
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;


public class Test1 {
    public void testJsop(){
        try {
            //从文件解析HTML
            //File input = new File("/tmp/input.html");
            //Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

            Document doc = Jsoup.connect("httpcomponents://www.cnblogs.com/zhangfei/p/4283930.html").get();
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Test1 t = new Test1();
        t.testJsop();
    }
}
