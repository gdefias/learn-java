package net.jcip.examples.part3_activityperftest.chapter12_concurrencytest;

import junit.framework.TestCase;
import net.jcip.examples.part3_activityperftest.chapter12_concurrencytest.SemaphoreBoundedBuffer;

/**
 * TestBoundedBuffer

 BoundedBuffer的基本单元测试

 */
public class TestBoundedBuffer extends TestCase {
    private static final long LOCKUP_DETECT_TIMEOUT = 1000;
    private static final int CAPACITY = 10000;
    private static final int THRESHOLD = 10000;

    void testIsEmptyWhenConstructed() {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<Integer>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

    void testIsFullAfterPuts() throws InterruptedException {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<Integer>(10);
        for (int i = 0; i < 10; i++)
            bb.put(i);
        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }


    //测试阻塞行为以及对中断的响应性
    void testTakeBlocksWhenEmpty() {
        final SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<Integer>(10);
        Thread taker = new Thread() {
            public void run() {
                try {
                    int unused = bb.take();
                    fail(); // if we get here, it's an error
                } catch (InterruptedException success) {
                }
            }
        };
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (Exception unexpected) {
            fail();
        }
    }

    class Big {
        double[] data = new double[100000];
    }

    //测试资源泄露
    void testLeak() throws InterruptedException {
        SemaphoreBoundedBuffer<Big> bb = new SemaphoreBoundedBuffer<Big>(CAPACITY);
        int heapSize1 = snapshotHeap();
        for (int i = 0; i < CAPACITY; i++)
            bb.put(new Big());
        for (int i = 0; i < CAPACITY; i++)
            bb.take();
        int heapSize2 = snapshotHeap();
        assertTrue(Math.abs(heapSize1 - heapSize2) < THRESHOLD);
    }

    private int snapshotHeap() {
        /* Snapshot heap and return heap size */
        return 0;
    }

}
