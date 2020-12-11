package Concurrency.task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2020/07.
 * Description: Runnable任务
 */
public class Tasks {
    public static class PrintChar implements Runnable {
        private char charToPrint;
        private int times;

        public PrintChar(char c, int t) {
            charToPrint = c;
            times = t;
        }

        @Override
        public void run() {
            for (int i = 0; i < times; i++) {
                System.out.println(charToPrint);
            }
        }
    }

    public static class PrintNum implements Runnable {
        private int lastNum;

        public PrintNum(int n) {
            lastNum = n;
        }

        @Override
        public void run() {
            for (int i = 1; i <= lastNum; i++) {
                System.out.println(" " +i);
            }
        }
    }


    public static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task "+taskNum);
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task "+taskNum+"执行完毕");
        }
    }


    public static class TaskSon implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            TimeUnit.MILLISECONDS.sleep(3000);
            int sum = 0;
            for(int i=0;i<100;i++)
                sum += i;
            return sum;
        }
    }
}
