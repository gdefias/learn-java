package net.jcip.examples.part1_base.chapter5_buildmodel;

import net.jcip.annotations.GuardedBy;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Memoizer1

 构建高效且可伸缩的结果缓存

 使用HashMap和同步机制来初始化缓存

 存在的问题：
 如果有多个线程在排队等待还未计算出的结果，那么其他线程将会被阻塞很长时间   ---可伸缩性问题
 */
public class Memoizer1 <A, V> implements Computable<A, V> {
    @GuardedBy("this") private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    //对整个compute方法加锁
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}


interface Computable <A, V> {
    V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction
        implements Computable<String, BigInteger> {
    public BigInteger compute(String arg) {
        // after deep thought...
        return new BigInteger(arg);
    }
}
