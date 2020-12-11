package net.jcip.examples.part2_structapplication.chapter8_threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ThreadDeadlock

 在单线程Executor中任务发生死锁（不要这么做）   ---线程饥饿死锁


 每当提交了一个有依赖性的Executor任务时，要清楚地知道可能会出现线程饥饿死锁，因此需要在代码或配置Executor的配置文件中记录线程池的大小
 限制或配置限制
 */
public class ThreadDeadlock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class LoadFileTask implements Callable<String> {
        private final String fileName;

        public LoadFileTask(String fileName) {
            this.fileName = fileName;
        }

        public String call() throws Exception {
            // Here's where we would actually read the file
            return "";
        }
    }

    public class RenderPageTask implements Callable<String> {
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            //将发生死锁 --- 由于任务在等待子任务的结果
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            // Here's where we would actually render the page
            return "";
        }
    }
}
