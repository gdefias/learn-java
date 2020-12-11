package Net;
import java.net.*;
import java.io.*;
/**
 * Created by Defias on 2020/08.
 * Description:  ECHO TCP服务器
 */

public class Test_TCPEchoServer {
    private static final int BUFSIZE = 32;   // Size of receive buffer

    public static void main(String[] args) throws IOException {
        int servPort = 4442;
        ServerSocket servSock = new ServerSocket(servPort);

        int recvMsgSize;   // Size of received message
        byte[] receiveBuf = new byte[BUFSIZE];  // Receive buffer

        while (true) {
            Socket clntSock = servSock.accept();     // Get client connection
            SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
            System.out.println("Handling client at " + clientAddress);

            InputStream in = clntSock.getInputStream();
            OutputStream out = clntSock.getOutputStream();

            // Receive until client closes connection, indicated by -1 return
            while ((recvMsgSize = in.read(receiveBuf)) != -1) {
                out.write(receiveBuf, 0, recvMsgSize);
            }

            clntSock.close();  // Close the socket.  We are done with this client!
        }
    }
}
