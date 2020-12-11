package Concurrency;
import java.util.concurrent.*;
/**
 * Created by Defias on 2017/2/26.

 使用阻塞队列简化生产者与消费者模型 - ArrayBlockingQueue



 */

public class Test7_PCBlockingQueue {
    private static ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<Integer>(2);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(new ProducerTask());
        executor.execute(new ConsumerTask());
        executor.shutdown();
    }


    private static class ProducerTask implements Runnable {
        public void run() {
            try {
                int i = 1;
                while (true) {
                    if(i==20) {
                        break;
                    }
                    System.out.println("Producer writes " + i);
                    buffer.put(i++);
                    Thread.sleep(50);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    private static class ConsumerTask implements Runnable {
        public void run() {
            try {
                while (true) {
                    System.out.println("\t\t\t\t\tConsumer reads " + buffer.take());
                    Thread.sleep(1500);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
