package net.jcip.examples.part1_base.chapter3_objectshare;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * SynchronizedInteger

 线程安全的可变整数类

 set和get方法都需要加synchronized
 */

@ThreadSafe
public class SynchronizedInteger {
    @GuardedBy("this") private int value;


    public synchronized int get() {
        return value;
    }

    public synchronized void set(int value) {
        this.value = value;
    }
}
