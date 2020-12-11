package Net.Protocol;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;
/**
 * Created by Defias on 2020/08.
 * Description: 服务器 - 一客户端一线程
 */
public class TCPEchoServerThread {
    public static void main(String[] args) throws IOException {

        int echoServPort = 8081;
        ServerSocket servSock = new ServerSocket(echoServPort);
        Logger logger = Logger.getLogger("practical");

        while (true) {
            Socket clntSock = servSock.accept();
            Thread thread = new Thread(new EchoProtocol(clntSock, logger));
            thread.start();
            logger.info("Created and started Thread " + thread.getName());
        }
    }
}
