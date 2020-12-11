package Concurrency.solutions;

import java.util.concurrent.Exchanger;

/**
 * Created by Defias on 2020/07.
 * Description: Exchanger 交换器

 V	exchange(V x)
 等待另一个线程到达此交换点（除非当前线程为interrupted ），然后将给定对象传输给它，接收其对象作为回报（交换）
 返回：由另一个线程提供的对象
 */
public class Test7_Exchanger {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ExchangerTest test1 = new ExchangerTest(exchanger, "string1",
                "thread-1");
        ExchangerTest test2 = new ExchangerTest(exchanger, "string2",
                "thread-2");

        test1.start();
        test2.start();
    }
}

class ExchangerTest extends Thread {
    private Exchanger<String> exchanger;
    private String string;
    private String threadName;

    public ExchangerTest(Exchanger<String> exchanger, String string,
                         String threadName) {
        this.exchanger = exchanger;
        this.string = string;
        this.threadName = threadName;
    }

    public void run() {
        try {
            System.out.println(threadName + ": " + exchanger.exchange(string));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
