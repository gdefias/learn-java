package Concurrency;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
/**
 * Created by Defias on 2017/8/27.

 同步队列 SynchronousQueue

 BlockingQueue的一种实现
 每个插入操作必须等待另一个线程的对应移除操作，反之亦然
 提供了在线程之间交换单一元素的极轻量级方法

 */
public class Test7_PCBlockingQueue4 {

    public static void main(String[] args) {
        Test7_PCBlockingQueue4 o = new Test7_PCBlockingQueue4();

        BlockingQueue<String> q = new SynchronousQueue<String>();
        new Thread(o.new Producer(q)).start();
        new Thread(o.new Consumer(q)).start();
    }


    class Producer implements Runnable {
        private BlockingQueue<String> queue;
        List<String> objects = Arrays.asList("one", "two", "three");

        public Producer(BlockingQueue<String> q) {
            this.queue = q;
        }

        @Override
        public void run() {
            try {
                for (String s : objects) {
                    queue.put(s);  //产生数据放入队列中   阻塞直到另一个线程调用take方法为止
                    System.out.printf("put:%s%n",s);
                }
                queue.put("Done"); //已完成的标志Done
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        private BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> q) {
            this.queue = q;
        }

        @Override
        public void run() {
            String obj = null;
            try {
                while (!((obj = queue.take()).equals("Done"))) {
                    System.out.println(obj);    //从队列中读取对象
                    Thread.sleep(3000);  //sleep，证明Producer是put不进去的
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
