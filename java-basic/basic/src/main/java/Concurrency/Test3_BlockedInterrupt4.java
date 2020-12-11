package Concurrency;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import static Basic.Print.*;

/**
 * Created by Defias on 2020/07.
 * Description: 阻塞 - 中断 - Lock


 ReentrantLock上的阻塞任务具备可以被中断的能力

 lock()   获取锁定，如果没有获取到就会被阻塞，且不可被中断
 lockInterruptibly() 获取锁定，如果没有获取到就会被阻塞，且可被中断interrupted而引发InterruptedException异常
 */

public class Test3_BlockedInterrupt4 {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Blocked2());
        t.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}



class Blocked2 implements Runnable {
    BlockedMutex blocked = new BlockedMutex();
    public void run() {
        print("Waiting for f() in BlockedMutex");
        blocked.f();
        //blocked.f2();
        print("Broken out of blocked call");
    }
}


class BlockedMutex {
    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        // Acquire it right away, to demonstrate interruption
        // of a task blocked on a ReentrantLock:
        lock.lock();
    }

    public void f() {
        try {
            // This will never be available to a second task
            lock.lockInterruptibly(); //  Special call
            print("lock acquired in f()");
        } catch(InterruptedException e) {
            print("Interrupted from lock acquisition in f()");
        }
    }

    public void f2() {
        lock.lock();
        print("lock acquired in f()");
    }
}

