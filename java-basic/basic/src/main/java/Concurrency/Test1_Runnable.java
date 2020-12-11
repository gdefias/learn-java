package Concurrency;
import Concurrency.task.LiftOff;
import Concurrency.task.Tasks;

/**
 * Created by Defias on 2017/2/26.

 创建线程 - 实现Runnable接口

 */
public class Test1_Runnable {
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    public static void test1() {
        LiftOff launch = new LiftOff();
        launch.run();
    }

    public static void test2() {
        // Create tasks
        Runnable printA = new Tasks.PrintChar('a', 100);
        Runnable printB = new Tasks.PrintChar('b', 100);
        Runnable print100 = new Tasks.PrintNum(100);

        // Create threads
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);

        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void test3() {
        for(int i = 0; i < 5; i++)
            new SelfManaged();
    }
}


//自管理的Runnable
class SelfManaged implements Runnable {
    private int countDown = 5;
    private Thread t = new Thread(this);

    public SelfManaged() {
        t.start();
    }

    public String toString() {
        return Thread.currentThread().getName() +
                "(" + countDown + "), ";
    }

    public void run() {
        while(true) {
            System.out.print(this);
            if(--countDown == 0)
                return;
        }
    }
}