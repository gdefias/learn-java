package net.jcip.examples.part1_base.chapter2_threadsafe;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * UnsafeCountingFactorizer

 在没有同步的情况下统计已处理请求数量的servlet ---非线程安全

 竞态条件： 当某个计算的准确性取决于多个线程的交替执行时序时，就会发生竞态条件

 竞态条件并不总是会产生错误，还需要某种不恰当的执行时序，竞态条件也可能导致严重的问题


 数据竞争：如果访问共享的非final类型的域时没有采用同步来进行协同，那么就会出现数据竞争，当一个线程写入一个变量而另一个线程接下来读取
 这个变量，或者读取一个之前由另一个线程写入的变量时，并且这两个线程之间没有使用同步，那么就可能发生数据竞争

 并非所有竞态条件都是数据竞争，并非所有的数据竞争都是竞态条件，二者都可能是并发程序失败
 */


//存在竞态条件，也存在数据竞争
@NotThreadSafe
public class UnsafeCountingFactorizer extends GenericServlet implements Servlet {
    private long count = 0;

    public long getCount() {
        return count;
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        ++count;
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[] { i };
    }
}
