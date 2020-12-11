package Concurrency.solutions;
import java.util.concurrent.*;
import java.util.*;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: CountDownLatch 锁存器

 同步一个或多个任务，强制他们等待有其他任务执行的一组操作完成
 让一个线程集等待直到计数器变为0，一次性的，一旦计数为0就不能再重置了

 典型用法：将一个任务分解成n个互相独立的可解决任务
 */
public class Test1_CountDownLatch {
    static final int SIZE = 100;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        // All must share a single CountDownLatch object:
        CountDownLatch latch = new CountDownLatch(SIZE);
        for(int i = 0; i < 10; i++)
            exec.execute(new WaitingTask(latch));
        for(int i = 0; i < SIZE; i++)
            exec.execute(new TaskPortion(latch));
        print("Launched all tasks");

        exec.shutdown(); // Quit when all tasks complete
    }
}


// Waits on the CountDownLatch:
class WaitingTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }
    public void run() {
        try {
            latch.await();
            print("Latch barrier passed for " + this);
        } catch(InterruptedException ex) {
            print(this + " interrupted");
        }
    }
    public String toString() {
        return String.format("WaitingTask %1$-3d ", id);
    }
}



class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;

    //使用static而不会有并发问题是因为Random.nextInt方法恰好是线程安全的，否则要去掉static
    private static Random rand = new Random(47);
    private final CountDownLatch latch;

    TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch(InterruptedException ex) {
            // Acceptable way to exit
        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        print(this + "completed");
    }

    public String toString() {
        return String.format("%1$-3d ", id);
    }
}
