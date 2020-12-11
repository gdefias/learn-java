package Concurrency;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Defias on 2020/07.
 * Description: 基于Fork/Join框架的归并排序
 */

public class Test9_ForkJoinMSort {
    private static int MAX = 100;
    private static int[] arr = new int[MAX];

    static {
        Random random = new Random();
        for (int i = 1; i <= MAX; i++) {
            arr[i - 1] = random.nextInt(MAX);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask(arr);
        ForkJoinTask<int[]> taskResult = pool.submit(task);

        int[]  res = taskResult.get();
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println(Arrays.toString(res));
    }

    static class MergeSortTask extends RecursiveTask<int[]> {
        private int[] source;

        public MergeSortTask(int[] source) {
            this.source = source;
        }

        @Override
        protected int[] compute() {
            int length = source.length;
            // 如果条件成立，说明任务中要进行排序的集合还不够小
            if (length > 2) {
                int mid = length / 2;

                // 拆分成两个子任务
                MergeSortTask task1 = new MergeSortTask(Arrays.copyOf(source, mid));
                task1.fork();

                MergeSortTask task2 = new MergeSortTask(Arrays.copyOfRange(source, mid, length));
                task2.fork();

                // 将两个有序的数组，合并成一个有序的数组
                int[] result1 = task1.join();
                int[] result2 = task2.join();
                return joinInts(result1, result2);
            }
            // 集合中只有一个或者两个元素，可以进行这两个元素的比较排序了
            else {
                if (length == 1 || source[0] < source[1]) {
                    return source;
                } else {
                    int[] temp = new int[length];
                    temp[0] = source[1];
                    temp[1] = source[0];
                    return temp;
                }
            }
        }


        //功能描述 : 将两个有序数组合并起来
        private int[] joinInts(int[] arr1, int[] arr2) {
            int left = 0;
            int right = 0;
            int[] mergeArr = new int[arr1.length + arr2.length];
            if (mergeArr.length == 0) {
                return null;
            }
            for (int i = 0; i < arr1.length + arr2.length; i++) {
                if (arr1.length == left) {
                    mergeArr[i] = arr2[right];
                    right++;
                    continue;
                } else if (arr2.length == right) {
                    mergeArr[i] = arr1[left];
                    left++;
                    continue;
                }
                if (arr1[left] <= arr2[right]) {
                    mergeArr[i] = arr1[left];
                    left++;
                } else {
                    mergeArr[i] = arr2[right];
                    right++;
                }
            }
            return mergeArr;
        }
    }
}
