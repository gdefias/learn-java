package apache_httpcomponents.asyncclient;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-06
 *
 * AsynHttpClient
 */
import org.apache.http.client.fluent.Async;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.concurrent.FutureCallback;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AsynHttpClientDemo1 {

    //异步get请求
    public void doGetAsyn() throws InterruptedException, ExecutionException {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();   //开启httpclient
        HttpGet httpGet = new HttpGet("http://www.baidu.com");  //开始执行
        Future<HttpResponse> future = httpclient.execute(httpGet, null);
        HttpResponse httpResponse = future.get();
        System.out.println(httpResponse.getStatusLine()+"==="+httpGet.getRequestLine());
    }

    //异步的post方式请求
    public static void doPostAsyn(String url,String outStr) throws ParseException, IOException, InterruptedException, ExecutionException {
        CloseableHttpAsyncClient httpAsyncClient =  HttpAsyncClients.createDefault();
        httpAsyncClient.start();  //开启httpclient
        HttpPost httpost = new HttpPost(url);
        httpost.addHeader(HTTP.CONTENT_TYPE, "application/json");

        StringEntity se = new StringEntity(outStr,"UTF-8");
        se.setContentType("application/json");
        se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
        httpost.setEntity(se);

        //发送请求
        Future<HttpResponse> future = httpAsyncClient.execute(httpost,null);  //设置回调为空
        System.out.println(future.get().toString());
    }

    //在后台线程中异步执行多个请求
    public static void testFluentAsyn() {
        Request[] requests = new Request[]{
                Request.Get("http://www.baidu.com/"),
                Request.Get("http://www.yahoo.com/"),
                Request.Get("http://www.apache.com/"),
                Request.Get("http://www.apple.com/")
        };
        Queue<Future<Content>> queue = new LinkedList<>();

        /**
         * 异步执行GET请求
         */
        ExecutorService es = Executors.newFixedThreadPool(4);
        Async async = Async.newInstance().use(es);
        for (final Request request : requests) {
            Future<Content> future = async.execute(request, new FutureCallback<Content>() {
                @Override
                public void completed(Content content) {
                    System.out.println(System.currentTimeMillis() + " Request completed: " + request);
                }

                @Override
                public void failed(Exception e) {
                    System.out.println(e.getMessage() + ": " + request);
                }

                @Override
                public void cancelled() {
                    System.out.println("Request cancelled: " + request);
                }
            });
            queue.add(future);
        }

        Future<Content> future;
        while ((future = queue.poll()) != null) {
            if (future.isDone()) {
                if (!future.isCancelled()) {
                    try {
                        future.get();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        future.cancel(true);
                    } catch (ExecutionException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                queue.add(future);
            }
        }
        System.out.println("done");
        es.shutdown();
    }
}
