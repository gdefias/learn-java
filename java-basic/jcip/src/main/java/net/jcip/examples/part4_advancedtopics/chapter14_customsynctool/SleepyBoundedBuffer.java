package net.jcip.examples.part4_advancedtopics.chapter14_customsynctool;

import net.jcip.annotations.ThreadSafe;
import net.jcip.examples.part4_advancedtopics.chapter14_customsynctool.BaseBoundedBuffer;

/**
 * SleepyBoundedBuffer

 使用简单阻塞实现的有界缓存

 */
@ThreadSafe
public class SleepyBoundedBuffer <V> extends BaseBoundedBuffer<V> {
    int SLEEP_GRANULARITY = 60;

    public SleepyBoundedBuffer() {
        this(100);
    }

    public SleepyBoundedBuffer(int size) {
        super(size);
    }

    public void put(V v) throws InterruptedException {   //调用者需要处理InterruptedException
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isEmpty())
                    return doTake();
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }
}
