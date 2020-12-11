package Concurrency;

/**
 * Created by Defias on 2017/8/19.

 线程的让步/礼让 - yield()

 */

public class Test3_yield extends Thread {

    public static void main(String[] args) {
        Test3_yield yt1 = new Test3_yield("张三");
        Test3_yield yt2 = new Test3_yield("李四");
        yt1.start();
        yt2.start();
    }


    public Test3_yield(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            if (i == 30) {
                this.yield();
            }
        }
    }


}


