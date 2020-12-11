package Net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Defias on 2016/2/29.
 *
 * 客户端套接字 Socket
 *
 */

public class Test1_Client {
    public static void main(String args[]) {
        String s = null;
        Socket mySocket;
        DataInputStream in = null;
        DataOutputStream out = null;
        try{
            mySocket = new Socket("localhost", 4441);  //创建客户端套接字
            System.out.println("connect success!");
            in = new DataInputStream(mySocket.getInputStream());  //getInputStream: 从套接字获得InputStream流
            out = new DataOutputStream(mySocket.getOutputStream());   //getOutputStream: 从套接字获得OutputStream流
            out.writeUTF("good server!");
            out.flush();
            s = in.readUTF();
            System.out.println(s);
            mySocket.close();
        } catch(IOException e) {
            System.out.println("can't connect!");
        }
    }
}