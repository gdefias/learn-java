package Net.AIO;

import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;
/**
 * Created with IntelliJ IDEA.
 * Description: AIO服务器
 * User: Defias
 * Date: 2019-09
 */

public class Test_AIOServer {
    public static void main(String[] args) {
        try {
            Server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Server() throws Exception {
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8014);
        serverSocketChannel.bind(inetSocketAddress);

        Future<AsynchronousSocketChannel> accept;

        while (true) {
            //accept()不会阻塞
            accept = serverSocketChannel.accept();
            System.out.println("=================");
            System.out.println("服务器等待连接...");

            //get()方法将阻塞
            AsynchronousSocketChannel socketChannel = accept.get();
            System.out.println("服务器接受连接");
            System.out.println("服务器与" + socketChannel.getRemoteAddress() + "建立连接");

            ByteBuffer buffer = ByteBuffer.wrap("yzhdebug".getBytes());
            Future<Integer> write=socketChannel.write(buffer);

            while(!write.isDone()) {
                Thread.sleep(10);
            }

            System.out.println("服务器发送数据完毕.");
            socketChannel.close();
        }
    }
}
