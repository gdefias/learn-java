package Net.AIO;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * Description: AIO客户端
 * User: Defias
 * Date: 2019-09
 */


public class Test_AIOClient {
    public static void main(String[] args) {
        AsynchronousSocketChannel socketChannel = null;
        try {
            socketChannel = AsynchronousSocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8014);

            //connect使用future阻塞方式
            Future<Void> connect = socketChannel.connect(inetSocketAddress);
            while (!connect.isDone()) {
                Thread.sleep(10);
            }

            System.out.println("建立连接 " + socketChannel.getRemoteAddress());
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //接收数据使用future阻塞方式
            Future<Integer> read = socketChannel.read(buffer);
            while (!read.isDone()) {
                Thread.sleep(10);
            }

            System.out.println("接收服务器数据: " + new String(buffer.array(), 0, read.get()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
