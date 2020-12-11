package Net.NIO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
/**
 * Created by Defias on 2020/08.
 * Description: UDP ECHO服务器
 */
public class Test_UDPServerSelector {

    private static final int TIMEOUT = 3000;

    private static final int ECHOMAX = 255;

    public static void main(String[] args) throws IOException {
        int servPort = Integer.parseInt("8083");

        Selector selector = Selector.open();

        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(servPort));
        channel.register(selector, SelectionKey.OP_READ, new ClientRecord());

        while (true) {
            // Wait for task or until timeout expires
            if (selector.select(TIMEOUT) == 0) {
                System.out.print(".");
                continue;
            }

            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();

                if (key.isReadable())
                    handleRead(key);

                if (key.isValid() && key.isWritable())
                    handleWrite(key);

                keyIter.remove();
            }
        }
    }

    public static void handleRead(SelectionKey key) throws IOException {
        DatagramChannel channel = (DatagramChannel) key.channel();
        ClientRecord clntRec = (ClientRecord) key.attachment();
        clntRec.buffer.clear();    // Prepare buffer for receiving
        clntRec.clientAddress = channel.receive(clntRec.buffer);
        if (clntRec.clientAddress != null) {  // Did we receive something?
            // Register write with the selector
            key.interestOps(SelectionKey.OP_WRITE);
        }
    }

    public static void handleWrite(SelectionKey key) throws IOException {
        DatagramChannel channel = (DatagramChannel) key.channel();
        ClientRecord clntRec = (ClientRecord) key.attachment();
        clntRec.buffer.flip(); // Prepare buffer for sending
        int bytesSent = channel.send(clntRec.buffer, clntRec.clientAddress);
        if (bytesSent != 0) { // Buffer completely written?
            // No longer interested in writes
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    static class ClientRecord {
        public SocketAddress clientAddress;
        public ByteBuffer buffer = ByteBuffer.allocate(ECHOMAX);
    }
}
