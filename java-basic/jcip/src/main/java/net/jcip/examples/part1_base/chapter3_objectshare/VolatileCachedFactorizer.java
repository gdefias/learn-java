package net.jcip.examples.part1_base.chapter3_objectshare;

import net.jcip.annotations.ThreadSafe;
import net.jcip.examples.part1_base.chapter3_objectshare.OneValueCache;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * VolatileCachedFactorizer

 使用指向不可变容器对象的volatile类型引用以缓存最新的结果
 */


@ThreadSafe
public class VolatileCachedFactorizer extends GenericServlet implements Servlet {

    private volatile OneValueCache cache = new OneValueCache(null, null);

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
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

