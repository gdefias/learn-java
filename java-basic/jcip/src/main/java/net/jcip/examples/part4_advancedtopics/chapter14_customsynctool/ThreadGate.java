package net.jcip.examples.part4_advancedtopics.chapter14_customsynctool;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * ThreadGate

 虽然闭锁CountDownLatch通常都能满足需求，但在某些情况下存在一个缺陷：按照这种方式构造的阀门在打开后无法重新关闭

 使用wait和notifyAll来实现可重新关闭的阀门

 */
@ThreadSafe
public class ThreadGate {
    // CONDITION-PREDICATE: opened-since(n) (isOpen || generation>n)
    @GuardedBy("this") private boolean isOpen;
    @GuardedBy("this") private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    // BLOCKS-UNTIL: opened-since(generation on entry)
    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation)
            wait();
    }
}
