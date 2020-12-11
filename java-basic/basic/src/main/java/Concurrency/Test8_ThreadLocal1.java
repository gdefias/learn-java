package Concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2020/07.
 * Description: 线程的本地存储 - ThreadLocal

 可以为使用相同变量的每个不同的线程都创建不同的存储

 ThreadLocal类
 public void set(T value)：将值放入线程局部变量中
 public T get()：从线程局部变量中获取值
 public void remove()：从线程局部变量中移除值（有助于JVM垃圾回收）
 protected T initialValue()：返回线程局部变量中的初始值（默认为null）
 */

public class Test8_ThreadLocal1 {
    public static void main(String[] args) throws Exception {
       // test1();
        test2();
    }

    public static void test1() throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
            exec.execute(new Accessor(i));
        TimeUnit.SECONDS.sleep(3);  // Run for a while
        exec.shutdownNow();         // All Accessors will quit
    }


    public static void test2(){
        MyRunnable instance=new MyRunnable();
        Thread thread1=new Thread(instance);
        Thread thread2=new Thread(instance);

        thread1.start();
        thread2.start();
    }

}



class Accessor implements Runnable {
    private final int id;
    public Accessor(int idn) {
        id = idn;
    }

    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }
    public String toString() {
        return "#" + id + ": " +
                ThreadLocalVariableHolder.get();
    }
}

class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value =
            new ThreadLocal<Integer>() {
                private Random rand = new Random(47);
                protected synchronized Integer initialValue() {
                    return rand.nextInt(10000);
                }
            };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }
}


class MyRunnable implements Runnable{
    private ThreadLocal threadlocal = new ThreadLocal();

    @Override
    public void run(){
        threadlocal.set((int)(Math.random()*1000));
        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        //两个线程都无法看到对方保存的值。也就是说，它们存取的是两个不同的值
        System.out.println(threadlocal.get());
    }
}