package Concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Defias on 2020/07.
 * Description: 解决共享资源问题 - 使用显示的Lock对象
 */

public class Test5_Lock1 {
    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}



class MutexEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();
    public int next() {
        lock.lock();
        try {
            ++currentEvenValue;
            Thread.yield(); // Cause failure faster
            ++currentEvenValue;
            return currentEvenValue;
        } finally {
            lock.unlock();
        }
    }

}