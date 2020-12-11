package JVM;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Defias on 2020/09.
 * Description:  Atomic变量自增运算测试  -- 使用CAS操作来避免阻塞同步
 *
 *
 */
public class AtomicTest {
    public static AtomicInteger race = new AtomicInteger(0);

    public static void increase(){
        race.incrementAndGet();
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[]args)throws Exception{
        Thread[]threads = new Thread[THREADS_COUNT];

        for(int i=0; i<THREADS_COUNT; i++){
            threads[i] = new Thread(new Runnable(){
                @Override
                public void run(){
                    for(int i=0; i<10000; i++){
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while(Thread.activeCount()>2)
            Thread.yield();
        System.out.println(race);
    }
}