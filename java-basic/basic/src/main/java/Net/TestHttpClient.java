package Net;

/**
 * Created by Defias on 2020/09.
 * Description:

 需要java 11


 从Java 11开始，引入了新的HttpClient，它使用链式调用的API，能大大简化HTTP的处理
 */
//public class TestHttpClient {
//    // 全局HttpClient实例 HttpClient内部使用线程池优化多个HTTP连接，可以复用
//    static HttpClient httpClient = HttpClient.newBuilder().build();
//
//    public static void main(String[] args) throws Exception {
//
//    }
//
//
//    public static void testGet() throws Exception {
//        String url = "https://www.sina.com.cn/";
//        HttpRequest request = HttpRequest.newBuilder(new URI(url))
//                // 设置Header:
//                .header("User-Agent", "Java HttpClient").header("Accept", "*/*")
//                // 设置超时:
//                .timeout(Duration.ofSeconds(5))
//                // 设置版本:
//                .version(Version.HTTP_2).build();
//        //二进制: HttpResponse.BodyHandlers.ofByteArray() 获得HttpResponse<byte[]>对象
//        //响应的内容很大，不希望一次性全部加载到内存： HttpResponse.BodyHandlers.ofInputStream()  获取一个InputStream流
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        // HTTP允许重复的Header，因此一个Header可对应多个Value:
//        Map<String, List<String>> headers = response.headers().map();
//        for (String header : headers.keySet()) {
//            System.out.println(header + ": " + headers.get(header).get(0));
//        }
//        System.out.println(response.body().substring(0, 1024) + "...");
//    }
//
//    public static void testPost() throws Exception {
//        String url = "http://www.example.com/login";
//        String body = "username=bob&password=123456";
//        HttpRequest request = HttpRequest.newBuilder(new URI(url))
//                // 设置Header:
//                .header("Accept", "*/*")
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                // 设置超时:
//                .timeout(Duration.ofSeconds(5))
//                // 设置版本:
//                .version(Version.HTTP_2)
//                // 使用POST并设置Body:
//                .POST(BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        String s = response.body();
//    }
//}
