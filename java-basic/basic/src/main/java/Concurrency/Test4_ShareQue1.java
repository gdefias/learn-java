package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Defias on 2020/07.
 * Description: 共享受限资源

 你永远不知道一个线程何时在运行

 不正确的访问资源
 */

public class Test4_ShareQue1 {
    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}

class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator g, int ident) {
        generator = g;
        id = ident;
    }

    public void run() {
        while(!generator.isCanceled()) {
            int val = generator.next();
            if(val % 2 != 0) {
                //检测next产生的是奇数时才输出，按理说是没有奇数的，但是由于非原子性可以读取到中间的奇数状态
                System.out.println(val + " not even!");
                generator.cancel(); // Cancels all EvenCheckers
            }
        }
    }

    // Test any type of IntGenerator:
    public static void test(IntGenerator gp, int count) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < count; i++)  //启动多个线程
            exec.execute(new EvenChecker(gp, i));  //传入同一个IntGenerator
        exec.shutdown();
    }

    // Default value for count:
    public static void test(IntGenerator gp) {
        test(gp, 10);
    }
}


class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    public int next() {
        //Java中即使单个递增操作也不是原子性的
        ++currentEvenValue; // Danger point here!
        ++currentEvenValue;
        return currentEvenValue;
    }

}

//共享资源
abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    // Allow this to be canceled:
    public void cancel() { canceled = true; }
    public boolean isCanceled() { return canceled; }
}