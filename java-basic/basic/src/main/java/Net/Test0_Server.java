package Net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * Java Server Socket
 */
public class Test0_Server extends Thread {
    private Socket socket;

    public static void main(String[] args) throws Exception {
        ServerSocket servSock = new ServerSocket(8080);
        System.out.println("服务端启动...");
        while (true) {
            Socket clntSock = servSock.accept();
            Thread sercicethread = new Test0_Server(clntSock);
            sercicethread.start();
            sercicethread.join();
            clntSock.close();
        }
    }

    public Test0_Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            String data = reader.readLine();
            System.out.println("服务端接收到数据： " + data);
            String senddata = "你好! " + data;
            System.out.println("服务端发送数据： " + senddata);
            writer.println(senddata);
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
