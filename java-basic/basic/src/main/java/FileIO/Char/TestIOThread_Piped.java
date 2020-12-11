package FileIO.Char;
import java.util.concurrent.*;
import java.io.*;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 管道通信

 PipedReader 管道输入流
 PipedWriter 管道输出流

 PipedReader与普通的输入流之间一个重要的区别是：PipedReader是可中断的

 管道基本上是一个种阻塞队列，存在于多个引入BlockingQueue之前的Java版本中
 */
public class TestIOThread_Piped {
    public static void main(String[] args) throws Exception {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);

        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
        System.out.println("--main end--");
    }
}


class Sender implements Runnable {
    private Random rand = new Random(47);
    private PipedWriter out = new PipedWriter();

    public PipedWriter getPipedWriter() {
        return out;
    }

    public void run() {
        try {
            while(true)
                for(char c = 'A'; c <= 'z'; c++) {
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
        } catch(IOException e) {
            System.out.println(e + " Sender write exception");
        } catch(InterruptedException e) {
            System.out.println(e + " Sender sleep interrupted");
        }
    }
}

class Receiver implements Runnable {
    private PipedReader in;
    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getPipedWriter());
    }
    public void run() {
        try {
            while(true) {
                // Blocks until characters are there:
                System.out.print("Read: " + (char)in.read() + ", ");
            }
        } catch(IOException e) {
            System.out.println(e + " Receiver read exception");
        }
    }
}


