package Net.NIO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
/**
 * Created by Defias on 2020/08.
 * Description: TCP ECHO服务器
 */
public class Test_TCPServerSelector {
    private static final int BUFSIZE = 256;  // Buffer size (bytes)
    private static final int TIMEOUT = 3000; // Wait timeout (milliseconds)

    public static void main(String[] args) throws IOException {

        String[] argsport = new String[] {"8083", "8086", "8089"};

        Selector selector = Selector.open();

        for (String arg : argsport) {
            ServerSocketChannel listnChannel = ServerSocketChannel.open();
            listnChannel.socket().bind(new InetSocketAddress(Integer.parseInt(arg)));
            listnChannel.configureBlocking(false); // must be nonblocking to register

            listnChannel.register(selector, SelectionKey.OP_ACCEPT);
        }

        TCPProtocol protocol = new EchoSelectorProtocol(BUFSIZE);

        while (true) {
            // Wait for some channel to be ready (or timeout)
            if (selector.select(TIMEOUT) == 0) {
                System.out.print(".");
                continue;
            }

            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                if (key.isAcceptable()) {
                    protocol.handleAccept(key);
                }

                if (key.isReadable()) {
                    protocol.handleRead(key);
                }

                if (key.isValid() && key.isWritable()) {
                    protocol.handleWrite(key);
                }
                keyIter.remove();
            }
        }
    }
}
