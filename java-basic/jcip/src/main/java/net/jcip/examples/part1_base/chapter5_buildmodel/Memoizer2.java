package net.jcip.examples.part1_base.chapter5_buildmodel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Memoizer2

 使用ConcurrentHashMap替换HashMap

 存在的问题：
 如果compute(a)是一个耗时很长的计算过程，当线程A在计算的时候，线程B可能也需要compute(a)的结果，但由于此时，B并不知道A在计算中，
 因此会进行一次重复计算
 */
public class Memoizer2 <A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
