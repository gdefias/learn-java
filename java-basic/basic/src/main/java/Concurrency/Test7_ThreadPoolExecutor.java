package Concurrency;

import Concurrency.task.Tasks;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by Defias on 2017/8/27.
 *
 * 可以直接使用ThreadPoolExecutor 而不通过Executors工具
 */
public class Test7_ThreadPoolExecutor {
    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1() {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(5, 50, 200,
                        TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        for(int i=0;i<50;i++){
            Tasks.MyTask myTask = new Tasks.MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();  //所有线程都执行完成才结束
    }


    public static void test2() {
        DaemonThreadPoolExecutor executor = new DaemonThreadPoolExecutor();

        for(int i=0;i<50;i++){
            Tasks.MyTask myTask = new Tasks.MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown(); //某些守护线程可能还没执行完就结束了
    }
}


class DaemonThreadPoolExecutor extends ThreadPoolExecutor {
    public DaemonThreadPoolExecutor() {
        super(0, Integer.MAX_VALUE, 60L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new DaemonThreadFactory());
    }
}

