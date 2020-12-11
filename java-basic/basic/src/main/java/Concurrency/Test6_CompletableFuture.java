package Concurrency;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Defias on 2020/09.
 * Description: CompletableFuture
 */
public class Test6_CompletableFuture {
    public static void main(String[] args) throws Exception {
        //test1();
        test2();
    }

    public static void test1() throws Exception {
        // 创建异步执行任务:
        //supplyAsync方法：入参需要一个实现了Supplier接口的对象（fetchPrice()静态方法的签名符合Supplier接口的定义）
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(Test6_CompletableFuture::fetchPrice);

        // 如果执行成功:
        //thenAccept方法：入参需要一个Consumer接口对象
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });

        // 如果执行异常:
        //exceptionally方法：入参需要一个Function接口对象
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    public static void test2() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(Test6_CompletableFuture::fetchPrice2);

        // 暂停让Future完成:
        Thread.sleep(3000);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("thenAccept: " + result);
        });

        Thread.sleep(2000);
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    public static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

    public static double fetchPrice2() {
        System.out.println("fetched!");
        return 123.456;
    }

}
