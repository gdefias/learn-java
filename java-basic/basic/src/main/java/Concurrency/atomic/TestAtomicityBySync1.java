package Concurrency.atomic;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2020/07.
 * Description: 正确的使用同步
 */


public class TestAtomicityBySync1 {
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {
        public void run() {
            while(true) {
                int serial = SerialNumberGenerator.nextSerialNumberSafe();
                if(serials.contains(serial)) {
                    System.out.println("Duplicate: " + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < SIZE; i++)
            exec.execute(new TestAtomicityBySync1.SerialChecker());

        int sleeptime = 3;
        TimeUnit.SECONDS.sleep(sleeptime);
        System.out.println("No duplicates detected");
        System.exit(0);
    }
}



class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    public static int nextSerialNumber() {
        return serialNumber++; // Not thread-safe
    }

    public synchronized static int nextSerialNumberSafe() {
        return serialNumber++; // thread-safe
    }
}


class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;

    public CircularSet(int size) {
        array = new int[size];
        len = size;
        // Initialize to a value not produced
        // by the SerialNumberGenerator:
        for(int i = 0; i < size; i++)
            array[i] = -1;
    }

    public synchronized void add(int i) {
        array[index] = i;
        // Wrap index and write over old elements:
        index = ++index % len;
    }

    public synchronized boolean contains(int val) {
        for(int i = 0; i < len; i++)
            if(array[i] == val)
                return true;
        return false;
    }
}