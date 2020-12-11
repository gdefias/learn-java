package Concurrency.task;

/**
 * Created by Defias on 2020/07.
 * Description: 继承Thread类
 *
 */

public class NewThread extends Thread {
    public NewThread() {
        super("Demo Thread");
        System.out.println("Child thread: " + this);
        start();  //在构造器中启动线程任务的执行
    }

    //Thread类中惟一的必须被重载的是run()方法
    @Override
    public void run() {
        try {
            for(int i=5; i>0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}
