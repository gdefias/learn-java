package Net.NIO;
import java.nio.channels.SelectionKey;
import java.io.IOException;
/**
 * Created by Defias on 2020/08.
 * Description: TCP协议
 */
public interface TCPProtocol {
    void handleAccept(SelectionKey key) throws IOException;
    void handleRead(SelectionKey key) throws IOException;
    void handleWrite(SelectionKey key) throws IOException;
}
