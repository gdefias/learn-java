package Concurrency;

/**
 * Created by Defias on 2020/07.
 * Description: 创建有响应的用户界面
 */

public class Test2_Response {
    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
        //! new UnresponsiveUI(); // Must kill this process

        new ResponsiveUI();
        System.in.read();  //读取到输入后就不会陷入死循环
        System.out.println(ResponsiveUI.d); // Shows progress
    }
}



class UnresponsiveUI {
    public volatile double d = 1;
    public UnresponsiveUI() throws Exception {
        while(d > 0)  //死循环
            d = d + (Math.PI + Math.E) / d;
        System.in.read(); // Never gets here
    }
}


class ResponsiveUI extends Thread {
    public static volatile double d = 1;
    public ResponsiveUI() {
        setDaemon(true);  //守护线程
        start();
    }
    public void run() {
        while(true) {  //死循环置于run中
            d = d + (Math.PI + Math.E) / d;
        }
    }
}