package JVM;

import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2020/09.
 * Description: volatile变量自增运算测试

 每次运行程序，输出的结果都不一样，都是一个小于200000的数字(期望的结果是200000)

 自增运算"race++"并非原子操作

 VolatileTest的increase方法的字节码
 public static void increase()；
 Code:
 Stack=2, Locals=0, Args_size=0
 0:getstatic#13；//Field race:I
 3:iconst_1
 4:iadd
 5:putstatic#13；//Field race:I
 8:return
 LineNumberTable:
 line 14:0
 line 15:8

 对于自增运算，且不说这里编译为多字节码指令了（肯定不是原子操作），即使编译出来只有一条字节码指令，也并不意味执行这条指令就是一个原子操作
 一条字节码指令在解释执行时，解释器将要运行许多行代码才能实现它的语义，如果是编译执行，一条字节码指令也可能转化成若干条本地机器码指令

 更严谨的方式是使用-XX:+PrintAssembly参数输出反汇编来分析

 */
public class VolatileTest {
    public static volatile int race=0;

    public static void increase(){
        race++;
    }

    private static final int THREADS_COUNT=20;

    public static void main(String[]args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for(int i=0; i<THREADS_COUNT; i++){
            threads[i]=new Thread(new Runnable(){
                @Override
                public void run(){
                    for(int i=0;i<10000;i++){
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        //等待所有累加线程都结束
        while(Thread.activeCount()>2) {
            Thread.yield();
        }
        System.out.println(race);
    }
}