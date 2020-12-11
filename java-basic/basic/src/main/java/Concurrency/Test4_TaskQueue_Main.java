package Concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Defias on 2020/09.
 * Description:
 */
public class Test4_TaskQueue_Main {
    public static void main(String[] args) throws InterruptedException {
        Test4_TaskQueue_SyncWaitNotify q = new Test4_TaskQueue_SyncWaitNotify();
        //Test5_TaskQueue_LockCondition q = new Test5_TaskQueue_LockCondition();

        List<Thread> ts = new ArrayList<Thread>();
        for (int i=0; i<6; i++) {
            Thread t = new Thread() {
                public void run() {
                    // 执行task:
                    while (true) {
                        try {
                            String s = q.getTask();
                            System.out.println(Thread.currentThread().getName() + "execute task: " + s);
                        } catch (Exception e) {
                            return;
                        }
                    }
                }
            };
            t.start();
            ts.add(t);
        }

        Thread add = new Thread(() -> {
            for (int i=0; i<10; i++) {
                // 放入task:
                String s = "t-" + Math.random();
                System.out.println(Thread.currentThread().getName() + "add task: " + s);
                q.addTask(s);
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {}
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        for (Thread t : ts) {
            t.interrupt();
        }
    }
}
