package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Defias on 2020/07.
 * Description: 不正确的访问共享资源及解决方案
 */

public class Test5_Lock2 {
    private static Account account = new Account(); //共享资源

    public static class AddAPennyTask implements Runnable {
        public void run() {
//            account.deposit(1);
//            account.deposit1(1);
            account.deposit2(1);
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {  //启动1000个线程
            System.out.println(i);
            executor.execute(new AddAPennyTask());  //1000个AddAPennyTask对象共享一个account
        }

        executor.shutdown();

        //等待所有线程执行完成
        while (!executor.isTerminated()) {
        }

        //由于不正确的共享资源访问，并非得到1000
        System.out.println("What is balance? " + account.getBalance());
    }
}



//资源
class Account {
    private volatile int balance = 0;
    private Lock lock = new ReentrantLock();

    public int getBalance() {
        return balance;
    }

    //非原子操作
    public  void  deposit(int amount) {
        int newbalance = balance + amount;
        try {
            Thread.sleep(3);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        balance = newbalance;
    }

    //解决方案1：加synchronized关键字
    public synchronized void  deposit1(int amount) {
        int newbalance = balance + amount;
        try {
            Thread.sleep(3);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        balance = newbalance;
    }

    //解决方案2：使用显示的Lock对象
    public void deposit2(int amount) {
        lock.lock();

        try {
            int newBalance = balance + amount;
            Thread.sleep(3);
            balance = newBalance;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}