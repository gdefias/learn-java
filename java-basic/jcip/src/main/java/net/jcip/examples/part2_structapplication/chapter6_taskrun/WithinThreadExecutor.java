package net.jcip.examples.part2_structapplication.chapter6_taskrun;

import java.util.concurrent.Executor;

/**
 * WithinThreadExecutor

 在调用线程中以同步方式执行所有任务的Executor

 */
public class WithinThreadExecutor implements Executor {
    public void execute(Runnable r) {
        r.run();
    };
}
