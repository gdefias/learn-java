package Concurrency;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * Created by Defias on 2017/8/27.

 CompletionService VS List


 test1
 使用List收集异步任务结果 (List记录每个submit返回的Future)，循环遍历结果, Future不一定完成, 如果没有完成, 那么调用get会阻塞

 问题：
 如果排在前面的任务没有完成, 那么就会阻塞, 这样后面已经完成的任务就得等待
 更为严重的是: 第一个任务如果几个小时或永远完成不了, 而后面的任务几秒钟就完成了, 那么后面的任务的结果都将得不到处理


 test2
 对第1种情况进行的改进，在循环遍历结果时查看任务是否完成, 如果完成, 就获取任务的结果, 然后将该任务从任务列表中删除，如果未完成,
 就跳过此任务, 继续查看下一个任务结果；如果到了任务列表末端, 结果列表还非空的话，就从新回到任务列表开始, 继续从第一步开始执行
 这样就可以及时处理已完成任务的结果了


 test0
 使用更方便的ExecutorCompletionService管理异步任务
 ExecutorCompletionService内部维护了一个BlockingQueue, 只有完成的任务才被加入到队列中，内部还维护列一个Executor, 可以
 执行任务，任务一完成就加入到内置管理队列中, 如果队列中的数据为空时, 调用take()就会阻塞 (等待任务完成)

 ExecutorCompletionService的take/poll方法是对BlockingQueue对应的方法的封装
 完美解决了已完成任务得不到及时处理的问题
 */



public class Test7_CompletionService2 {
    public final static int tasknum = 50;
    public final static int threadnum = 10;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test0();
        System.out.println("-----");
        test1();
        System.out.println("-----");
        test2();
    }

    public static void test0() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(threadnum);
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<String>(service);

        for(int i=0; i<tasknum; i++) {
            completionService.submit(new myCallable("test0-" + i));
        }

        int completionTask = 0;
        while(completionTask < tasknum) {
            Future<String> resultHolder = completionService.take();
            System.out.println(resultHolder.get());
            completionTask++;
        }

        System.out.println(completionTask + " task done !");
        service.shutdown();
    }



    public static void test1() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(threadnum);
        List<Future<String>> taskResultHolder = new ArrayList<>();

        for(int i=0; i<tasknum; i++) {
            taskResultHolder.add(service.submit(new myCallable("test1-" + i)));
        }

        int count = 0;
        for(Future<String> future : taskResultHolder) {
            System.out.println(future.get());
            count++;
        }
        System.out.println(count + " task done !");

        service.shutdown();
    }



    public static void test2() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(threadnum);
        List<Future<String>> results = new ArrayList<>();

        for(int i=0; i<tasknum; i++) {
            results.add(service.submit(new myCallable("test2-" + i)));
        }

        //获取结果 - 自旋
        int count = 0;
        for(int i=0; i<results.size(); i++) {
            Future<String> taskHolder = results.get(i);
            if(taskHolder.isDone()) {
                String result = taskHolder.get();
                System.out.println(result);
                results.remove(taskHolder);
                i--;
                count++; //完成的任务的计数器
            }

            //回到列表开头, 从新获取结果
            if(i == results.size() - 1)
                i = -1;
        }
        System.out.println(count + " task done !");

        service.shutdown();
    }


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