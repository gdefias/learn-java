package Net.Protocol;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/**
 * Created by Defias on 2020/08.
 * Description: 压缩客户端 - 无死锁版本

 解决死锁问题方案之一： 在不同的线程中执行客户端的write循环和read循环
 （解决死锁问题也可以不使用多线程，而是使用非阻塞Channel和Selector）
 */
public class CompressClientNoDeadlock {

    public static final int BUFSIZE = 256;

    public static void main(String[] args) throws IOException {

        String server = "localhost";
        int port = Integer.parseInt("8089");
        String filename = "base/src/test";

        final FileInputStream fileIn = new FileInputStream(filename);
        FileOutputStream fileOut = new FileOutputStream(filename + ".gz");

        final Socket sock = new Socket(server, port);

        //单独的线程中发送未压缩的数据字节流给服务器
        Thread thread = new Thread() {
            public void run() {
                try {
                    SendBytes(sock, fileIn);
                } catch (Exception ignored) {}
            }
        };
        thread.start();

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

    public static void SendBytes(Socket sock, InputStream fileIn)
            throws IOException {

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
