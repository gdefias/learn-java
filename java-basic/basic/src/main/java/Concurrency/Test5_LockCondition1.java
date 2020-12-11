package Concurrency;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
/**
 * Created by Defias on 2017/2/26.

 线程间通信： 条件

 Lock - Condition
 */



public class Test5_LockCondition1 {
    private static Account account = new Account();

    public static void main(String[] args) {
        System.out.println("Thread 1\t\tThread 2\t\t\tBalance");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());
        executor.shutdown();
    }


    // 存款任务
    public static class DepositTask implements Runnable {
        public void run() {
            try {
                while (true) {
                    account.deposit((int)(Math.random() * 10) + 1);
                    Thread.sleep(3000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // 提款任务
    public static class WithdrawTask implements Runnable {
        public void run() {
            while (true) {
                account.withdraw((int)(Math.random() * 10) + 1);
            }
        }
    }

    //账户
    private static class Account {
        private static Lock lock = new ReentrantLock();
        private static Condition newDeposit = lock.newCondition();  //创建条件对象
        private int balance = 0;  //余额

        public int getBalance() {
            return balance;
        }

        //提款amount元
        public void withdraw(int amount) {
            lock.lock(); // Acquire the lock
            try {
                //如果提款数额大于账户余额时提款线程必须等待新的存款，直到账户余额大于或等于提款数额
                while (balance < amount) {
                    System.out.println("\t\t\tWait for a deposit");
                    newDeposit.await();
                }
                balance -= amount;
                System.out.println("\t\t\tWithdraw " + amount +
                        "\t\t\t\t" + getBalance());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        //存款amount元
        public void deposit(int amount) {
            lock.lock(); // Acquire the lock
            try {
                balance += amount;
                System.out.println("Deposit " + amount +
                        "\t\t\t\t\t\t\t" + getBalance());

                // 唤醒所有等待线程 on the condition
                newDeposit.signalAll();
            }
            finally {
                lock.unlock();
            }
        }
    }
}
