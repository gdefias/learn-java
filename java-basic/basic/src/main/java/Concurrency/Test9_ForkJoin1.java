package Concurrency;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * Created by Defias on 2017/2/26.

 使用Fork/Join框架

 在线性表中查找最大值
 */

public class Test9_ForkJoin1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //test1();
        //test2();
        test3();
    }


    //在线性表中查找最大值
    public static void test1() {
        final int N = 9000000;
        int[] list = new int[N];
        for (int i=0; i<list.length; i++)
            list[i] = i;

        long startTime = System.currentTimeMillis();
        ForkJoinPool fjpool = new ForkJoinPool();
        RecursiveTask<Integer> task = new MaxTask(list, 0, list.length);
        int maxnum =  fjpool.invoke(task);
        System.out.println("\nThe maximal number is " + maxnum);
        long endTime = System.currentTimeMillis();

        //availableProcessors()： 返回可用处理器的数目
        System.out.println("The number of processors is " + Runtime.getRuntime().availableProcessors());
        System.out.println("Time is " + (endTime - startTime) + " milliseconds");
    }


    //第n个斐波拉契数
    public static void test2() throws InterruptedException, ExecutionException {
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> future = pool.submit(new Fibonacci(10));
        System.out.println(future.get());
        pool.shutdown();
    }


    //数组排序
    public static void test3() throws ExecutionException, InterruptedException {
        long[] array = new long[120];
        for (int i = 0; i < array.length; i++) {
            array[i] = (long) (Math.random() * 1000);
        }
        System.out.println(Arrays.toString(array));

        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new SortTask(array));
        pool.awaitTermination(5, TimeUnit.SECONDS);
        pool.shutdown();

        System.out.println(Arrays.toString(array));
    }


    private static class SortTask extends RecursiveAction {
        static final int THRESHOLD = 100;

        final long[] array;
        final int lo, hi;

        public SortTask(long[] array, int lo, int hi) {
            this.array = array;
            this.lo = lo;
            this.hi = hi;
        }

        public SortTask(long[] array) {
            this(array, 0, array.length);
        }

        public void sortSequentially(int lo, int hi) {
            Arrays.sort(array, lo, hi);
        }

        public void merge(int lo, int mid, int hi) {
            long[] buf = Arrays.copyOfRange(array, lo, mid);
            for (int i = 0, j = lo, k = mid; i < buf.length; j++) {
                array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
            }
        }

        @Override
        protected void compute() {
            if (hi - lo < THRESHOLD) {
                sortSequentially(lo, hi);
            }else {
                int mid = (lo + hi) >>> 1;
                invokeAll(new SortTask(array, lo, mid), new SortTask(array, mid, hi));
                merge(lo, mid, hi);
            }
        }
    }


    private static class Fibonacci extends RecursiveTask<Integer> {
        final int n;
        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }else {
                Fibonacci f1 = new Fibonacci(n - 1);
                f1.fork();
                Fibonacci f2 = new Fibonacci(n - 1);
                return f2.compute() + f1.join();
            }
        }
    }


    private static class MaxTask extends RecursiveTask<Integer> {
        private final static int THRESHOLD = 10000; //阈值
        private int[] list;
        private int low;
        private int high;

        public MaxTask(int[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        public Integer compute() {
            //规模小于阈值时直接循环比较
            if (high-low < THRESHOLD) {
                int max = list[0];
                for (int i = low; i < high; i++)
                    if (list[i] > max)
                        max = list[i];
                return new Integer(max);
            } else {
                //规模大于阀值时使用递归进行并行异步执行
                int mid = (low + high) / 2;
                RecursiveTask<Integer> left = new MaxTask(list, low, mid);
                RecursiveTask<Integer> right = new MaxTask(list, mid, high);
                right.fork();
                left.fork();
                return new Integer(Math.max(left.join().intValue(), right.join().intValue()));
            }
        }
    }
}