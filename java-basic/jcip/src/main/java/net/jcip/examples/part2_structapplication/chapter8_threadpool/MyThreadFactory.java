package net.jcip.examples.part2_structapplication.chapter8_threadpool;

import java.util.concurrent.ThreadFactory;

/**
 * MyThreadFactory

 自定义线程工厂

 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    public Thread newThread(Runnable runnable) {
        return new MyAppThread(runnable, poolName);
    }
}
