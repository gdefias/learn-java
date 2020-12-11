package Concurrency;

import Concurrency.task.MyThread1;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2017/3/17.

 线程组 - ThreadGroup

 ThreadGroup是为了方便线程管理出现了，可以统一设定线程组的一些属性，比如setDaemon，设置未处理异常的处理方法，设置统一的安全策略等等；
 也可以通过线程组方便的获得线程的一些信息

 线程组和线程池的区别：
 线程组和线程池是两个不同的概念，他们的作用完全不同
 前者是为了方便线程的管理
 后者是为了管理线程的生命周期，复用线程，减少创建销毁线程的开销

 根线程组
 当前线程的线程组的父线程组是系统线程组；系统线程组的父线程组不存在；系统线程组就是根线程组
 */


public class Test2_ThreadGroup {
    public static void main(String args[]) throws Exception {
        //test1();
        test2();
    }

    public static void test1() {
        MyThread1 mt0 = new MyThread1();
        MyThread1 mt1 = new MyThread1();
        ThreadGroup tg = new ThreadGroup("新建线程组1");
        Thread t0 = new Thread(tg, mt0);
        Thread t1 = new Thread(tg, mt1);
        t0.start();
        t1.start();
        System.out.println("活动的线程数为：" + tg.activeCount());
        System.out.println("线程组的名称为：" + tg.getName());

        try {
            TimeUnit.MILLISECONDS.sleep(4000);
            tg.interrupt();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
    }

    public static void test2() {
        printGroupInfo(Thread.currentThread());

        Thread appThread = new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i=0;i<5;i++) {
                    System.out.println("do loop " + i);
                }
            }
        });
        appThread.setName("appThread");
        appThread.start();
        printGroupInfo(appThread);
    }


    //打印线程组信息
    public static void printGroupInfo(Thread t) {
        ThreadGroup group = t.getThreadGroup();
        System.out.println("thread name: " + t.getName() + "\ngroup name: "
                + group.getName()+ "\nmax priority: " + group.getMaxPriority()
                + "\nthread count: " + group.activeCount());

        //迭代打印父线程组信息
        ThreadGroup parent = group;
        do {
            ThreadGroup current = parent;
            parent = parent.getParent();
            if (parent == null) {
                break;
            }

            System.out.println(current.getName() + "'s parent: " + parent.getName());
        } while (true);
        System.out.println();
    }
}




