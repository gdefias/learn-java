package net.jcip.examples.part1_base.chapter2_threadsafe;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * CachedFactorizer

 缓存最近执行因数分解的数值及其计算结果的servlet   --线程安全的且不会造成性能严重下降

 同步块合理的大小需要权衡：安全性、简单性、性能


 当执行时间较长的计算或者可能无法快速完成的操作时（IO）一定不要持有锁
 */

//没有使用原子类型：因为已经使用了同步块来构造原子操作，虽然使用两种不同的同步机制不会带来混乱，但也不会在性能或安全性上带来任何好处

@ThreadSafe
public class CachedFactorizer extends GenericServlet implements Servlet {
    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @GuardedBy("this") private long hits;      //统计请求的次数
    @GuardedBy("this") private long cacheHits;   //统计命中缓存的请求次数

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;

        //使用synchronized代码块  ---缩小了同步的/加锁的范围
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}
