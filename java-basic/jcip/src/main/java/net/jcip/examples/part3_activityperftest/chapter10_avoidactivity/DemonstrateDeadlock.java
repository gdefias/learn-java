package net.jcip.examples.part3_activityperftest.chapter10_avoidactivity;

import net.jcip.examples.part3_activityperftest.chapter10_avoidactivity.DynamicOrderDeadlock;
import net.jcip.examples.part3_activityperftest.chapter10_avoidactivity.DynamicOrderDeadlock.Account;
import net.jcip.examples.part3_activityperftest.chapter10_avoidactivity.DynamicOrderDeadlock.DollarAmount;

import java.util.Random;

/**
 * DemonstrateDeadlock

 使用DynamicOrderDeadlock类的transferMoney方法转账

 在典型条件下会发生死锁的循环   ---大多数系统下都会很快发生死锁

 */
public class DemonstrateDeadlock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++)
            accounts[i] = new Account();

        class TransferThread extends Thread {
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    DollarAmount amount = new DollarAmount(rnd.nextInt(1000));
                    try {
                        DynamicOrderDeadlock.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                    } catch (DynamicOrderDeadlock.InsufficientFundsException ignored) {
                    }
                }
            }
        }
        for (int i = 0; i < NUM_THREADS; i++)
            new TransferThread().start();
    }
}
