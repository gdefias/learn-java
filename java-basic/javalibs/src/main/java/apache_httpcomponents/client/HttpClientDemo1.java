package apache_httpcomponents.client;

/**
 * Created by Defias on 2017/8/15.
 *
 *
 * httpclient
 *
 * 使用HttpClient发送请求、接收响应一般需要如下几步：
 * 1. 创建HttpClient对象
 * 2. 创建请求方法的实例，并指定请求URL。如果需要发送GET请求，创建HttpGet对象；如果需要发送POST请求，创建HttpPost对象
 * 3. 如果需要发送请求参数，可调用HttpGet、HttpPost共同的setParams(HetpParams params)方法来添加请求参数；对于HttpPost对象而言，也可调用
 * setEntity(HttpEntity entity)方法来设置请求参数
 * 4. 调用HttpClient对象的execute(HttpUriRequest request)发送请求，该方法返回一个HttpResponse
 * 5. 调用HttpResponse的getAllHeaders()、getHeaders(String name)等方法可获取服务器的响应头；调用HttpResponse的getEntity()方法可获取HttpEntity
 * 对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容
 * 6. 释放连接。无论执行方法是否成功，都必须释放连接
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientDemo1 {

    //Get请求
    public static void testGet() throws IOException {
        HttpGet get = new HttpGet("http://www.baidu.com");  //HttpGet的实例就是一个get请求
        //HttpGet get = new HttpGet("http://localhost:8080/jsx/servlet?id=007"); //Get请求传递参数方法一：将查询字符串作为请求地址的一部分
        HttpClient http = new DefaultHttpClient();  //基本的HttpClient实例
        HttpResponse response = http.execute(get);  //由于底层是基于阻塞的JAVA I/O模型，执行execute()的时间与具体请求的远程服务器和网络速度有关

        //读取返回结果
        if (response.getStatusLine().getStatusCode() == 200) { //一个成功响应的StatusLine实例本身包含信息：HTTP/1.0 200 OK
            ProtocolVersion protocolversion = response.getStatusLine().getProtocolVersion();
            System.out.println(protocolversion);  //请求协议和协议版本号

            HttpEntity entity = response.getEntity();  //HttpEntity实例
            InputStream in = entity.getContent();  //输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    //Post请求
    public static void testPost() throws IOException {
        HttpPost post = new HttpPost("http://www.126.com");
        HttpClient http = new DefaultHttpClient();
        HttpResponse response = http.execute(post);

        //读取返回结果
        if (response.getStatusLine().getStatusCode() == 200) {
            ProtocolVersion protocolversion = response.getStatusLine().getProtocolVersion();
            System.out.println(protocolversion);  //请求协议和协议版本号

            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    //Get请求传递参数方法二：使用URI携带查询字符串
    public static void testGetParam() throws IOException,URISyntaxException {
        //创建查询参数，NameValuePair用一对键、值表示一个查询参数，将多个NameValuePair放在一个List中，就形成了一组查询参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", "ahopedog"));
        params.add(new BasicNameValuePair("work", "程序员"));

        //需要用URLEncodedUtils.format()方法将其编码成字符串
        String queryString = URLEncodedUtils.format(params, "utf-8");


        //使用URIUtils构建URI
        URI uri = URIUtils.createURI("http", "localhost", 8080, "/jsx/servlet", queryString, null);

        //也可以使用URIBuilder构建URI
        URI uri2 = new URIBuilder()
                .setScheme("http")
                .setHost("www.google.com")
                .setPath("/search")
                .setParameter("q", "apache_httpclient")
                .setParameter("btnG", "Google Search")
                .setParameter("aq", "f")
                .setParameter("oq", "")
                .build();

        //创建Get实例
        HttpGet get = new HttpGet(uri);

        //发送请求
        HttpClient http = new DefaultHttpClient();
        HttpResponse response = http.execute(get);

        //读取返回结果
        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println("request success done");
        } else {
            System.out.println("request false done");
        }
    }

    //Post请求传递参数
    public static void testPostParam() throws IOException,URISyntaxException {
        HttpPost post = new HttpPost("http://localhost:8080/jsx/servlet");

        //添加参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", "ahopedog"));
        params.add(new BasicNameValuePair("work", "程序员"));
        post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

        //发送请求
        HttpClient http = new DefaultHttpClient();
        HttpResponse response = http.execute(post);

        //读取返回结果
        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println("request success done");
        } else {
            System.out.println("request false done");
        }
    }

    //处理响应
    public static void testResponse() {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpPost post = new HttpPost("http://www.126.com");
            System.out.println("executing request: " + post.getURI());

            //发送请求
            HttpResponse response = httpclient.execute(post);

            //处理响应结果
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                System.out.println("--------------------------------------------------");
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength()); //响应体的字节长度
                    System.out.println("Response content: " + EntityUtils.toString(entity));
                    System.out.println("Response content type: " + entity.getContentType());  //响应体的类型
                    System.out.println("Response content encoding: " + entity.getContentEncoding());  //响应体的字符编码


                    InputStream in = null;
                    try {
                        in = entity.getContent();  //响应体的InputStream，有了这个流对象基本上就可以"为所欲为"了
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } finally {
                        if(in != null)
                            in.close();  //关闭输入流
                    }
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();  //关闭连接
        }
    }

    //处理头
    public static void testHeaders()  throws IOException {
        HttpPost post = new HttpPost("http://www.126.com");

        //添加请求头信息
        post.setHeader("User-Agent", "Ahopedog/5.0 (Linux NT 5.1; rv:5.0) Gecko/20100101 FireDog/5.0");
        post.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");

        //发送请求
        HttpClient http = new DefaultHttpClient();
        HttpResponse response = http.execute(post);

        //遍历返回头
        Header[] headers = response.getAllHeaders(); //得到响应头数组，一个响应头封装成一个Header实例
        for(Header h : headers){
            System.out.println(h.getName() + " : " + h.getValue());  //Header的两个关键方法是getName()和getValue()，得到头名字和值
        }
        System.out.println("======================================");

        //获取Server头信息，头名字不区分大小写
        Header serverHeader = response.getFirstHeader("server"); //指定获取一个特定的头，需要指定头的名字。多个头名字是可以重名的，而getFirstHeader是得到同名头中的第一个
        System.out.println(serverHeader.getName() + " : " + serverHeader.getValue());
    }


    public static void testRequestConfig() throws IOException {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("account", ""));
        formparams.add(new BasicNameValuePair("password", ""));
        HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://cnivi.com.cn/login");
        post.setEntity(reqEntity);
        post.setConfig(requestConfig);
        HttpResponse response = client.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity resEntity = response.getEntity();
            String message = EntityUtils.toString(resEntity, "utf-8");
            System.out.println(message);
        } else {
            System.out.println("请求失败");
        }

    }

    public static void main(String[] args) throws IOException {
        testPost();
    }

}
