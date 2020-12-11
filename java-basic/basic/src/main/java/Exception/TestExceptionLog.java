package Exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.*;

/**
 * Created by Defias on 2020/07.
 * Description: 异常与记录日志
 */

public class TestExceptionLog {
    public static void main(String[] args) {
        try {
            throw new LoggingException();
        } catch(LoggingException e) {
            System.err.println("Caught " + e);
        }

        try {
            throw new NullPointerException();
        } catch(NullPointerException e) {
            LoggingExceptions2.logException(e);
            System.err.println("Caught " + e);
        }
    }
}


class LoggingException extends Exception {
    private static Logger logger = Logger.getLogger("LoggingException");  //将输出发送到System.err
    public LoggingException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));  //把堆栈轨迹送到指定的流
        logger.severe(trace.toString());  //写入日志记录
    }
}


//捕获和记录其他人的异常
class LoggingExceptions2 {
    private static Logger logger = Logger.getLogger("LoggingExceptions2");
    public static void logException(Exception e) {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}