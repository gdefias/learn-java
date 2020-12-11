package net.jcip.examples.part1_base.chapter4_objectcomb;

import net.jcip.annotations.GuardedBy;

/**
 * PrivateLock

 */

//通过一个私有锁来保护状态

public class PrivateLock {
    private final Object myLock = new Object();
    @GuardedBy("myLock") Widget widget;

    void someMethod() {
        synchronized (myLock) {
            // Access or modify the state of widget
        }
    }
}
