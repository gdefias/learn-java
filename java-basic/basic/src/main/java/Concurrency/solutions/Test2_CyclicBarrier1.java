package Concurrency.solutions;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by Defias on 2017/8/27.

 应用CyclicBarrier

 场景：
 今天晚上我们哥们4个去Happy。就互相通知了一下：晚上八点准时到xx酒吧门前集合，不见不散！。有个哥们住的近，早早就到了。
 有的事务繁忙，刚好踩点到了。无论怎样，先来的都不能独自行动，只能等待所有人
 */

public class Test2_CyclicBarrier1 {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Random random=new Random();

        final CyclicBarrier barrier = new CyclicBarrier(4,new Runnable() {
            public void run() {
                System.out.println("大家都到齐了，开始happy去");
            }});

        for(int i=0; i<4; i++){
            exec.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"到了");
                    try {
                        barrier.await();  //等待其他哥们
                        System.out.println(Thread.currentThread().getName() + " 等待结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }});
        }
        exec.shutdown();
    }
}
