package Concurrency;
import java.util.concurrent.*;
import java.io.*;
import static Basic.Print.*;

/**
 * Created by Defias on 2020/07.
 * Description: 阻塞 - 中断

 SleepBlocked是可中断的阻塞
 IOBlocked和SynchronizedBlocked是不可中断的阻塞（对中断无反应，仍然阻塞着）

 */

public class Test3_BlockedInterrupt {
    public static void main(String[] args) throws Exception {
        test1();
        //test2();
    }

    public static void test1() throws Exception {
//        Interrupting.test(new SleepBlocked());
//        Interrupting.test(new IOBlocked(System.in));
        Interrupting.test(new SynchronizedBlocked());

        TimeUnit.SECONDS.sleep(3);
        print("Aborting with System.exit(0)");
        System.exit(0); // ... since last 2 interrupts failed
    }


    //解决IOBlocked阻塞不可中断办的办法：关闭任务在其上发生阻塞的底层资源
    public static void test2() throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
//        ServerSocket server = new ServerSocket(8080);
//        InputStream socketInput = new Socket("localhost", 8080).getInputStream();
//        exec.execute(new IOBlocked(socketInput));
       exec.execute(new IOBlocked(System.in));

        TimeUnit.MILLISECONDS.sleep(100);
        print("Shutting down all threads");
        exec.shutdownNow();

//        TimeUnit.SECONDS.sleep(1);
//        print("Closing " + socketInput.getClass().getName());
//        socketInput.close(); // Releases blocked thread
//        TimeUnit.SECONDS.sleep(1);

        print("Closing " + System.in.getClass().getName());
        System.in.close(); //关闭底层资源，任务将解除阻塞  Releases blocked thread
    }
}

class Interrupting {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException{
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        print("Interrupting " + r.getClass().getName());
        f.cancel(true); // 中断正在执行的任务 Interrupts if running
        print("Interrupt sent to " + r.getClass().getName());
    }

}


class SleepBlocked implements Runnable {
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch(InterruptedException e) {
            print("InterruptedException");
        }
        print("Exiting SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream in;
    public IOBlocked(InputStream is) {
        in = is;
    }

    public void run() {
        try {
            print("Waiting for read():");
            in.read();
        } catch(IOException e) {
            if(Thread.currentThread().isInterrupted()) {
                print("Interrupted from blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }
        print("Exiting IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable {
    public synchronized void f() {
        while(true) // Never releases lock
            Thread.yield();
    }

    public SynchronizedBlocked() {
        new Thread() {
            public void run() {
                print("Thread Trying to call f()");
                f(); // Lock acquired by this thread 此处先拿到锁了
            }
        }.start();
    }

    public void run() {
        print("Trying to call f()");
        f();  //此处拿不到锁而阻塞
        print("Exiting SynchronizedBlocked.run()");
    }
}

