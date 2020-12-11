package Net.Protocol;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Defias on 2020/08.
 * Description: 压缩客户端 - 有产生死锁的风险
 */
public class CompressClient {

    public static final int BUFSIZE = 256;  // Size of read buffer

    public static void main(String[] args) throws IOException {

        String server = "localhost";
        int port = Integer.parseInt("8089");
        String filename = "base/src/test";

        FileInputStream fileIn = new FileInputStream(filename);
        FileOutputStream fileOut = new FileOutputStream(filename + ".gz");

        Socket sock = new Socket(server, port);

        //发送未压缩的数据字节流给服务器
        sendBytes(sock, fileIn);

        //从服务器接收已压缩的数据并写入压缩文件中
        InputStream sockIn = sock.getInputStream();
        int bytesRead;
        byte[] buffer = new byte[BUFSIZE];
        while ((bytesRead = sockIn.read(buffer)) != -1) {
            fileOut.write(buffer, 0, bytesRead);
            System.out.print("R");
        }
        System.out.println();

        sock.close();
        fileIn.close();
        fileOut.close();
    }

    private static void sendBytes(Socket sock, InputStream fileIn) throws IOException {
        OutputStream sockOut = sock.getOutputStream();
        int bytesRead;
        byte[] buffer = new byte[BUFSIZE];
        while ((bytesRead = fileIn.read(buffer)) != -1) {
            sockOut.write(buffer, 0, bytesRead);
            System.out.print("W");
        }
        sock.shutdownOutput();
    }
}
