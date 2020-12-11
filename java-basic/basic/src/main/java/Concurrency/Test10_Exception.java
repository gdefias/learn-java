package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Defias on 2020/07.
 * Description: 捕获异常

 Thread.UncaughtExceptionHandler
 可以在每个Thread对象上都附着一个异常处理器
 Thread.UncaughtExceptionHandler.uncaughtException会在线程因未捕获的异常而临近死亡时被调用
 */

public class Test10_Exception {
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    //问题
    public static void test1() {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch(RuntimeException ue) { //无法捕获到ExceptionThread中的异常
            // This statement will NOT execute!
            System.out.println("Exception has been handled!");
        }
    }

    //解决方案
    public static void test2() {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread2());
    }

    //更好的方式
    public static void test3() {
        //设置一个统一的默认的未捕获异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
    }
}


class ExceptionThread implements Runnable {
    public void run() {
        throw new RuntimeException();
    }
}


class ExceptionThread2 implements Runnable {
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        //System.out.println("eh = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}


class HandlerThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        //System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        //System.out.println("eh = " + t.getUncaughtExceptionHandler());
        return t;
    }
}

//未捕获异常处理器
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}