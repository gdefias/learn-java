package Net.NIO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
/**
 * Created with IntelliJ IDEA.
 * Description: NIP服务器 - UDP
 * User: Defias
 * Date: 2019-09
 */

public class Test_NIOUDPServer {
    public static void main(String[] args) throws InterruptedException{
        Thread server = new Thread(new NIOUDPServerHandler());
        server.start();

    }

    public static class NIOUDPServerHandler implements Runnable {
        @Override
        public void run() {
            try {
                DatagramChannel datagramChannel = DatagramChannel.open();
                datagramChannel.configureBlocking(false);
                datagramChannel.bind(new InetSocketAddress(8989));

                System.out.println("服务端启动！");
                Selector selector = Selector.open();
                datagramChannel.register(selector, SelectionKey.OP_READ);

                while(selector.select()>0) {
                    System.out.println("select！");
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        System.out.println("hasNext！");
                        SelectionKey selectionKey = (SelectionKey) iterator.next();
                        if (selectionKey.isReadable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            datagramChannel.receive(buffer);
                            buffer.flip();
                            System.out.print("recevie: ");
                            System.out.println(new String(buffer.array(),0,buffer.limit()));
                            buffer.clear();
                        }
                    }
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
