package Net;
import java.io.*;
import java.net.*;
/**
 * Created by Defias on 2016/2/29.
 *
 * 服务器端套接字  ServerSocket
 */

public class Test1_Server {
    public static void main(String args[]){
        ServerSocket server = null;
        Socket socket = null;
        String s = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        try{
            InetAddress addr = InetAddress.getByName("localhost");
            server = new ServerSocket(4441,1, addr);    //创建服务器端套接字
        } catch(IOException e) {
            System.out.println("ERROR: " + e);
        }

        try{
            System.out.println("Server Starting ...");
            socket = server.accept();      //等待直到客户端请求连接
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            s = in.readUTF();
            System.out.println("收到客户端的信息： " + s);
            out.writeUTF("客户，你好，我是服务器");
            out.close();
        } catch(IOException e) {
            System.out.println("ERROR:"+e);
        }
    }
}