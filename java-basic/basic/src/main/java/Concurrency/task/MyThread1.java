package Concurrency.task;

/**
 * Created by Defias on 2020/07.
 * Description:
 */
public class MyThread1 implements Runnable {

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("ThreadName = " + Thread.currentThread().getName());
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println("MyThread1 recev InterruptedException");
        }
    }
}
