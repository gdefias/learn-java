package net.jcip.examples.part2_structapplication.chapter7_cancelclose;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * CheckForMail

 只执行一次的服务

 使用私有的Executor，并且该Executor的生命周期受限于方法的调用

 */
public class CheckForMail {
    public boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit)
            throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final AtomicBoolean hasNewMail = new AtomicBoolean(false);
        try {
            for (final String host : hosts)
                exec.execute(new Runnable() {
                    public void run() {
                        if (checkMail(host))
                            hasNewMail.set(true);
                    }
                });
        } finally {
            exec.shutdown();
            exec.awaitTermination(timeout, unit);
        }
        return hasNewMail.get();
    }

    private boolean checkMail(String host) {
        // Check for mail
        return false;
    }
}
