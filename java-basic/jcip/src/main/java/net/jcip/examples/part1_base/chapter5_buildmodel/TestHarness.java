package net.jcip.examples.part1_base.chapter5_buildmodel;

import java.util.concurrent.CountDownLatch;

/**
 * TestHarness

 使用闭锁CountDownLatch

 在计时测试中使用CountDownLatch来启动和停止线程

 */
public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task)
            throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);  //起始门
        final CountDownLatch endGate = new CountDownLatch(nThreads);   //结束门

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {
                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
