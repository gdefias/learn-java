package net.jcip.examples.part4_advancedtopics.chapter14_customsynctool;
import net.jcip.annotations.ThreadSafe;
import net.jcip.examples.part4_advancedtopics.chapter14_customsynctool.BaseBoundedBuffer;

/**
 * BoundedBuffer

 （条件队列的使用姿势）

 使用条件队列实现的有界缓存

 */
@ThreadSafe
public class BoundedBuffer <V> extends BaseBoundedBuffer<V> {
    // CONDITION PREDICATE: not-full (!isFull())
    // CONDITION PREDICATE: not-empty (!isEmpty())
    public BoundedBuffer() {
        this(100);
    }

    public BoundedBuffer(int size) {
        super(size);
    }

    // BLOCKS-UNTIL: not-full
    public synchronized void put(V v) throws InterruptedException {
        while (isFull())  //测试条件谓词之前必须持有保护状态变量的锁
            wait();   //锁对象与条件队列对象必须是同一个对象  wait方法会释放锁  从wait中醒来后必须再次测试条件谓词
        doPut(v);
        notifyAll();
    }


    public synchronized void put2(V v) throws InterruptedException {
        while (isFull())
            wait();
        boolean wasEmpty = isEmpty();        //使用条件通知，可以提升性能，但较难正确实现，谨慎使用
        doPut(v);
        if(wasEmpty)
            notifyAll();
    }

    // BLOCKS-UNTIL: not-empty
    public synchronized V take() throws InterruptedException {
        while (isEmpty())
            wait();
        V v = doTake();
        notifyAll();   //调用notifyAll来通知wait线程 并不意味正在等待的条件谓词已经变成真了
                       //调用notifyAll时也必须持有与条件队列相关联的锁  发出通知后应该尽快释放锁  以使wait线程尽快获得锁
        return v;
    }

    // BLOCKS-UNTIL: not-full
    // Alternate form of put() using conditional notification
    public synchronized void alternatePut(V v) throws InterruptedException {
        while (isFull())
            wait();
        boolean wasEmpty = isEmpty();
        doPut(v);
        if (wasEmpty)
            notifyAll();
    }
}
