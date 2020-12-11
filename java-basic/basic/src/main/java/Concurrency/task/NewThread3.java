package Concurrency.task;

/**
 * Created by Defias on 2020/07.
 * Description:
 */
public class NewThread3 implements Runnable {
    public Thread t;
    public NewThread3() {
        t = new Thread(this, "Demo Thread");
        System.out.println("Child thread: " + t);
        t.start();
    }

    @Override
    public void run() {
        try {
            for(int i=5; i>0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(500);  //单位：ms
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}