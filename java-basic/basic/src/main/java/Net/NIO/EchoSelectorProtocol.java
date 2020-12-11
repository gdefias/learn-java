package Net.NIO;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.ByteBuffer;
import java.io.IOException;
/**
 * Created by Defias on 2020/08.
 * Description: ECHO回显服务TCP协议
 */

public class EchoSelectorProtocol implements TCPProtocol {

    private int bufSize; // Size of I/O buffer

    public EchoSelectorProtocol(int bufSize) {
        this.bufSize = bufSize;
    }

    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel clntChan = ((ServerSocketChannel) key.channel()).accept();
        clntChan.configureBlocking(false); // Must be nonblocking to register
        clntChan.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufSize));
    }

    public void handleRead(SelectionKey key) throws IOException {
        SocketChannel clntChan = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        long bytesRead = clntChan.read(buf);
        if (bytesRead == -1) { // Did the other end close?
            clntChan.close();
        } else if (bytesRead > 0) {
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }

    public void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip(); // Prepare buffer for writing
        SocketChannel clntChan = (SocketChannel) key.channel();
        clntChan.write(buf);
        if (!buf.hasRemaining()) { // Buffer completely written?
            // Nothing left, so no longer interested in writes
            key.interestOps(SelectionKey.OP_READ);
        }
        buf.compact(); // Make room for more data to be read in
    }

}