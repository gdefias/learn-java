package FileIO.Char;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
/**
 * Created with IntelliJ IDEA.
 * Description:  管道通信
 * User: Defias
 * Date: 2019-09

 PipedReader与PipedWriter  线程有关

 管道输入流
 它和管道输出流(PipedWriter)对象绑定，从而可以接收“管道输出流”的数据

 管道输出流
 它和管道输入流(PipedReader)对象绑定，从而可以将数据发送给“管道输入流”，然后用户可以从“管道输入流”读取数据
 */

@SuppressWarnings("all")
public class TestIOThread_Piped1 {
    public static void main(String[] args) {
        Sender1 t1 = new Sender1();
        Receiver1 t2 = new Receiver1();
        PipedWriter out = t1.getWriter();
        PipedReader in = t2.getReader();

        try {
            //管道连接。下面2句话的本质是一样的
            //out.connect(in);
            in.connect(out);

            //两个线程并发地运行，多次启动一个线程是非法的。特别是当线程已经结束执行后，不能再重新启动
            t1.start();
            t2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


//接收者线程
@SuppressWarnings("all")
class Receiver1 extends Thread {

    // 管道输入流对象
    private PipedReader in = new PipedReader();

    // 获得管道输入流对象
    public PipedReader getReader(){
        return in;
    }

    @Override
    public void run(){
        readMessageOnce() ;
        //readMessageContinued() ;
    }

    // 从管道输入流中读取1次数据
    public void readMessageOnce(){
        // 虽然buf的大小是2048个字符，但最多只会从“管道输入流”中读取1024个字符
        // 因为，管道输入流的缓冲区大小默认只有1024个字符
        char[] buf = new char[2048];
        try {
            int len = in.read(buf);
            System.out.println(new String(buf,0, len));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从管道输入流读取>1024个字符时，就停止读取
    public void readMessageContinued(){
        int total=0;
        while(true) {
            char[] buf = new char[1024];
            try {
                int len = in.read(buf);
                total += len;
                System.out.println(new String(buf,0,len));
                // 若读取的字符总数>1024，则退出循环
                if (total > 1024)
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//发送者线程
@SuppressWarnings("all")
class Sender1 extends Thread {

    // 管道输出流对象
    private PipedWriter out = new PipedWriter();

    // 获得管道输出流对象
    public PipedWriter getWriter(){
        return out;
    }

    @Override
    public void run(){
        writeShortMessage();
        //writeLongMessage();
    }

    // 向管道输出流中写入一则较简短的消息："this is a short message"
    private void writeShortMessage() {
        String strInfo = "this is a short message" ;
        try {
            out.write(strInfo.toCharArray());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 向管道输出流中写入一则较长的消息
    private void writeLongMessage() {
        StringBuilder sb = new StringBuilder();
        // 通过for循环写入1020个字符
        for (int i=0; i<102; i++)
            sb.append("0123456789");
        //再写入26个字符
        sb.append("abcdefghijklmnopqrstuvwxyz");
        //str的总长度是1020+26=1046个字符
        String str = sb.toString();
        try {
            //将1046个字符写入到“管道输出流”中
            out.write(str);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}