package Net;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * SSL Server Socket
 */


public class Test_SSLServer extends Thread {
    private Socket socket;
    private static String SERVER_KEY_STORE = "base/src/main/java/Net/server_ks";
    private static String SERVER_KEY_STORE_PASSWORD = "123123";

    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.ssl.trustStore", SERVER_KEY_STORE);

        KeyStore ks = KeyStore.getInstance("jceks");
        ks.load(new FileInputStream(SERVER_KEY_STORE), null);
        KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");  //SunX509标准
        kf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());

        //返回一个 SSLContext指定安全套接字协议的 SSLContext对象
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(kf.getKeyManagers(), null, null);

        ServerSocketFactory factory = context.getServerSocketFactory();
        ServerSocket _socket = factory.createServerSocket(8443);
        //((SSLServerSocket) _socket).setNeedClientAuth(false); //单向认证
        ((SSLServerSocket) _socket).setNeedClientAuth(true); //双向认证

        System.out.println("SSL服务端启动...");
        while (true) {
            new Test_SSLServer(_socket.accept()).start();
        }
    }


    public Test_SSLServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            String data = reader.readLine();
            writer.println(data);
            writer.close();
            socket.close();
        } catch (IOException e) {

        }
    }
}
