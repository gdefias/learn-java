package net.jcip.examples.part1_base.chapter2_threadsafe;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * UnsafeCachingFactorizer

 在没有足够原子性保证的情况下对其最近计算结果进行缓存  ---非线程安全

 不变性条件：lastFactors中缓存的因数之积应该等于在lastNumber中缓存的数值

 在多线程时不变性条件中的每个变量都必须由同一个锁来保护，否则可能破坏这个不变性条件
 */

@NotThreadSafe
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {
    private final AtomicReference<BigInteger> lastNumber
            = new AtomicReference<BigInteger>();       //相当于缓存的key
    private final AtomicReference<BigInteger[]> lastFactors
            = new AtomicReference<BigInteger[]>();  //相当于缓存的value

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber.get()))
            encodeIntoResponse(resp, lastFactors.get());
        else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(resp, factors);
        }
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

