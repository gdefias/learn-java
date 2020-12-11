package apache_httpcomponents.client;

/**
 * Created by Defias on 2017/3/15.
 *
 *  结果封装类（处理响应）
 *  封装响应的头部信息、状态信息、Cookie信息、返回内容
 */


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;


public class ResponseResult {
    private String cookie;
    private int statusCode;
    private HashMap<String, Header> headerAll;
    private HttpEntity httpEntity;
    private String otherContent;

    /**
     * 获取Cookie信息
     */
    public String getCookie() {
        return cookie;
    }

    /**
     * 设置Cookie信息
     * @param cookie
     */
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    /**
     * 获取结果状态码
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * 设置结果状态码
     * @param statusCode
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 获取结果头部信息
     */
    public HashMap<String, Header> getHeaders() {
        return headerAll;
    }

    /**
     * 设置结果头部信息
     * @param headers
     */
    public void setHeaders(Header[] headers) {
        headerAll = new HashMap<String, Header>();
        for (Header header : headers) {
            headerAll.put(header.getName(), header);
        }
    }

    /**
     * 获取响应结果
     */
    public HttpEntity getHttpEntity() {
        return httpEntity;
    }

    /**
     * 设置响应结果
     * @param httpEntity
     */
    public void setHttpEntity(HttpEntity httpEntity) {
        this.httpEntity = httpEntity;
    }

    /**
     * 将服务器返回的结果HttpEntity流转换成String格式的内容
     * @param encoding   指定的转换编码
     */
    public String getHtmlContent(String encoding) {
        if (httpEntity != null) { //HTML内容
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            InputStream is = null;
            try {
                if (httpEntity.getContentEncoding() != null && httpEntity.getContentEncoding().getValue().indexOf("gzip") != -1) {
                    //GZIP格式的流解压
                    is = new GZIPInputStream(new BufferedInputStream(httpEntity.getContent()));
                } else {
                    is = new BufferedInputStream(httpEntity.getContent());
                }

                String responseContent = "";
                if (is != null) {
                    byte[] buffer = new byte[1024];
                    int n;
                    while ((n = is.read(buffer)) >= 0) {
                        output.write(buffer, 0, n);
                    }
                    responseContent = output.toString(encoding);
                    //responseContent = new String(responseContent.getBytes("utf-8"),"gbk");
                }
                return responseContent;
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * 获取响应中的内容
     */
    public String getHtml(ResponseResult result, String chart) {
        System.out.println("getHtml(Result, String) - start");

        HttpEntity entity = result.getHttpEntity();
        String resultStr = "";
        try {
            resultStr = EntityUtils.toString(entity, chart);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                EntityUtils.consume(entity);  //关闭HttpEntity流  如果手动关闭了InputStream instream = entity.getContent();这个流，也可以不调用这个方法
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("getHtml(Result, String) - end");
        return resultStr;
    }

    /**
     * 关闭HttpEntity流
     */
    public void consume(ResponseResult result) {
        try {
            HttpEntity entity = result.getHttpEntity();
            //EntityUtils.consume(entity);
            if (entity.isStreaming()) {
                InputStream instream = entity.getContent();
                if (instream != null) {
                    instream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getOtherContent() {
        return otherContent;
    }

    public void setOtherContent(String otherContent) {
        this.otherContent = otherContent;
    }
}