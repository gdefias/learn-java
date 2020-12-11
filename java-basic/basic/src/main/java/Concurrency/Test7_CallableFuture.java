package Concurrency;

import Concurrency.task.MyCallable;
import Concurrency.task.TaskWithResult;
import Concurrency.task.Tasks;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by Defias on 2020/07.
 * Description: 创建线程 - 实现Callable接口
 *
 * 从任务中产生返回值
 */


public class Test7_CallableFuture {
    public static void main(String[] args) {
        test1();
        //test2();
        //test3();
        //test4();

    }

    public static void test1() {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();

        for(int i = 0; i < 10; i++)
            results.add(exec.submit(new TaskWithResult(i)));

        for(Future<String> fs : results)
            try {
                // get() blocks until completion:
                System.out.println(fs.get());
            } catch(InterruptedException e) {
                System.out.println(e);
                return;
            } catch(ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
    }


    public static void test2() {
        MyCallable task1 = new MyCallable(0);
        MyCallable task2 = new MyCallable(1);
        MyCallable task3 = new MyCallable(2);
        ExecutorService es = Executors.newFixedThreadPool(3);

        try {
            Future<String> future1 = es.submit(task1);
            System.out.println("task1: " + future1.get());

            Future future2 = es.submit(task2);
            //等待5秒后再停止第二个任务
            TimeUnit.MILLISECONDS.sleep(5000);
            System.out.println("task2 cancel: " + future2.cancel(true));

            Future future3 = es.submit(task3);
            //因为执行第三个任务会引起异常，所以下面的语句将引起异常的抛出
            //仅仅submit并不会抛异常，从Future上取结果时才抛异常
            System.out.println("task3: " + future3.get());
        } catch (Exception e){
            System.out.println(e.toString());
        }

        es.shutdownNow();
    }


    public static void test3() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Tasks.TaskSon task = new Tasks.TaskSon();

        //FutureTask当成是Runnable
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executor.submit(futureTask);
        executor.shutdown();

        System.out.println("主线程正在执行");
        try {
            //TimeUnit.MILLISECONDS.sleep(1000); //不需要sleep，get()自己会等待直到有结果
            System.out.println("task运行结果: " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有线程任务执行完毕");
    }

    public static void test4() {
        Tasks.TaskSon task = new Tasks.TaskSon();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);

        //FutureTask当成是Runnable
        Thread thread = new Thread(futureTask);
        thread.start();
    }


}