package Net;
import java.net.Socket;
import java.net.SocketException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * Created by Defias on 2020/08.
 * Description: ECHO TCP客户端
 */
public class Test_TCPEchoClient {
    public static void main(String[] args) throws IOException {
        String server = "localhost";       // Server name or IP address
        String strd = "hello YZH！";
        byte[] data = strd.getBytes();
        int servPort = 4442;

        Socket socket = new Socket(server, servPort);
        System.out.println("Connected to server...sending echo string");

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        out.write(data);

        int totalBytesRcvd = 0;  // 累计已经读取的字节数
        int bytesRcvd;           // 每次实际读取到的字节数
        while (totalBytesRcvd < data.length) {
            if ((bytesRcvd = in.read(data, totalBytesRcvd, data.length - totalBytesRcvd)) == -1)
                throw new SocketException("Connection closed prematurely");
            totalBytesRcvd += bytesRcvd;
        }  // data array is full

        System.out.println("Received: " + new String(data));

        socket.close();  // Close the socket and its streams
    }
}
