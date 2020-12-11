package Net;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-06
 *
 * 多线程服务端 - 一客户端一线程
 */


public class Test_ThreadServer implements Runnable {
    private Socket client = null;

    public static void main(String[] args) throws Exception{
        //服务端在20006端口监听客户端请求的TCP连接
        ServerSocket server = new ServerSocket(20006);
        Socket client = null;

        boolean f = true;
        while(f) {
            //等待客户端的连接
            client = server.accept();
            System.out.println("与客户端连接成功！");
            //为每个客户端连接开启一个线程
            new Thread(new Test_ThreadServer(client)).start();
        }
        server.close();
    }

    public Test_ThreadServer(Socket client){
        this.client = client;
    }

    public void run() {
        try{
            PrintStream out = new PrintStream(client.getOutputStream());
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag =true;
            while(flag){
                //接收从客户端发送过来的数据
                String str =  buf.readLine();
                System.out.println("收到信息： "+str);
                if(str == null || "".equals(str)){
                    flag = false;
                } else {
                    if("bye".equals(str)) {     //bye表示结束
                        flag = false;
                    } else {
                        //将接收到的字符串前面加上echo，发送到对应的客户端
                        out.println("echo:" + str);
                    }
                }
            }
            out.close();
            client.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
