package Concurrency;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.concurrent.*;
import java.io.*;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: 阻塞 - 中断

 输入IOBlocked阻塞时不可中断的
 但NIO提供了更人性化的IO中断，被阻塞的NIO通道会自动的响应中断

 */
public class Test3_BlockedInterrupt2 {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();

        ServerSocket server = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
        //SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);
        //Future<?> f = exec.submit(new NIOBlocked(sc1));
        exec.execute(new NIOBlocked(sc2));
        exec.shutdown();

        //TimeUnit.SECONDS.sleep(1);
        // Produce an interrupt via cancel:
        //f.cancel(true);  //引发ClosedByInterruptException中断异常

        TimeUnit.SECONDS.sleep(1);
        // Release the block by closing the channel:
        sc2.close();  //引发AsynchronousCloseException中断异常
    }
}


class NIOBlocked implements Runnable {
    private final SocketChannel sc;
    public NIOBlocked(SocketChannel sc) {
        this.sc = sc;
    }

    public void run() {
        try {
            print("Waiting for read() in " + this);
            sc.read(ByteBuffer.allocate(1));
        } catch(ClosedByInterruptException e) {
            print("ClosedByInterruptException");
        } catch(AsynchronousCloseException e) {
            print("AsynchronousCloseException");
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        print("Exiting NIOBlocked.run() " + this);
    }
}