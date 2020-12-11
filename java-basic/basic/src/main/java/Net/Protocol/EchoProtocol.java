package Net.Protocol;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by Defias on 2020/08.
 * Description:  协议 - Echo协议
 */

public class EchoProtocol implements Runnable {
    private static final int BUFSIZE = 32; // Size (in bytes) of I/O buffer
    private Socket clntSock;               // Socket connect to client
    private Logger logger;                 // Server logger

    public EchoProtocol(Socket clntSock, Logger logger) {
        this.clntSock = clntSock;
        this.logger = logger;
    }

    //封装了对每个客户端的处理过程
    public static void handleEchoClient(Socket clntSock, Logger logger) {
        try {
            InputStream in = clntSock.getInputStream();
            OutputStream out = clntSock.getOutputStream();

            int recvMsgSize; // Size of received message
            int totalBytesEchoed = 0; // Bytes received from client
            byte[] echoBuffer = new byte[BUFSIZE]; // Receive Buffer
            while ((recvMsgSize = in.read(echoBuffer)) != -1) {
                out.write(echoBuffer, 0, recvMsgSize);
                totalBytesEchoed += recvMsgSize;
            }

            logger.info("Client " + clntSock.getRemoteSocketAddress() + ", echoed "
                    + totalBytesEchoed + " bytes.");

        } catch (IOException ex) {
            logger.log(Level.WARNING, "Exception in echo protocol", ex);
        } finally {
            try {
                clntSock.close();
            } catch (IOException e) {
            }
        }
    }

    public void run() {
        handleEchoClient(clntSock, logger);
    }
}