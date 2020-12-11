package net.jcip.examples.part1_base.chapter5_buildmodel;

import java.util.concurrent.BlockingQueue;

/**
 * TaskRunnable

 恢复中断状态以避免屏蔽中断

 */
public class TaskRunnable implements Runnable {
    BlockingQueue<Task> queue;

    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            // 恢复中断状态
            Thread.currentThread().interrupt();
        }
    }

    void processTask(Task task) {
        // Handle the task
    }

    interface Task {
    }
}
