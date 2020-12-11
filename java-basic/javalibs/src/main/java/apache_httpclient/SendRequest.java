package apache_httpclient;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.apache.http.impl.client.DefaultHttpClient;
/**
 * Created by Defias on 2017/3/15.
 *
 * httpclient   --发送请求
 *
 */
public class SendRequest {

    //private static DefaultHttpClient client = new DefaultHttpClient();
    //private static CloseableHttpClient client = HttpClients.createDefault();
    private static CookieStore cookieStore = new BasicCookieStore();
    private static CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    /**
     * 发送Get请求
     *
     * @param url
     *            请求的地址
     * @param headers
     *            请求的头部信息
     * @param params
     *            请求的参数
     * @param encoding
     *            字符编码
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static Result sendGet(String url, Map<String, String> params, Map<String, String> headers, String encoding,
                                 boolean duan) throws ClientProtocolException, IOException {
        url = url + (null == params ? "" : assemblyParameter(params));
        HttpGet hp = new HttpGet(url);
        if (null != headers)
            hp.setHeaders(assemblyHeader(headers));
        HttpResponse response = client.execute(hp);
        if (duan)
            hp.abort();
        HttpEntity entity = response.getEntity();
        Result result = new Result();
        result.setCookie(assemblyCookie(cookieStore.getCookies()));
        result.setStatusCode(response.getStatusLine().getStatusCode());
        result.setHeaders(response.getAllHeaders());
        result.setHttpEntity(entity);
        return result;
    }

    public static Result sendGet(String url,  Map<String, String> params, Map<String, String> headers, String encoding)
            throws ClientProtocolException, IOException {
        return sendGet(url, params, headers, encoding, false);
    }

    public static Result sendGet(String url, Map<String, String> params,  Map<String, String> headers)
            throws ClientProtocolException, IOException {
        return sendGet(url, params, headers,null, false);
    }


    public static Result sendGet(String url, Map<String, String> params)
            throws ClientProtocolException, IOException {
        return sendGet(url, params,null, null, false);
    }

    public static Result sendGet(String url)
            throws ClientProtocolException, IOException {
        return sendGet(url, null,null, null, false);
    }


    /**
     * 发送Get请求
     * 直接返回HttpResponse
     * */
    public static HttpResponse sendGetRes(String url, Map<String, String> params, Map<String, String> headers, String encoding) throws ClientProtocolException, IOException {
        url = url + (null == params ? "" : assemblyParameter(params));
        HttpGet hp = new HttpGet(url);
        if (null != headers)
            hp.setHeaders(assemblyHeader(headers));
        HttpResponse response = client.execute(hp);

        return response;
    }

    public static HttpResponse sendGetRes(String url, Map<String, String> params, Map<String, String> headers) throws ClientProtocolException, IOException {
        return sendGetRes(url, params, headers, null);
    }

    public static HttpResponse sendGetRes(String url, Map<String, String> params) throws ClientProtocolException, IOException {
        return sendGetRes(url, params, null, null);
    }

    public static HttpResponse sendGetRes(String url) throws ClientProtocolException, IOException {
        return sendGetRes(url, null, null, null);
    }



    /**
     * 发送Post请求
     *
     * @param url
     *            请求的地址
     * @param headers
     *            请求的头部信息
     * @param params
     *            请求的参数
     * @param encoding
     *            字符编码
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static Result sendPost(String url, Map<String, String> headers, Map<String, String> params, String encoding)
            throws ClientProtocolException, IOException {
        HttpPost post = new HttpPost(url);

        if (null != params) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (String temp : params.keySet()) {
                list.add(new BasicNameValuePair(temp, params.get(temp)));
            }
            post.setEntity(new UrlEncodedFormEntity(list, encoding));
        }

        if (null != headers)
            post.setHeaders(assemblyHeader(headers));
        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();

        Result result = new Result();
        result.setStatusCode(response.getStatusLine().getStatusCode());
        result.setHeaders(response.getAllHeaders());
        result.setCookie(assemblyCookie(cookieStore.getCookies()));
        result.setHttpEntity(entity);
        return result;
    }



    public static Result sendPostJsonBody(String url, Map<String, String> headers, String jsonbodys, String encoding) throws ClientProtocolException, IOException  {
        HttpPost post = new HttpPost(url);

        if (null != jsonbodys) {
            StringEntity se = new StringEntity(jsonbodys);
            se.setContentEncoding(encoding);
            se.setContentType("application/json");  //发送json需要设置contentType
            post.setEntity(se);
        }

        if (null != headers) {
            post.setHeaders(assemblyHeader(headers));
        }

        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();

        Result result = new Result();
        result.setStatusCode(response.getStatusLine().getStatusCode());
        result.setHeaders(response.getAllHeaders());
        result.setCookie(assemblyCookie(cookieStore.getCookies()));
        result.setHttpEntity(entity);
        return result;
    }

    /**
     * 发送Delete请求
     *
     * @param url
     *            请求的地址
     * @param headers
     *            请求的头部信息
     * @param params
     *            请求的参数
     * @param encoding
     *            字符编码
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static Result sendDelete(String url, Map<String, String> headers, Map<String, String> params, String encoding)
            throws ClientProtocolException, IOException {
        url = url + (null == params ? "" : assemblyParameter(params));
        HttpDelete delete = new HttpDelete(url);
        if (null != headers)
            delete.setHeaders(assemblyHeader(headers));
        HttpResponse response = client.execute(delete);

        HttpEntity entity = response.getEntity();
        Result result = new Result();
        result.setCookie(assemblyCookie(cookieStore.getCookies()));
        result.setStatusCode(response.getStatusLine().getStatusCode());
        result.setHeaders(response.getAllHeaders());
        result.setHttpEntity(entity);
        return result;
    }

    /**
     * 组装头部信息
     *
     * @param headers
     * @return
     */
    public static Header[] assemblyHeader(Map<String, String> headers) {
        Header[] allHeader = new BasicHeader[headers.size()];
        int i = 0;
        for (String str : headers.keySet()) {
            allHeader[i] = new BasicHeader(str, headers.get(str));
            i++;
        }
        return allHeader;
    }

    /**
     * 组装Cookie
     *
     * @param cookies
     * @return
     */
    public static String assemblyCookie(List<Cookie> cookies) {
        StringBuffer sbu = new StringBuffer();
        for (Cookie cookie : cookies) {
            sbu.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
        }
        if (sbu.length() > 0)
            sbu.deleteCharAt(sbu.length() - 1);
        return sbu.toString();
    }

    /**
     * 组装请求参数
     *
     * @param parameters
     * @return
     */
    public static String assemblyParameter(Map<String, String> parameters) {
        String para = "?";
        for (String str : parameters.keySet()) {
            para += str + "=" + parameters.get(str) + "&";
        }
        return para.substring(0, para.length() - 1);
    }

    public static void main(String[] args) {
        Map<String, String> param = new HashMap<String, String>();
        try {
            //get
            Result result = SendRequest.sendGet("http://www.baidu.com", param, param, "utf-8");
            // SendRequest.u
            //String str = result.getHtml(result, "utf-8");
            String str = result.getHtmlContent("utf-8");
            System.out.println(str);

            //post
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");

            Map<String, String> params = new HashMap<String, String>();
            params.put("xieyi", "100");
            params.put("commuid", "ae64fc9089514fae8349e916c37uid11111111333");
            params.put("operation", "2");
            params.put("index", "577");
            params.put("pagesize", "3");
            String paramstr = assemblyParameter(params);

            String host = "test2bservice.goluk.cn";
            String port = "80";
            String path = "/carbox/app/bindings";
            String url = "http://" + host + ":" + port + path + paramstr;

            result = SendRequest.sendPost(url, headers, null, "utf-8");
            str = result.getContent(result, "utf-8");
            System.out.println(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}