package apache_httpcomponents.client;

/**
 * Created by Defias on 2017/8/15.
 *
 * httpclient
 *
 * StringEntity
 */

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientDemo3 {
    public static void main(String[] args) throws Exception  {
        get();
    }

    //post方式提交json
    public static void postJson() throws Exception{
        //创建默认的httpClient实例
        CloseableHttpClient httpclient = null;
        //接收响应结果
        CloseableHttpResponse response = null;

        try {
            //创建httppost
            httpclient = HttpClients.createDefault();
            String url ="httpcomponents://192.168.16.36:8081/goSearch/gosuncn/deleteDocs.htm";
            HttpPost httppost = new HttpPost(url);
            httppost.addHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded");

            //参数
            String json ="{'ids':['html1','html2']}";
            StringEntity se = new StringEntity(json);
            se.setContentEncoding("UTF-8");
            se.setContentType("application/json");//发送json需要设置contentType
            httppost.setEntity(se);

            //发送请求
            response = httpclient.execute(httppost);

            //解析结果
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String resStr = EntityUtils.toString(entity, "UTF-8");
                System.out.println(resStr);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
            response.close();
        }
    }

    //post方式提交表单（模拟用户登录请求）
    public static void postForm() throws Exception  {
        //创建默认的httpClient实例
        CloseableHttpClient httpclient = null;
        //接收响应结果
        CloseableHttpResponse response = null;

        try {
            httpclient = HttpClients.createDefault();

            //创建httppost
            String url= "httpcomponents://localhost:8080/search/ajx/user.htm";
            HttpPost httppost = new HttpPost(url);

            //创建参数队列
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("username", "admin"));
            formparams.add(new BasicNameValuePair("password", "123456"));
            //参数转码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);

            //发送请求
            response = httpclient.execute(httppost);

            //解析结果
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println(EntityUtils.toString(entity, "UTF-8"));
            }
        } catch (Exception e) {
            throw e;
        } finally {  //释放连接
            httpclient.close();
            response.close();
        }
    }

    //发送get请求
    public static void get() throws Exception {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;

        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://www.baidu.com/");

            //发送请求
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode()); // 打印响应状态

            if (entity != null) {
                System.out.println("Response content: " + EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
            response.close();
        }
    }
}
