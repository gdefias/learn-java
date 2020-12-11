package Net;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * Java Client Socket
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Test0_Client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 8080);
        PrintWriter writer = new PrintWriter(s.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        System.out.println("客户端发送数据： hello");
        writer.println("hello");
        writer.flush();

        System.out.println("客户端接收到数据： " + reader.readLine());
        s.close();
    }
}
