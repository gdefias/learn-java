package net.jcip.examples.part2_structapplication.chapter7_cancelclose;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UEHLogger

  将异常写入日志的UncaughtExceptionHandler

 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Thread terminated with exception: " + t.getName(), e);
    }
}
