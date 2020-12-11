package Concurrency;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Defias on 2017/8/27.

 CompletionService VS BlockingQueue

 CompletionService - ExecutorCompletionService
 以异步的方式一边生产新的任务，一边处理已完成任务的结果，这样就可以将执行任务与处理任务分离开

 take()    返回的是最先完成任务的 Future 对象，take()方法是阻塞执行的
 pool()    返回的Future可能为null，因为poll()是非阻塞执行的

 */

public class Test7_CompletionService {
    private static final int POOL_SIZE = 5;
    private static final int TOTAL_TASK = 20;

    public static void main(String[] args) throws Exception {
        testByQueue();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("-----");
        testByCompetion();
    }

    //通过BlockingQueue来实现获取线程池中任务的返回结果
    public static void testByQueue() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        BlockingQueue<Future<String>> queue = new LinkedBlockingQueue<Future<String>>();

        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<String> future = pool.submit(new myCallable("Concurrency-" + i));
            queue.add(future);
        }

        for (int i = 0; i < TOTAL_TASK; i++) {
            System.out.println("By BlockingQueue: " + queue.take().get());
        }
        pool.shutdown();
    }

    //通过CompletionService来实现获取线程池中任务的返回结果
    public static void testByCompetion() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        CompletionService<String> cservice = new ExecutorCompletionService<String>(pool);

        for (int i = 0; i < TOTAL_TASK; i++) {
            cservice.submit(new myCallable("Concurrency-" + i));
        }

        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<String> future = cservice.take();
            System.out.println("By Competion: " + future.get());
        }
        pool.shutdown();
    }


    //具有返回值的callable线程
    public static class myCallable implements Callable<String> {
        private String name;

        public myCallable(String name) {
            this.name = name;
        }

        @Override
        public String call() {
            int sleepTime = new Random().nextInt(1000);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 返回给调用者的值
            String str = name + " sleep time:" + sleepTime;
            return str;
        }
    }
}
