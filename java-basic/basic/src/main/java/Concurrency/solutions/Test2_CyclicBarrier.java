package Concurrency.solutions;
import java.util.concurrent.*;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: CyclicBarrier

 CyclicBarrier 循环屏障

 若干个任务分别到达某一步骤前调用await()停下，当所有任务都到达时再同步开始执行

 void	reset()  将屏障重置为初始状态
 barrier在释放等待线程后可以重置，所以称它为循环barrier

 */
public class Test2_CyclicBarrier {
    public static void main(String[] args) {
        int nHorses = 7;  //马的数量
        int pause = 2000;  //
        new HorseRace(nHorses, pause);
    }
}

//赛马游戏
class HorseRace {
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<Horse>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public HorseRace(int nHorses, final int pause) {
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            public void run() {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++)
                    s.append("="); // The fence on the racetrack
                System.out.println(s);
                for (Horse horse : horses)
                    System.out.println(horse.tracks());

                for (Horse horse : horses)
                    if (horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse + "won!");
                        exec.shutdownNow();
                        return;
                    }

                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    System.out.println("barrier-action sleep interrupted");
                }
            }
        });

        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }
}

class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random rand = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b) {
        barrier = b;
    }

    public synchronized int getStrides() {
        return strides;
    }

    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    //随机产生一个步长
                    strides += rand.nextInt(3); // Produces 0, 1 or 2
                }
                barrier.await();
            }
        } catch(InterruptedException e) {
            // A legitimate way to exit
        } catch(BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "Horse " + id + " ";
    }

    //使用不同数量的*表示步长
    public String tracks() {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < getStrides(); i++)
            s.append("*");
        s.append(id);
        return s.toString();
    }
}
