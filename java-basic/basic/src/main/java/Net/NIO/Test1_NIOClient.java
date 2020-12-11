package Net.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-06
 *
 * NIOClient
 */

public class Test1_NIOClient {
    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(1234));
        while (true) {
            System.out.println("请输入要发生的数据:");
            Scanner sc = new Scanner(System.in);
            String next = sc.next();
            System.out.println("send: "+next);
            sendMessage(socketChannel, next);
        }
    }

    public static void sendMessage(SocketChannel socketChannel, String mes) throws IOException {
        if (mes == null || mes.isEmpty()) {
            return;
        }
        byte[] bytes = mes.getBytes("UTF-8");
        int size = bytes.length;
        ByteBuffer buffer = ByteBuffer.allocate(size);
        ByteBuffer sizeBuffer = ByteBuffer.allocate(4);

        sizeBuffer.putInt(size);
        buffer.put(bytes);

        buffer.flip();
        sizeBuffer.flip();
        ByteBuffer dest[] = {sizeBuffer,buffer};
        while (sizeBuffer.hasRemaining() || buffer.hasRemaining()) {
            socketChannel.write(dest);
        }
    }
}
