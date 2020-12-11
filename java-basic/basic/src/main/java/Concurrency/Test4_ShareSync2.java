package Concurrency;

/**
 * Created by Defias on 2020/07.
 * Description:  解决共享资源竞争

 使用synchronized块
 
 通用形式：
 synchronized(object) { 
    //statements to be synchronized 
 }
 object是被同步对象的引用。如果要同步的只是一个语句那么可以不要花括号
 
 */

public class Test4_ShareSync2 {
    public static void main(String args[]) {
        //test1();
        test2();
    }

    public static void test1() {
        Callme target = new Callme();
        Caller ob1 = new Caller(target, "Hello");
        Caller ob2 = new Caller(target, "Synchronized");
        Caller ob3 = new Caller(target, "World");

        // wait for threads to end
        try {
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    public static void test2() {
        final DualSynch ds = new DualSynch();
        new Thread() {
            public void run() {
                ds.f();
                //ds.f2();
            }
        }.start();
        ds.g();
    }
}


class Caller implements Runnable {
    String msg;
    Callme target;
    Thread t;
    public Caller(Callme targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }

    // synchronize calls to call()
    public void run() {
        synchronized(target) { // synchronized block 同步块   //锁住target
            target.call(msg);
        }
    }
}


class Callme {
    //非原子操作
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println(msg + "]");
    }
}


//f()上的同步与g()上的同步是相互独立的
class DualSynch {
    private Object syncObject = new Object();

    public synchronized void f() {
        for(int i = 0; i < 5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void f2() {
        synchronized(syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("f()");
                Thread.yield();
            }
        }
    }

    public void g() {
        synchronized(syncObject) {
            for(int i = 0; i < 5; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }

    public synchronized void g2() {
        for(int i = 0; i < 5; i++) {
            System.out.println("g()");
            Thread.yield();
        }
    }
}
