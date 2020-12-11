package Concurrency;
import Concurrency.task.LiftOff;
import static Basic.Print.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Defias on 2020/07.
 * Description: 阻塞队列

 LinkedBlockingQueue
 ArrayBlockingQueue
 SynchronousQueue
 */
public class Test7_PCBlockingQueue2 {
    public static void main(String[] args) {
//        TestBlockingQueues.test("LinkedBlockingQueue", // Unlimited size
//                new LinkedBlockingQueue<LiftOff>());

//        TestBlockingQueues.test("ArrayBlockingQueue", // Fixed size
//                new ArrayBlockingQueue<LiftOff>(3));

        TestBlockingQueues.test("SynchronousQueue", // Size of 1
                new SynchronousQueue<LiftOff>());
    }
}


class TestBlockingQueues {
    static void getkey() {
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch(java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void getkey(String message) {
        print(message);
        getkey();
    }

    static void  test(String msg, BlockingQueue<LiftOff> queue) {
        print(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);
        t.start();

        for(int i = 0; i < 5; i++)
            runner.add(new LiftOff(5));
        getkey("Press 'Enter' (" + msg + ")");
        t.interrupt();
        print("Finished " + msg + " test");
    }
}


class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> rockets;
    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        rockets = queue;
    }

    public void add(LiftOff lo) {
        try {
            rockets.put(lo);
        } catch(InterruptedException e) {
            print("Interrupted during put()");
        }
    }

    public void run() {
        try {
            while(!Thread.interrupted()) {
                LiftOff rocket = rockets.take();
                rocket.run(); // Use this thread
            }
        } catch(InterruptedException e) {
            print("Waking from take()");
        }
        print("Exiting LiftOffRunner");
    }
}


