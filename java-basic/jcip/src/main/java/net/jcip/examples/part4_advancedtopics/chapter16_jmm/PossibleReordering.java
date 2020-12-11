package net.jcip.examples.part4_advancedtopics.chapter16_jmm;

/**
 * PossibleReordering

 如果在程序中没有包含足够的同步，那么可能产生奇怪的结果

 内存级的重排序会使程序的行为变得不可预测

 */
public class PossibleReordering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            public void run() {
                a = 1;
                x = b;
            }
        });
        Thread other = new Thread(new Runnable() {
            public void run() {
                b = 1;
                y = a;
            }
        });
        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("( " + x + "," + y + ")");
    }
}
