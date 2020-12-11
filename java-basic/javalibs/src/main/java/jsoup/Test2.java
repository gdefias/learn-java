package jsoup;

/**
 * Created by Defias on 2017/3/15.
 *
 * 请求中的参数
 *
 */

import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.io.*;

public class Test2 {

    //Get请求
    public void testJsop(){
        try {
            //Connection conn = Jsoup.connect("http://www.cnblogs.com/zhangfei/p/");
            //conn.data("page","3");
            //Document doc = conn.get();

            Document doc = Jsoup.connect("httpcomponents://www.cnblogs.com/zhangfei/p/?page=3").get();
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //post请求
    public void testJsop2(){
        try {
            Connection conn = Jsoup.connect("https://passport.jd.com/new/login.aspx");
            conn.data("loginname","test1");
            conn.data("loginpwd","test1");
            Document doc = conn.post();
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //响应超时请求处理
    public void testJsop3(){
        try {
            Connection conn = Jsoup.connect("https://passport.jd.com/new/login.aspx");
            conn.data("loginname","test1");
            conn.data("loginpwd","test1");
            conn.timeout(30000);
            Document doc = conn.post();
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //cookies的管理
    //public void testJsop4(){
    //    try {
    //        Connection conn = Jsoup.connect("https://passport.jd.com/new/login.aspx");
    //        conn.data("loginname","test1");
    //        conn.data("loginpwd","test1");
    //        conn.timeout(30000);
    //        conn.method(Method.POST);
    //        Response response = conn.execute();
    //        Map<String, String> cookies = response.cookies();
    //        Document doc = Jsoup.connect("http://order.jd.com/center/list.action")
    //                .cookies(cookies)
    //                .timeout(30000)
    //                .get();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}


    public static void main(String[] args) {
        Test2 t = new Test2();
        //t.testJsop();
        t.testJsop2();
    }
}
