package Concurrency;

import Concurrency.task.NewThread2;
import Concurrency.task.NewThread3;

/**
 * Created by Defias on 2020/07.
 * Description: join一个线程

 一个线程可以在其他线程之上调用t.join()方法，其效果是等待一段时间直到第二个线程结束才继续执行，此线程将被挂起，直到目标线程t结束才恢复
 （即t.isAlive()返回假）

 join过程中可以被interrupt()中断
 sleep过程中也可以被interrupt()中断
 interrupt()方法仅仅向线程发出了“中断请求”，至于线程是否能立刻响应，要看具体代

 另一个常用的中断线程的方法是设置标志位
 可以用一个running标志位标识线程是否应该继续运行，然后在外部线程中，通过把HelloThread.running置为false，就可以让线程中断而结束
 */

public class Test2_join {
    public static void main(String[] args) throws InterruptedException {
        test1();
        //test2();
        //test3();
        //test4();
    }


    public static void test1() {
        Sleeper
                sleepy = new Sleeper("Sleepy", 1500),
                grumpy = new Sleeper("Grumpy", 1500);

        Joiner
                dopey = new Joiner("Dopey", sleepy),
                doc = new Joiner("Doc", grumpy);
        //doc.interrupt();
        grumpy.interrupt();
    }

    public static void test2() {
        NewThread2 t1 = new NewThread2("One");
        NewThread2 t2 =  new NewThread2("Two");
        NewThread2 t3 =  new NewThread2("Three");
        System.out.println("Thread One is alive: "+ t1.t.isAlive());
        System.out.println("Thread Two is alive: "+ t2.t.isAlive());
        System.out.println("Thread Three is alive: "+ t3.t.isAlive());

        //主线程 -join子线程以等待子线程结束后再执行
        //如果主线程不等待子线程结束而自己先运行结束，不会影响子线程的执行，除非子线程是守护线程
        try {
            System.out.println("Waiting for threads to finish.");
            //Thread.sleep(10000);
            t1.t.join();
            t2.t.join();
            t3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Thread One is alive: "+ t1.t.isAlive());
        System.out.println("Thread Two is alive: "+ t2.t.isAlive());
        System.out.println("Thread Three is alive: "+ t3.t.isAlive());
        System.out.println("Main thread exiting.");
    }

    public static void test3() {
        new NewThread3();

        //主线程 -不join子线程
        try {
            for(int i=5; i>0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }

    public static void test4() throws InterruptedException {
        Thread t = new MyThread();
        t.start();
        Thread.sleep(1000);
        t.interrupt(); // 中断t线程
        t.join(); // 等待t线程结束
        System.out.println("end");
    }

    public static void test5() throws InterruptedException {
        HelloThread2 t = new HelloThread2();
        t.start();
        Thread.sleep(1);
        t.running = false; // 标志位置为false
    }


}

class Sleeper extends Thread {
    private int duration;
    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }
    public void run() {
        try {
            sleep(duration);
        } catch(InterruptedException e) {
            System.out.println(getName() + " was interrupted. " +
                    "isInterrupted(): " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened");
    }
}


class Joiner extends Thread {
    private Sleeper sleeper;
    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }
    public void run() {
        try {
            sleeper.join();  //Sleeper对象被中断或正常结束，join结束
        } catch(InterruptedException e) {
            System.out.println(getName() + " join Interrupted");
            return;
        }
        System.out.println(getName() + " join completed");
    }
}


class MyThread extends Thread {
    public void run() {
        Thread hello = new HelloThread();
        hello.start(); // 启动hello线程
        try {
            hello.join(); // 等待hello线程结束
        } catch (InterruptedException e) {
            System.out.println("interrupted!");
        }
        hello.interrupt();
    }
}

class HelloThread extends Thread {
    public void run() {
        int n = 0;
        while (!isInterrupted()) {  //通过isInterrupted()检测标志 获取自身是否已中断
            n++;
            System.out.println(n + " hello!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}



class HelloThread2 extends Thread {
    //线程间共享的变量。线程间共享变量需要使用volatile关键字标记，确保每个线程都能读取到更新后的变量值
    public volatile boolean running = true;

    public void run() {
        int n = 0;
        while (running) {
            n ++;
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}