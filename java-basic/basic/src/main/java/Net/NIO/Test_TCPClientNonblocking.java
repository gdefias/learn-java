package Net.NIO;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
/**
 * Created by Defias on 2020/08.
 * Description:  非阻塞式TCP回显客户端
 */
public class Test_TCPClientNonblocking {

    public static void main(String args[]) throws Exception {
        String server = "localhost"; // Server name or IP address
        String argdate = "Hello YZH!";
        byte[] argument = argdate.getBytes();

        int servPort = 8086;

        SocketChannel clntChan = SocketChannel.open();
        clntChan.configureBlocking(false);

        if (!clntChan.connect(new InetSocketAddress(server, servPort))) {
            while (!clntChan.finishConnect()) {
                System.out.print(".");
            }
        }
        ByteBuffer writeBuf = ByteBuffer.wrap(argument);
        ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
        int totalBytesRcvd = 0;
        int bytesRcvd;
        while (totalBytesRcvd < argument.length) {
            if (writeBuf.hasRemaining()) {
                clntChan.write(writeBuf);
            }
            if ((bytesRcvd = clntChan.read(readBuf)) == -1) {
                throw new SocketException("Connection closed prematurely");
            }
            totalBytesRcvd += bytesRcvd;
            System.out.print(".");
        }

        System.out.println("Received: " + new String(readBuf.array(), 0, totalBytesRcvd));
        clntChan.close();
    }
}
