package Concurrency;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Defias on 2020/07.
 * Description: 使用Lock对象

 Synchronized关键字不能尝试着获取锁且最终获取锁会失败，或者尝试着获取锁一段时间然后放弃它

 显示的Lock对象在加锁和释放锁方面，相对于内建的Synchronized锁来说，赋予了你更细粒度的控制力，这对于实现专用同步结构是很有用的


 */

public class Test5_Lock3 {
    public ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        final Test5_Lock3 al = new Test5_Lock3();
        al.untimed();
        al.timed();

        // Now create a separate task to grab the lock:
        new Thread() {
            {
                setDaemon(true);
            }

            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();

        Thread.yield(); // Give the 2nd task a chance
        TimeUnit.MILLISECONDS.sleep(4000);
        al.untimed(); // False -- lock grabbed by task
        al.timed();   // False -- lock grabbed by task
    }

    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if(captured)
                lock.unlock();
        }
    }

    public void timed() {
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
        } finally {
            if(captured)
                lock.unlock();
        }
    }


}


