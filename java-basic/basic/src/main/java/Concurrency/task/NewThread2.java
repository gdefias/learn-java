package Concurrency.task;

/**
 * Created by Defias on 2020/07.
 * Description:
 */
public class NewThread2 implements Runnable {
    public String name; // name of thread
    public Thread t;
    public NewThread2(String threadname) {
        this.name = threadname;
        this.t = new Thread(this, this.name);
        System.out.println("New thread: " + t);
        t.start();
    }

    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println(this.name + ": " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(this.name + "Interrupted");
        }
        System.out.println(this.name + " exiting.");
    }
}
