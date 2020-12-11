package Net.AIO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * Created with IntelliJ IDEA.
 * Description:  AIO服务器 - 阻塞式的异步IO
 * User: Defias
 * Date: 2018-05
 *
 */
public class Test1_AIOServer {
    private static Charset charset = Charset.forName("US-ASCII");
    private static CharsetEncoder encoder = charset.newEncoder();

    public static void main(String[] args) throws Exception {
        server();
    }

    public static void server() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(executorService);

        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group).bind(
                new InetSocketAddress("0.0.0.0", 8013));

        //使用回调的方式
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Void attachment) {
                System.out.println("服务器接受连接");
                server.accept(null, this); // 接受下一个连接使服务器可以持续接收连接请求

                try {
                    String now = new Date().toString();
                    ByteBuffer buffer = encoder.encode(CharBuffer.wrap(now + "\r\n"));
                    //result.write(buffer, null, new CompletionHandler<Integer,Void>(){...}); //callback or

                    Future<Integer> f = result.write(buffer);

                    //Future对象的get方法会阻塞该线程，所以这种方式是阻塞式的异步IO
                    f.get();
                    System.out.println("sent to client: " + now);
                    System.out.println("服务器发送数据完毕.");

                    result.close();
                } catch (IOException | InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
            }
        });

        //一直等待
        //group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        while(true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }

    }
}
