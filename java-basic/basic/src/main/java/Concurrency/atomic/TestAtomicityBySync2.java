package Concurrency.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Defias on 2020/07.
 * Description:  原子性
 *
 * 错误的应用原子性
 */


public class TestAtomicityBySync2 {
    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1() {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        while(true) {
            int val = at.getValue();  //按理应该不会读取到奇数，但实际可以读取到奇数
            if(val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }

    public static void test2() {
        Thread thread1 =  new Thread(new NormalBoolean("张三"));
        Thread thread2 =  new Thread(new NormalBoolean("李四"));
        Thread thread3 =  new Thread(new NormalBoolean("王五"));
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class AtomicityTest implements Runnable {
    private int i = 0;

    public int getValue() {
        return i;  //return i虽然是原子操作，但是缺少同步使得可以读取不稳定的中间状态值
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
    }

    public void run() {
        while(true)
            evenIncrement();
    }
}



class NormalBoolean implements Runnable {
    private static boolean exists = false;

    private String name;

    public NormalBoolean(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " run");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!exists) {
            exists = true;
            System.out.println(name + " enter");
            System.out.println(name + " working");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " leave");
            exists = false;
        } else {
            System.out.println(name + " give up");
        }
    }
}