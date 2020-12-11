package Net.AIO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;
/**
 * Created with IntelliJ IDEA.
 * Description:  AIO客户端
 * User: Defias
 * Date: 2018-05
 */

public class Test1_AIOClient {
    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        Future<Void> future = client.connect(new InetSocketAddress("127.0.0.1", 8013));
        future.get();
        System.out.println("建立连接 " + client.getRemoteAddress());

        ByteBuffer buffer = ByteBuffer.allocate(100);

        //接收数据使用了回调的方式
        client.read(buffer, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                System.out.println("client received: " + new String(buffer.array()));

            }
            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(10000);
    }

}
