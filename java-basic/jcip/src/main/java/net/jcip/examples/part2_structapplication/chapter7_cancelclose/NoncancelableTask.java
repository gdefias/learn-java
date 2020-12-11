package net.jcip.examples.part2_structapplication.chapter7_cancelclose;

import java.util.concurrent.BlockingQueue;

/**
 * NoncancelableTask

 不可取消的任务在退出前恢复中断

 */
public class NoncancelableTask {
    public Task getNextTask(BlockingQueue<Task> queue) {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                    // fall through and retry
                }
            }
        } finally {
            if (interrupted)
                Thread.currentThread().interrupt();
        }
    }

    interface Task {
    }
}
