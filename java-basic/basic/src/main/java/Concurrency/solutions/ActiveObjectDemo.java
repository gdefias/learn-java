package Concurrency.solutions;
import java.util.concurrent.*;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 活动对象模型 - Future

 一种解决并发竞争的方式
 把动作都封装成Callable对象，提交给消息队列，并立即返回一个Future对象

 每个活动对象维护着自己的工作线程和消息队列，并且所有对这种对象的请求都将进入队列排队，任何时刻都只能运行其中的一个，有了活动对象就可以
 串行化消息而不是方法，意味着不再需要防备一个任务在其循环的中间被中断

 每个活动对象方法的返回值都是一个具有泛型参数的Future，泛型参数就是该方法中实际的返回类型

 活动对象
    每个活动对象都有自己的线程
    每个对象都将维护对他自己的域的全部权利
    所有活动对象间的通信都将以这些对象之间的消息形式发生
    活动对象之间的消息都要排队

 活动对象之间的消息都成了一个任务，而任务会被传入一个List保存，然后逐个执行，任何时刻都只有一个调用，所以不会产生死锁

 */
public class ActiveObjectDemo {
    private ExecutorService ex =  Executors.newSingleThreadExecutor(); //单线程
    private Random rand = new Random(47);

    public static void main(String[] args) {
        ActiveObjectDemo d1 = new ActiveObjectDemo();
        // Prevents ConcurrentModificationException:
        List<Future<?>> results = new CopyOnWriteArrayList<Future<?>>();
        for(float f = 0.0f; f < 1.0f; f += 0.2f)
            results.add(d1.calculateFloat(f, f));

        for(int i = 0; i < 5; i++)
            results.add(d1.calculateInt(i, i));
        System.out.println("All asynch calls made");

        while(results.size() > 0) {
            for(Future<?> f : results)
                if(f.isDone()) {
                    try {
                        System.out.println(f.get());
                    } catch(Exception e) {
                        throw new RuntimeException(e);
                    }
                    results.remove(f);
                }
        }
        d1.shutdown();
    }

    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch(InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }

    public Future<Integer> calculateInt(final int x, final int y) {
        return ex.submit(new Callable<Integer>() {
            public Integer call() {
                System.out.println("starting " + x + " + " + y);
                pause(500);
                return x + y;
            }
        });
    }

    public Future<Float>  calculateFloat(final float x, final float y) {
        return ex.submit(new Callable<Float>() {
            public Float call() {
                System.out.println("starting " + x + " + " + y);
                pause(2000);
                return x + y;
            }
        });
    }

    public void shutdown() {
        ex.shutdown();
    }
}