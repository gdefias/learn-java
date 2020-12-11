package net.jcip.examples.part2_structapplication.chapter7_cancelclose;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * PrimeProducer

 通过中断来取消

 中断是实现取消的最合理方式

 */
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted())
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
            /* Allow thread to exit */
        }
    }

    public void cancel() {
        interrupt();
    }
}
