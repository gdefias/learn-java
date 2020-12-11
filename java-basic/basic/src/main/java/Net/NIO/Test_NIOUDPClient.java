package Net.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: NIP客户端 - UDP
 * User: Defias
 * Date: 2019-09
 */
public class Test_NIOUDPClient {
    public static void main(String[] args) throws InterruptedException{
        Thread client = new Thread(new NIOUDPClientHandler());
        client.start();
    }

    public static class NIOUDPClientHandler implements Runnable {
        @Override
        public void run() {
            try {
                DatagramChannel dChannel = DatagramChannel.open();
                dChannel.configureBlocking(false);
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                System.out.println("请输入要发生的数据:");
                Scanner scanner = new Scanner(System.in);
                while(scanner.hasNext()){
                    System.out.println("请输入要发生的数据:");
                    String string = scanner.next();
                    buffer.put((new Date().toString()+">>"+string).getBytes());
                    buffer.flip();
                    dChannel.send(buffer, new InetSocketAddress("127.0.0.1", 8989));
                    buffer.clear();

                }
                dChannel.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
