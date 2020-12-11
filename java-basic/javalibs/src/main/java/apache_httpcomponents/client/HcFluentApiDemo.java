package apache_httpcomponents.client;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-06
 *
 * Httpclient Fluent API
 *
 * Fluent API(流式API)：
 * HttpClient基于流接口概念提供了更易使用的API，流接口将用户不得不处理连接管理和资源重分配的繁琐中解放出来
 */

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.protocol.HTTP;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class HcFluentApiDemo {
    /**
     * Fluent API流接口
     * @throws IOException
     * @throws ClientProtocolException
     */
    public void m1() throws ClientProtocolException, IOException{
        //Execute a GET with timeout settings and return response content as String
        Request.Get("http://somehost/")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();
    }

    public void m2() throws ClientProtocolException, IOException{
        //Execute a POST with the "expect-continue" handshake, using HTTP/1.1,
        //continuing a request body as String and return response content as byte array.
        Request.Post("http://somehost/do-stuff")
                .useExpectContinue()
                .version(HttpVersion.HTTP_1_1)
                .bodyString("Important stuff", ContentType.DEFAULT_TEXT)
                .execute().returnContent().asBytes();
    }

    public void m3() throws ClientProtocolException, IOException{
        //Execute a POST with a custom header through the proxy containing a request body
        //as an HTML form and save the result to the file
        Request.Post("http://somehost/some-form")
                .addHeader("X-Custom-header", "stuff")
                .viaProxy(new HttpHost("myproxy", 8080))
                .bodyForm(Form.form().add("username", "vip").add("password", "secret").build())
                .execute().saveContent(new File("result.dump"));;
    }

    /**
     * 用户也可以通过缓存和重用后续请求的认证详细信息直接使用Executor来执行特定安全上下文中的请求.
     * @throws IOException
     * @throws ClientProtocolException
     */
    public void m4() throws ClientProtocolException, IOException{
        Executor executor=Executor.newInstance()
                .auth(new HttpHost("somehost"), "username", "password")
                .auth(new HttpHost("myproxy", 8080), "username", "password")
                .authPreemptive(new HttpHost("myproxy", 8080));

        executor.execute(Request.Get("http://somehost"))
                .returnContent().asString();

        executor.execute(Request.Post("http://somehost/do-stuff")
                .useExpectContinue()
                .bodyString("Important stuff", ContentType.DEFAULT_TEXT))
                .returnContent().asString();
    }

    /**
     * 响应处理:在大多数情况下,使用fluent facade api会带来不得不将响应消息内容缓冲进内存的代价.
     * 在这里极度推荐使用ResponseHandler用于HTTP响应处理以避免不得不将内容缓冲进入内存.
     * @throws IOException
     * @throws ClientProtocolException
     */
    public void m5() throws IOException{
        Document result = Request.Get("http://somehost/content")
                .execute().handleResponse(new ResponseHandler<Document>() {

                    public Document handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                        StatusLine statusLine = response.getStatusLine();
                        HttpEntity entity = response.getEntity();
                        if(statusLine.getStatusCode() >= 300){
                            throw new HttpResponseException(
                                    statusLine.getStatusCode(),
                                    statusLine.getReasonPhrase());
                        }
                        if(entity == null){
                            throw new ClientProtocolException("Response contains no content");
                        }
                        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();

                        try {
                            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
                            ContentType contentType = ContentType.get(entity);

                            if(!contentType.equals(ContentType.APPLICATION_XML)){
                                throw new ClientProtocolException("Unexpected content type:"
                                        +contentType);
                            }

                            Charset charset = contentType.getCharset();
                            if(charset == null){
                                charset = HTTP.DEF_CONTENT_CHARSET;
                            }

                            return docBuilder.parse(entity.getContent());

                        } catch (ParserConfigurationException ex) {
                            throw new IllegalStateException(ex);
                        } catch (SAXException ex) {
                            throw new ClientProtocolException("Malformed XML document", ex);
                        }
                    }
                });
    }
}
