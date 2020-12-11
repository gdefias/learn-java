package Concurrency.task;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2020/07.
 * Description: 实现Callable接口
 */
public class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public String call() {
        try {
            //Thread.currentThread().sleep(4000);
            TimeUnit.MILLISECONDS.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "result of TaskWithResult " + id;
    }
}