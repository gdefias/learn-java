package Concurrency.collection;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 乐观加锁 - Atomic

 乐观锁： 事先假设不会发生并发冲突，只在提交的时候检查数据有没有被改变过（数据完整性）。Atomic类就是如此

 执行某项计算时不加互斥（synchronized或lock），当准备更新Atomic对象时使用compareAndSet方法，将旧值和新值一起提交给这个方法，如果
 旧值与它在Atomic对象中发现的值不一致，那么这个操作就失败了 -- 这意味着其他任务已经于此操作执行期间修改了这个对象 -- 失败后做什么？
 重试？直接结束？... 正是这项技术的受限之处


 boolean compareAndSet(int expect, int update)
 如果当前值==预期值，则将该值原子设置为给定的更新值，并返回true，否则返回false

 乐观加锁的思想 - 不使用任何互斥，而是使用AtomicInteger.compareAndSet
 */


public class FastSimulation {
    static final int N_ELEMENTS = 100000;
    static final int N_GENES = 30;
    static final int N_EVOLVERS = 50;
    static final AtomicInteger[][] GRID = new AtomicInteger[N_ELEMENTS][N_GENES];
    static Random rand = new Random(47);

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < N_ELEMENTS; i++)
            for(int j = 0; j < N_GENES; j++)
                GRID[i][j] = new AtomicInteger(rand.nextInt(1000));

        for(int i = 0; i < N_EVOLVERS; i++)
            exec.execute(new Evolver());

        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }

    static class Evolver implements Runnable {
        public void run() {
            while(!Thread.interrupted()) {
                // Randomly select an element to work on:
                int element = rand.nextInt(N_ELEMENTS);
                for(int i = 0; i < N_GENES; i++) {
                    int previous = element - 1;
                    if(previous < 0)
                        previous = N_ELEMENTS - 1;
                    int next = element + 1;
                    if(next >= N_ELEMENTS)
                        next = 0;

                    int oldvalue = GRID[element][i].get();
                    // Perform some kind of modeling calculation:
                    int newvalue = oldvalue +
                            GRID[previous][i].get() + GRID[next][i].get();
                    newvalue /= 3; // Average the three values
                    if(!GRID[element][i].compareAndSet(oldvalue, newvalue)) {
                        // Policy here to deal with failure. Here, we
                        // just report it and ignore it; our model
                        // will eventually deal with it.
                        //失败了，直接打印出来
                        System.out.println("Old value changed from " + oldvalue);
                    }
                }
            }
        }
    }

}