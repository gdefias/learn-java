package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2020/07.
 * Description: 守护线程/后台线程

 是指在程序运行的时候在后台提供一种通用服务的线程，并且这种线程并不属于程序中农不可或缺的部分
 当所有的非后台线程结束时，程序也就终止了，同时会杀死进程中的所有后台进程，反过来，只要有任何非后台线程还在运行，程序就不会终止


 意义
 当主线程结束时，其余的子线程（守护线程）自动结束，就免去了还要继续关闭子线程的麻烦
 如：Java垃圾回收线程就是一个典型的守护线程

 守护线程不能持有任何需要关闭的资源，例如打开文件等，因为虚拟机退出时，守护线程可能没有任何机会来关闭文件而导致数据丢失

 */

public class Test2_Daemon {
    public static void main(String[] args) throws Exception {
        //test1();
        //test2();
        //test3();
        test4();
    }

    public static void test1() throws Exception {
        for(int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true); // Must call before start()
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(150);  //主线程若不sleep等待而直接结束，那么所有的守护线程都将被杀死
    }

    public static void test2() throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for(int i = 0; i < 10; i++)
            exec.execute(new DaemonFromFactory());
        System.out.println("All daemons started");
        //TimeUnit.MILLISECONDS.sleep(500); //主线程若不sleep等待而直接结束，那么所有的守护线程都将被杀死
    }

    public static void test3() throws Exception {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.print("d.isDaemon() = " + d.isDaemon() + ", ");
        // Allow the daemon threads to
        // finish their startup processes:
        TimeUnit.SECONDS.sleep(1);
    }


    public static void test4() throws Exception {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}


class SimpleDaemons implements Runnable {
    public void run() {
        try {
            while(true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch(InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }
}


class DaemonFromFactory implements Runnable {
    public void run() {
        try {
            while(true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}

//专门将Runnable对象包装成守护线程的工厂
class DaemonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}



class Daemon implements Runnable {
    private Thread[] t = new Thread[10];
    public void run() {
        for(int i = 0; i < t.length; i++) {
            //在守护线程中创建出的子线程，即使没有显示设置setDaemon(true)为守护线程也还是守护线程
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            System.out.print("DaemonSpawn " + i + " started, ");
        }
        for(int i = 0; i < t.length; i++)
            System.out.print("t[" + i + "].isDaemon() = " +
                    t[i].isDaemon() + ", ");
        while(true)
            Thread.yield();
    }
}

class DaemonSpawn implements Runnable {
    public void run() {
        while(true)
            Thread.yield();
    }
}


//守护线程终止是不会执行finally子句的
class ADaemon implements Runnable {
    public void run() {
        try {
            System.out.println("Starting ADaemon");
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            System.out.println("Exiting via InterruptedException");
        } finally {
            System.out.println("This should always run?");
        }
    }
}