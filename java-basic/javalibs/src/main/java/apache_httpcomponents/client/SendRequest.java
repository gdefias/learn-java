package apache_httpcomponents.client;

/**
 * Created by Defias on 2017/3/15.
 *
 * httpclient
 *
 * 发送请求
 * 使用ResponseResult
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;


@SuppressWarnings("deprecation")
public class SendRequest {

    private static DefaultHttpClient client = new DefaultHttpClient();

    /**
     * 发送Get请求
     *
     * @param url   请求的地址
     * @param headers   请求的头部信息
     * @param params  请求的参数
     * @param encoding   字符编码
     *
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static ResponseResult sendGet(String url, Map<String, String> headers, Map<String, String> params, String encoding,
                                 boolean duan) throws ClientProtocolException, IOException {
        url = url + (null == params ? "" : assemblyParameter(params));
        HttpGet hg = new HttpGet(url);
        if (null != headers)
            hg.setHeaders(assemblyHeader(headers));

        //发送请求
        HttpResponse response = client.execute(hg);
        if (duan)
            hg.abort();

        //处理响应
        HttpEntity entity = response.getEntity();
        ResponseResult result = new ResponseResult();
        result.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
        result.setStatusCode(response.getStatusLine().getStatusCode());
        result.setHeaders(response.getAllHeaders());
        result.setHttpEntity(entity);
        return result;
    }

    public static ResponseResult sendGet(String url, Map<String, String> headers, Map<String, String> params, String encoding)
            throws ClientProtocolException, IOException {
        return sendGet(url, headers, params, encoding, false);
    }

    /**
     * 发送Post请求
     *
     * @param url  请求的地址
     * @param headers  请求的头部信息
     * @param params  请求的参数
     * @param encoding  字符编码
     *
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static ResponseResult sendPost(String url, Map<String, String> headers, Map<String, String> params, String encoding)
            throws ClientProtocolException, IOException {
        HttpPost hp = new HttpPost(url);

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String temp : params.keySet()) {
            list.add(new BasicNameValuePair(temp, params.get(temp)));
        }
        hp.setEntity(new UrlEncodedFormEntity(list, encoding));

        if (null != headers)
            hp.setHeaders(assemblyHeader(headers));

        //发送请求
        HttpResponse response = client.execute(hp);

        //处理响应
        HttpEntity entity = response.getEntity();
        ResponseResult result = new ResponseResult();
        result.setStatusCode(response.getStatusLine().getStatusCode());
        result.setHeaders(response.getAllHeaders());
        result.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
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
            //http get
            ResponseResult result = SendRequest.sendGet("httpcomponents://www.baidu.com", param, param, "utf-8");

            String str = result.getHtmlContent("utf-8");
            System.out.println(str);

            //http post
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");

            Map<String, String> params = new HashMap<String, String>();
            params.put("xieyi", "100");

            result = SendRequest.sendPost("httpcomponents://test2bservice.goluk.cn/carbox/app/bindings?xieyi=100&commuid=ae64fc9089514fae8349e916c37uid11111111333&operation=2&index=577&pagesize=3", headers, params, "utf-8");

            str = result.getHtml(result, "utf-8");
            System.out.println(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}