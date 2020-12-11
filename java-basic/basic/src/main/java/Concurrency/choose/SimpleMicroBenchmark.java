package Concurrency.choose;
import java.util.concurrent.locks.*;

/**
 * Created by Defias on 2020/07.
 * Description: 比较各类互斥技术 - 简单的测试

 只有互斥存在竞争的情况下才能看到真正的性能差异，必须有多个任务尝试着访问互斥代码区
 否则编译机可能会进行特殊的优化而无法反应真实的性能差异
 */
public class SimpleMicroBenchmark {
    static long test(Incrementable incr) {
        long start = System.nanoTime();
        for(long i = 0; i < 10000000L; i++)
            incr.increment();
        return System.nanoTime() - start;
    }

    public static void main(String[] args) {
        long synchTime = test(new SynchronizingTest());
        long lockTime = test(new LockingTest());
        System.out.printf("synchronized: %1$10d\n", synchTime);
        System.out.printf("Lock:         %1$10d\n", lockTime);
        System.out.printf("Lock/synchronized = %1$.3f",
                (double)lockTime/(double)synchTime);
    }
}


abstract class Incrementable {
    protected long counter = 0;
    public abstract void increment();
}

class SynchronizingTest extends Incrementable {
    public synchronized void increment() { ++counter; }
}

class LockingTest extends Incrementable {
    private Lock lock = new ReentrantLock();
    public void increment() {
        lock.lock();
        try {
            ++counter;
        } finally {
            lock.unlock();
        }
    }
}

