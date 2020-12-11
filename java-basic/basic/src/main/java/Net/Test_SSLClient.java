package Net;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * SSL Client Socket
 */

public class Test_SSLClient {
    //单向认证
    //private static String CLIENT_KEY_STORE = "base/src/main/java/Net/client_ks";

    //双向认证
    private static String CLIENT_KEY_STORE = "base/src/main/java/Net/client_ks";
    private static String CLIENT_KEY_STORE_PASSWORD = "456456";

    public static void main(String[] args) throws Exception {
        // Set the key store to use for validating the server cert.
        System.setProperty("javax.net.ssl.trustStore", CLIENT_KEY_STORE);
        //System.setProperty("javax.net.debug", "ssl,handshake");  //设置SSL握手过程日志输出级别为DEBUG

        Test_SSLClient client = new Test_SSLClient();
        //Socket s = client.clientWithoutCert();
        Socket s = client.clientWithCert();

        PrintWriter writer = new PrintWriter(s.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println("SSL客户端发送数据： hello");
        writer.println("hello");
        writer.flush();
        System.out.println("SSL客户端接收到数据： " + reader.readLine());
        s.close();
    }

    //单向认证
    private Socket clientWithoutCert() throws Exception {
        SocketFactory sf = SSLSocketFactory.getDefault();
        Socket s = sf.createSocket("localhost", 8443);
        return s;
    }

    //双向认证
    private Socket clientWithCert() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        KeyStore ks = KeyStore.getInstance("jceks");

        ks.load(new FileInputStream(CLIENT_KEY_STORE), null);
        KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
        kf.init(ks, CLIENT_KEY_STORE_PASSWORD.toCharArray());
        context.init(kf.getKeyManagers(), null, null);

        SocketFactory factory = context.getSocketFactory();
        Socket s = factory.createSocket("localhost", 8443);
        return s;
    }
}
