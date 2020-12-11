package net.jcip.examples.part2_structapplication.chapter6_taskrun;

import java.util.concurrent.Executor;

/**
 * ThreadPerTaskExecutor

 为每个请求启动一个新线程的Executor

 */
public class ThreadPerTaskExecutor implements Executor {
    public void execute(Runnable r) {
        new Thread(r).start();
    };
}
