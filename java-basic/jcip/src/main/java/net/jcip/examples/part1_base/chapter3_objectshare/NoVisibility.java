package net.jcip.examples.part1_base.chapter3_objectshare;

/**
 * NoVisibility

 在没有同步的情况下共享变量   ---可见性问题


 在没有同步的情况下，编译器、处理器以及运行时等都可能对操作的执行顺序进行一些意想不到的调整，在缺乏足够同步的多线程程序中，要想对内存操作
 的执行顺序进行判断，几乎无法得出正确的结论

 */

//在没有同步的情况下共享变量  会出现可见性问题！

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();  //可能死循环 也可能输出0
        number = 42;  //没有使用足够的同步机制，无法保证主线程写入的ready值和number值对于读线程来说是可见的（也不一定不可见）
        ready = true;
    }
}
