package net.jcip.main;

/**
 * Created by Defias on 2017/8/19.
 */


public class Main {

    public static void main(String[] args) {
        Thread thread1 =  new Thread(new Normal("张三"));
        Thread thread2 =  new Thread(new Normal("李四"));
        Thread thread3 =  new Thread(new Normal("王五"));
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
