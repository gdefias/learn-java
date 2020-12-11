package net.jcip.examples.part4_advancedtopics.chapter15_atomicvar;

import net.jcip.annotations.ThreadSafe;
import net.jcip.examples.part4_advancedtopics.chapter15_atomicvar.PseudoRandom;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicPseudoRandom

 基于AtomicInteger实现的随机数生成器

 */
@ThreadSafe
public class AtomicPseudoRandom extends PseudoRandom {
    private AtomicInteger seed;

    AtomicPseudoRandom(int seed) {
        this.seed = new AtomicInteger(seed);
    }

    public int nextInt(int n) {
        while (true) {
            int s = seed.get();
            int nextSeed = calculateNext(s);
            if (seed.compareAndSet(s, nextSeed)) {
                int remainder = s % n;
                return remainder > 0 ? remainder : remainder + n;
            }
        }
    }
}
