package net.jcip.examples.part3_activityperftest.chapter11_prefscalability;

import java.util.concurrent.BlockingQueue;

/**
 * WorkerThread

 对任务队列的串行访问

 */

public class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> queue;

    public WorkerThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                Runnable task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                break; /* Allow thread to exit */
            }
        }
    }
}
