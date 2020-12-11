package Concurrency;

import Concurrency.task.LiftOff;
import Concurrency.task.SleepingTask;
import Concurrency.task.Tasks;

import java.util.concurrent.*;

/**
 * Created by Defias on 2020/07.
 * Description: 使用执行器Executor（ExecutorService） 管理Thread对象
 *
 *
 * 如果执行过程中遇到了问题（error/exception）,那么后面的定时任务也就不会继续执行了
 * 一个解决办法：编写一个wrap类，封装ThreadPoolExecutor，在这个类里面进行try/catch。这样外部就不用try/catch了
 */

public class Test7_Executors {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        test6();
    }

    public static void test0() {
        ExecutorService threadPool = new ThreadPoolExecutor(2,5,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for(int i = 0; i < 5; i++)
            threadPool.execute(new LiftOff());
        threadPool.shutdown();
    }



    public static void test1() {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
            exec.execute(new LiftOff());  //接受Runnable对象
        exec.shutdown(); //启动有序关闭，先前提交的任务将被执行，但不会接受任何新任务
    }


    public static void test2() {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }

    public static void test3() {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }


    public static void test4() {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
            exec.execute(new SleepingTask());
        exec.shutdown();
    }

    public static void test5() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new Tasks.PrintChar('a', 1000));
        executor.execute(new Tasks.PrintChar('b', 1000));
        executor.execute(new Tasks.PrintNum(1000));
        executor.shutdown();
    }


    //6个任务共享线程池中的3个线程
    public static void test6() {
        ExecutorExample ee = new ExecutorExample();
        ee.setExecutor(Executors.newFixedThreadPool(3));
        ee.executeTasks();
    }


    static class ExecutorExample {
        private Executor executor;
        public void setExecutor(Executor executor) {
            this.executor = executor;
        }
        public void executeTasks() {
            for (int i = 0; i < 6; i++) {
                executor.execute(new SimpleTask("task" + i));
            }
        }
    }

    static class SimpleTask implements Runnable {
        private String taskName;
        public SimpleTask(String taskName) {
            this.taskName = taskName;
        }
        public void run() {
            System.out.println("do " + taskName + "... in Thread:"
                    + Thread.currentThread().getId());
        }
    }

}



