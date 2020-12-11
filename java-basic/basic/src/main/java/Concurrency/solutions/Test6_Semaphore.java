package Concurrency.solutions;
import java.util.concurrent.*;
/**
 * Created by Defias on 2017/2/26.

 Semaphore 信号量

 正常锁（Lock或Synchronize）任何时刻都只允许一个任务访问一项资源， 而计数信号量Semaphore允许若干任务同时访问这个资源
 可以将信号量看作是在向外分发使用资源的'许可证'，尽管实际上没有使用任何许可证对象

 限制访问一个共享资源的线程数
 对共同资源进行访问控制的对象，在访问资源之前，线程必须从信号量获取许可，在访问资源之后，这个线程必须将许可返回信号量

 java.util.concurrent.Semaphore
 --------------------------------------------------------------------
 +Semaphore(numberOfPermits: int)   创建一个具有指定数目的许可的信号量。公平性策略参数为假
 +Semaphore(numberOfPermits: int, fair: boolean)   创建一个具有指定数目的许可以及公平性策略的信号量
 +acquire(): void   从该信号量获取一个许可，如果许可不可用，线程将被阻塞，直到一个许可可用（信号量中的许可总数减1）
 +release(): void   释放一个许可返回给信号量（信号量中的许可总数加1）

 fair -- 是否公平，获得锁的顺序与线程启动顺序有关，就是公平，先启动的线程，先获得锁

 */
public class Test6_Semaphore {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        executor.shutdownNow();
        System.out.println("---main end---");
    }

    // 存款
    public static class DepositTask implements Runnable {
        public void run() {
            while (true) {
                account.deposit((int) (Math.random() * 10) + 1); //
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("DepositTask InterruptedException");
                    break;
                }
            }
        }
    }


    // 提款： 如果提款数额大于账户余额时提款线程必须等待新的存款，直到账户余额大于或等于提款数额
    public static class WithdrawTask implements Runnable {
        public void run() {
            while (true) {
                account.withdraw((int) (Math.random() * 10) + 1); //
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("WithdrawTask InterruptedException");
                    break;
                }
            }
        }
    }

    //账户
    private static class Account {
        private static Semaphore semaphore = new Semaphore(1);
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        //存
        public void deposit(int amount) {
            try {
                semaphore.acquire(); //获取一个许可证
                int newBalance = balance + amount;
                Thread.sleep(5);
                balance = newBalance;
                System.out.println("deposited: " + amount + "  balance: " + balance );
            } catch (InterruptedException ex) {
                System.out.println("deposit InterruptedException");
            } finally {
                semaphore.release();   //释放许可证
            }
        }

        //取
        public void withdraw(int amount) {
            try {
                semaphore.acquire(); //获取一个许可证
                if (balance >= amount) {
                    int newBalance = balance - amount;
                    Thread.sleep(5);
                    balance = newBalance;
                } else {
                    System.out.println("Balance is less, Nothing to do!");
                }
                System.out.println("withdraw: " + amount + "  balance: " + balance );
            } catch (InterruptedException ex) {
                System.out.println("withdraw InterruptedException");
            } finally {
                semaphore.release(); //释放许可证
            }
        }
    }
}



