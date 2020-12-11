package net.jcip.examples.part1_base.chapter5_buildmodel;

import net.jcip.examples.part2_structapplication.chapter7_cancelclose.LaunderThrowable;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Memoizer3

 基于FutureTask的Memoizing封装器

 具有很好的并发性，若结果已经计算出来那么将立即返回，如果其他线程真在计算该结果，那么新的线程将一直等待这个结果被计算出来

 仍存在的问题：
 两个线程计算出相同值，虽然发生的概率远小于Memoizer2中发生的概率
 由于compute方法中的if代码块仍然是非原子的"先检查再执行"操作

 */
public class Memoizer3 <A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache
            = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                public V call() throws InterruptedException {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run(); // call to c.compute happens here
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }
}