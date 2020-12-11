package Concurrency;
import java.util.concurrent.*;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 终结任务

 ExecutorService的awaitTermination： 等待每个任务结束，如果所有的任务在超时时间达到之前全部结束，则返回true，否则返回false

 仿真程序：每天通过多个大门进入公园的总人数
 */

public class Test7_Cancel {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
            exec.execute(new Entrance(i));
        // Run for a while, then stop and collect the data:
        TimeUnit.MILLISECONDS.sleep(3000);

        Entrance.cancel();
        exec.shutdown();
        if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            System.out.println("Some tasks were not terminated!");
         System.out.println("Total: " + Entrance.getTotalCount());
         System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
    }
}


class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private int number = 0;
    // Doesn't need synchronization to read:
    private final int id;
    private static volatile boolean canceled = false;

    // Atomic operation on a volatile field:
    public static void cancel() {
        canceled = true;
    }

    public Entrance(int id) {
        this.id = id;
        // Keep this task in a list. Also prevents
        // garbage collection of dead tasks:
        entrances.add(this);
    }

    public void run() {
        while(!canceled) {
            //synchronized(this) {
                ++number;
            //}
            System.out.println(this + " Total: " + count.increment());

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch(InterruptedException e) {
                 System.out.println("sleep interrupted");
            }
        }

        System.out.println("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for(Entrance entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}


class Count {
    private int count = 0;
    private Random rand = new Random(47);
    // Remove the synchronized keyword to see counting fail:
    public synchronized int increment() {
        int temp = count;
        if(rand.nextBoolean()) // Yield half the time
            Thread.yield();  //如果没有synchronized关键字，多个任务同时访问并修改count而发生崩溃，yield会使问题更快发生
        return (count = ++temp);
    }

    public synchronized int value() {
        return count;
    }
}
