package Concurrency.atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Defias on 2020/07.
 * Description: 原子性变量类

 通过使用AtomicInteger/AtomicBoolean而消除了synchronized关键字

 public final boolean compareandset(boolean expect, boolean update)
 如果当前值==预期值，则以原子方式将该值设置为给定的更新值
 返回：如果成功，则返回true。返回false指示实际值与预期值不相等
 */

public class TestAtomicityAtomic {
    public static void main(String[] args) {
        //test1();
        test2();
    }


    public static void test1() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 5000); // Terminate after 5 seconds

        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest ait = new AtomicIntegerTest();
        exec.execute(ait);

        while(true) {
            int val = ait.getValue();
            if(val % 2 != 0) {  //不会检测到奇数
                System.out.println(val);
                System.exit(0);
            }
        }
    }

    public static void test2() {
        Thread thread1 =  new Thread(new NormalBoolean3("张三"));
        Thread thread2 =  new Thread(new NormalBoolean3("李四"));
        Thread thread3 =  new Thread(new NormalBoolean3("王五"));
        thread1.start();
        thread2.start();
        thread3.start();
    }
}


class AtomicIntegerTest implements Runnable {
    private AtomicInteger i = new AtomicInteger(0);
    public int getValue() {
        return i.get();
    }

    private void evenIncrement() {
        i.addAndGet(2);
    }

    public void run() {
        while(true)
            evenIncrement();
    }
}


class NormalBoolean3 implements Runnable {
    public static AtomicBoolean exits = new AtomicBoolean(false);
    private String name;
    public NormalBoolean3(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if(exits.compareAndSet(false,true)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ",step 1");

            System.out.println(name + ",step 2");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ",step 3");
            exits.set(false);
        } else {
            System.out.println(name + ",step else");
        }
    }
}