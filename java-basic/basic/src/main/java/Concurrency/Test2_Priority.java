package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2020/07.
 * Description: 线程优先级

 不要误以为优先级越高就先执行。谁先执行还是取决于谁先取得CPU的资源。优先级高的线程获取CPU资源的概率较大而已
 操作系统对高优先级线程可能调度更频繁，但我们决不能通过设置优先级来确保高优先级的线程一定会先执行

 Thread.setPriority(int n) // 1~10, 默认值5
 Java线程的优先级用1~10（MIN_PRIORITY~MAX_PRIORITY）之间的整数表示，数值越大优先级越高，默认的优先级为5（NORM_PRIORITY）
 不同操作系统的线程优先级数量是不一样的，所以Java的优先级与操作系统的优先级并不能很好的一一对应

 最好是只使用：MIN_PRIORITY、MAX_PRIORITY、NORM_PRIORITY三个优先级

 */

public class Test2_Priority {
    public static void main(String[] args) throws InterruptedException {
        //test1();
        test2();
    }

    public static void test1() {
        ExecutorService exec = Executors.newCachedThreadPool();

        for(int i = 0; i < 5; i++)
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));

        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }

    public static void test2() throws InterruptedException {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        clicker hi = new clicker(Thread.NORM_PRIORITY + 2);
        clicker lo = new clicker(Thread.NORM_PRIORITY - 2);

        hi.start();
        lo.start();

        TimeUnit.MILLISECONDS.sleep(1000);

        hi.stop();
        lo.stop();

        try {
            hi.t.join();
            lo.t.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }

        //每次运行结果可能都不一样
        System.out.println("Low-priority thread: " + lo.click);
        System.out.println("High-priority thread: " + hi.click);
    }
}


class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d; // No optimization
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    public void run() {
        //可以在一个任务内部通过Thread.currentThread()来获得对驱动该任务的Thread对象的引用
        Thread.currentThread().setPriority(priority);
        while(true) {
            // An expensive, interruptable operation:
            for(int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double)i;
                if(i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);

            if(--countDown == 0)
                return;
        }
    }
}



class clicker implements Runnable {
    int click = 0;
    Thread t;

    //如果不用volatile（易变的），Java可以自由的优化循环：running的值可能被存放到CPU的一个寄存器中
    //volatile阻止了该优化，告知Java running是易变的，不需要放在寄存器中
    private volatile boolean running = true;

    public clicker(int p) {
        t = new Thread(this);
        t.setPriority(p);
    }

    public void run() {
        while (running) {
            click++;
        }
    }

    public void stop() {
        running = false;
    }

    public void start() {
        t.start();
    }
}