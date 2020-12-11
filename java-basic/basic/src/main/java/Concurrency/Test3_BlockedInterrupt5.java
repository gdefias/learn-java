package Concurrency;
import java.util.concurrent.*;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: 阻塞 - 中断

 检查中断
 interrupted: 测试当前线程是否处于中断状态，若处于中断状态则还会清除线程的中断状态
 isInterrupted： 测试这个线程是否被中断。 线程的中断状态不受此方法的影响

 */
public class Test3_BlockedInterrupt5 {
    public static void main(String[] args) throws Exception {
        int sleeptime = 3000;
        Thread t = new Thread(new Blocked3());
        t.start();

        TimeUnit.MILLISECONDS.sleep(new Integer(sleeptime));
        t.interrupt();
    }
}


class Blocked3 implements Runnable {
    private volatile double d = 0.0;
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // point1
                NeedsCleanup n1 = new NeedsCleanup(1);
                // Start try-finally immediately after definition
                // of n1, to guarantee proper cleanup of n1:
                try {
                    print("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    // point2
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    // Guarantee proper cleanup of n2:
                    try {
                        print("Calculating");
                        // A time-consuming, non-blocking operation:
                        for(int i = 1; i < 2500000; i++)
                            d = d + (Math.PI + Math.E) / d;
                        print("Finished time-consuming operation");
                    } finally {
                        n2.cleanup();  //总是会清理资源
                    }
                } finally {
                    n1.cleanup(); //总是会清理资源
                }
            }
            print("Exiting via while() test");
        } catch(InterruptedException e) {
            print("Exiting via InterruptedException");
        }
    }
}



class NeedsCleanup {
    private final int id;
    public NeedsCleanup(int ident) {
        id = ident;
        print("NeedsCleanup " + id);
    }

    public void cleanup() {
        print("Cleaning up " + id);
    }
}

