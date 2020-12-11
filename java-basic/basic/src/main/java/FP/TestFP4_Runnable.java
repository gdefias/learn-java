package FP;

/**
 * Created by Defias on 2020/07.
 * Description:  Runnable接口

 Runnable接口：符合特殊的单方法接口格式：它的方法 run() 不带参数，也没有返回值。因此可以使用 Lambda 表达式和方法引用作为 Runnable
 */

public class TestFP4_Runnable {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                System.out.println("Anonymous");
            }
        }).start();

        new Thread(
                () -> System.out.println("lambda")
        ).start();

        new Thread(Go::go).start();
    }
}


class Go {
    static void go() {
        System.out.println("Go::go()");
    }
}