package net.jcip.examples.part1_base.chapter3_objectshare;

import net.jcip.annotations.NotThreadSafe;

/**
 * MutableInteger

 非线程安全的可变整数类
 */

@NotThreadSafe
public class MutableInteger {
    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
}








