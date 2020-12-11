package JVM;
import java.util.Vector;

/**
 * Created by Defias on 2020/09.
 * Description:
 *
 * 必须加入synchronized同步以保证Vector访问的线程安全性，否则仍然存在线程安全问题（Vector提供的只是相对线程安全，而不是绝对）
 *
 * 因为尽管Vector的get()、remove()和size()方法都是同步的，但是在多线程的环境中，如果另一个线程恰好在错误的时间里删除了一个元素，导致
 * 序号i已经不再可用的话，再用i访问数组就会抛出一个ArrayIndexOutOfBoundsException
 *
 */
public class VectorTest {
    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[]args) {
        while(true){
            for(int i=0;i<10;i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized(vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            vector.remove(i);
                        }
                    }
                }
            });

            Thread printThread=new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized(vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            System.out.println((vector.get(i)));
                        }
                    }
                }
            });
            removeThread.start();
            printThread.start();

            //不要同时产生过多的线程, 否则会导致操作系统假死
            while(Thread.activeCount()>20);
        }
    }

}
