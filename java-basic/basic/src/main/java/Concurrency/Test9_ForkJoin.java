package Concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
/**
 * Created by Defias on 2020/07.
 * Description: Fork/Join框架

 采用Fork/Join框架来求和：1+2+3+…+10000的结果
 */

public class Test9_ForkJoin {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = new SumTask(1, 10000);

        pool.submit(task);
        System.out.println(task.get());

        //System.out.println(pool.invoke(task));
    }


    static final class SumTask extends RecursiveTask<Integer> {
        private static final long serialVersionUID = 1L;

        final int start; //开始计算的数
        final int end; //最后计算的数

        SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            //如果计算量小于1000，直接计算并返回结果
            if(end - start < 1000) {
                System.out.println(Thread.currentThread().getName() + " 开始执行：" + start + "-" + end);
                int sum = 0;
                for(int i = start; i <= end; i++)
                    sum += i;
                return sum;
            }
            //如果计算量大于1000，那么拆分为两个子任务分别计算
            SumTask task1 = new SumTask(start, (start + end) / 2);
            SumTask task2 = new SumTask((start + end) / 2 + 1, end);
            //执行任务
            task1.fork();
            task2.fork();
            //获取任务执行的结果
            return task1.join() + task2.join();
        }
    }
}
