package Concurrency;

/**
 * Created by Defias on 2020/07.
 * Description: 解决共享资源竞争

 使用同步 - synchronized关键字
 */

public class Test4_ShareSync1 {
    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}


class SynchronizedEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    public synchronized int next() {  //锁住this
        ++currentEvenValue;
        //Thread.yield(); // Cause failure faster
        ++currentEvenValue;
        return currentEvenValue;
    }
}