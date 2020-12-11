package net.jcip.examples.part2_structapplication.chapter7_cancelclose;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.Executors.newScheduledThreadPool;
import static net.jcip.examples.part2_structapplication.chapter7_cancelclose.LaunderThrowable.launderThrowable;

/**
 * TimedRun2

 在专门的线程中中断任务

 */
public class TimedRun2 {
    private static final ScheduledExecutorService cancelExec = newScheduledThreadPool(1);

    public static void timedRun(final Runnable r,
                                long timeout, TimeUnit unit)
            throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (t != null)
                    throw launderThrowable(t);
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }
}
