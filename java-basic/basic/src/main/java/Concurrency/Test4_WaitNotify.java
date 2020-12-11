package Concurrency;
import java.util.concurrent.*;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: 线程中间的协作

 wait() 与 notify()/notifyAll()
 */

//Car上涂蜡和抛光
//抛光需要等待涂蜡完成才能进行
//第2层涂蜡开始，需要等待前一层的抛光完成才能进行
public class Test4_WaitNotify {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));  //控制权在两个任务之间来回传递
        exec.execute(new WaxOn(car));

        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}


class Car {
    private boolean waxOn = false;  //涂蜡抛光处理的状态

    public synchronized void waxed() {   //涂蜡完成
        waxOn = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() { //抛光完成
        waxOn = false; // Ready for another coat of wax
        notifyAll();
    }

    public synchronized void waitForWaxing()  //等待涂蜡完成
            throws InterruptedException {
        while(waxOn == false)
            wait();
    }

    public synchronized void waitForBuffing()   //等待抛光完成
            throws InterruptedException {
        while(waxOn == true)
            wait();
    }
}

//涂蜡
class WaxOn implements Runnable {
    private Car car;
    public WaxOn(Car c) { car = c; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                printnb("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch(InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax On task");
    }
}


//抛光
class WaxOff implements Runnable {
    private Car car;
    public WaxOff(Car c) { car = c; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                car.waitForWaxing();
                printnb("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch(InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax Off task");
    }
}


