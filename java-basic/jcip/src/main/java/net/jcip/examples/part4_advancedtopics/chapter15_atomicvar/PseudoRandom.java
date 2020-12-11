package net.jcip.examples.part4_advancedtopics.chapter15_atomicvar;

/**
 * PseudoRandom
 *
 * @author Brian Goetz and Tim Peierls
 */
public class PseudoRandom {
    int calculateNext(int prev) {
        prev ^= prev << 6;
        prev ^= prev >>> 21;
        prev ^= (prev << 7);
        return prev;
    }
}