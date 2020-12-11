package Concurrency.task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2020/07.
 * Description: 实现Callable接口
 */

public class MyCallable implements Callable {
    private int flag = 0;

    public MyCallable(int flag){
        this.flag = flag;
    }

    public String call() throws Exception {
        if (this.flag == 0){
            return "flag = 0";
        }
        if (this.flag == 1){
            try {
                while (true) {
                    System.out.println("looping.");
                    TimeUnit.MILLISECONDS.sleep(2000);
                }
            } catch (InterruptedException e) {
                System.out.println("thow InterruptedException");
            }
            return "false";
        } else {
            throw new Exception("Bad flag value!");
        }
    }
}
