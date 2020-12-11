package Concurrency.solutions;
import java.util.concurrent.*;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;
/**
 * Created by Defias on 2020/07.
 * Description: DelayQueue 延迟队列

 DelayQueue中只能存放Delayed（Delayed接口实现类）对象
 take(): 检索并删除此队列的头部，如果需要，等待有一个延迟到期的元素在此队列上可用，按延迟时间取出，与对象存入的顺序没有关系
         DelayQueue中的Delayed对象的延迟时间越短越，越被先取出来

 Delayed对象的getDelay()方法告知延迟到期还有多长时间或延迟在多长时间之前已经到期
 */

public class Test3_DelayQueue {
    public static void main(String[] args) {
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();

        // Fill with tasks that have random delays:
        for(int i = 0; i < 20; i++)
            queue.put(new DelayedTask(rand.nextInt(5000)));
        // Set the stopping point
        queue.add(new DelayedTask.EndSentinel(5000, exec));
        exec.execute(new DelayedTaskConsumer(queue));
    }
}


class DelayedTaskConsumer implements Runnable {
    private DelayQueue<DelayedTask> q;
    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }
    public void run() {
        try {
            while(!Thread.interrupted())
                q.take().run(); // Run task with the current thread
        } catch(InterruptedException e) {
            // Acceptable way to exit
        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}


class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

    public DelayedTask(int delayInMilliseconds) {
        delta = delayInMilliseconds;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
        sequence.add(this);
    }

    public long getDelay(TimeUnit unit) {
        //TimeUnit.convert：将给定单位的给定持续时间转换为本机时间
        return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
    }

    //因为Delayed继承了Comparable接口，所以这个方法也是要实现的
    public int compareTo(Delayed arg) {
        DelayedTask that = (DelayedTask)arg;
        if(trigger < that.trigger) return -1;
        if(trigger > that.trigger) return 1;
        return 0;
    }

    public void run() {
        System.out.print(this + " ");
    }

    public String toString() {
        return String.format("[%1$-4d]", delta) +
                " Task " + id + "\n";
    }

    public String summary() {
        return "(" + id + ":" + delta + ")";
    }

    public static class EndSentinel extends DelayedTask {
        private ExecutorService exec;
        public EndSentinel(int delay, ExecutorService e) {
            super(delay);
            exec = e;
        }
        public void run() {
            //顺序遍历sequence list
            for(DelayedTask pt : sequence) {
                System.out.print(pt.summary() + " \n");
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

