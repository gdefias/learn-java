package net.jcip.examples.part4_advancedtopics.chapter15_atomicvar;

import net.jcip.annotations.ThreadSafe;
import net.jcip.examples.part4_advancedtopics.chapter15_atomicvar.SimulatedCAS;

/**
 * CasCounter

  基于CAS实现的非阻塞计数器

 */
@ThreadSafe
public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (v != value.compareAndSwap(v, v + 1));
        return v + 1;
    }
}
