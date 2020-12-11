package net.jcip.examples.part2_structapplication.chapter7_cancelclose;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * BrokenPrimeProducer

 不可靠的取消操作把生产者置于阻塞的操作中（不要这么做）

 */
class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled)
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
        }
    }

    public void cancel() {
        cancelled = true;
    }

//    void consumePrimes() throws InterruptedException {
//
//    }
}

