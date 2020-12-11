package net.jcip.examples.part0;

import net.jcip.annotations.NotThreadSafe;

/**
 * UnsafeSequence

 非线程安全的数值序列生成器

 */

@NotThreadSafe
public class UnsafeSequence {
    private int value;

    /**
     * Returns a unique value.
     */
    public int getNext() {
        return value++;
    }
}
