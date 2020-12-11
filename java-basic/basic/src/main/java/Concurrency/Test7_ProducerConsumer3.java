package Concurrency;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import  java.util.LinkedList;
/**
 * Created by Defias on 2017/2/26.
 *
 * 生产者与消费者
 *
 * Lock - Condition
 *
 * await signal
 */



public class Test7_ProducerConsumer3 {
    private static Buffer buffer = new Buffer();  //创建缓冲区

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new ProducerTask());
        executor.execute(new ConsumerTask());
        executor.shutdown();
    }

    //重复向缓冲区产生数字
    private static class ProducerTask implements Runnable {
        public void run() {
            try {
                int i = 1;
                while (true) {
                    System.out.println("Producer writes " + i);
                    buffer.write(i++); // Add a value to the buffer  write向缓冲区添加一个整数
                    // Put the thread into sleep
                    Thread.sleep(500);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    //重复从缓冲区消耗数字
    private static class ConsumerTask implements Runnable {
        public void run() {
            try {
                while (true) {
                    System.out.println("\t\t\t\t\t\t\tConsumer reads " + buffer.read());   //read从缓冲区删除和返回一个整数
                    // Put the thread into sleep
                    Thread.sleep(500);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    // 缓冲区
    private static class Buffer {
        private static final int CAPACITY = 1; // buffer size
        private LinkedList<Integer> queue = new LinkedList<Integer>();
        //可重入锁
        private static Lock lock = new ReentrantLock();
        private static Condition notEmpty = lock.newCondition();    //条件：缓冲区非空
        private static Condition notFull = lock.newCondition();     //条件：缓冲区未满

        public void write(int value) {
            lock.lock(); // Acquire the lock
            try {
                while (queue.size() == CAPACITY) {
                    System.out.println("Wait for notFull condition");
                    notFull.await();   //当向缓冲区添加数字时，如果缓冲区是满的，那么就会等待notFull条件
                }

                queue.offer(value);
                notEmpty.signal(); //唤醒一个等待线程notEmpty条件的线程
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock(); // Release the lock
            }
        }

        public int read() {
            int value = 0;
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    System.out.println("\t\t\t\t\t\t\tWait for notEmpty condition");
                    notEmpty.await();   //当从缓冲区读取一个数字时，如果缓冲区是空的，那么任务将等待notEmpty条件
                }

                value = queue.remove();
                notFull.signal(); //唤醒一个等待线程notFull条件的线程
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock(); // Release the lock
                return value;
            }
        }
    }
}
