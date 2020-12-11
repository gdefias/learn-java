package Concurrency;
import java.util.concurrent.*;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 死锁

 */
public class Test4_Deadlock {
    public static void main(String[] args) throws Exception {
        //演示死锁可能发生和死锁不会发生的开关
        //boolean deadlocktest = true;
        boolean deadlocktest = false;

        int ponder = 1;  //影响思考时间长短的因子（越小死锁的可能越大）
        int size = 5;  //哲学家和筷子的数量
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for(int i = 0; i < size; i++)
            sticks[i] = new Chopstick();

        if(deadlocktest) {
            //循环等待条件
            for(int i = 0; i < size; i++)
                exec.execute(new Philosopher(
                        sticks[i], sticks[(i+1) % size], i, ponder));
        } else {
            //破坏循环等待条件 - 避免发生死锁
            for(int i = 0; i < size; i++)
                if(i < (size-1))
                    exec.execute(new Philosopher(
                            sticks[i], sticks[i+1], i, ponder));
                else
                    //最后一个哲学家先拿左筷子再拿右筷子
                    exec.execute(new Philosopher(
                            sticks[0], sticks[i], i, ponder));
        }


        TimeUnit.SECONDS.sleep(5);
        System.out.println("Press 'Enter' to quit");
        System.in.read();
        exec.shutdownNow();
    }
}

//哲学家
class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;  //会影响花在思考上的时间长短，思考的时间越少死锁的可能性越大
    private Random rand = new Random(47);

    //思考
    private void pause() throws InterruptedException {
        if(ponderFactor == 0)
            return;
        TimeUnit.MILLISECONDS.sleep(
                rand.nextInt(ponderFactor * 250));
    }

    public Philosopher(Chopstick left, Chopstick right,
                       int ident, int ponder) {
        this.left = left;
        this.right = right;
        id = ident;
        ponderFactor = ponder;
    }

    public void run() {
        try {
            while(!Thread.interrupted()) {
                System.out.println(this + " " + "thinking");
                pause();
                // Philosopher becomes hungry
                System.out.println(this + " " + "grabbing right");
                right.take();
                System.out.println(this + " " + "grabbing left");
                left.take();
                System.out.println(this + " " + "eating");
                pause();
                right.drop();
                left.drop();
            }
        } catch(InterruptedException e) {
            System.out.println(this + " " + "exiting via interrupt");
        }
    }
    public String toString() {
        return "Philosopher " + id;
    }
}

//筷子
class Chopstick {
    private boolean taken = false;

    public synchronized void take() throws InterruptedException {
        while(taken)
            wait();
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}