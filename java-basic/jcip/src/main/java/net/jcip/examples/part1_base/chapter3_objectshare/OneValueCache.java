package net.jcip.examples.part1_base.chapter3_objectshare;

import net.jcip.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * OneValueCache

 对数值及其因数分解结果进行缓存的不可变容器类

 在访问和更新多个相关变量时出现竞争条件问题，可以通过将这些变量全部保存在一个不可变对象中来消除（如果是可变对象就要使用锁来确保原子性）
 */

//不可变容器类

@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i,
                         BigInteger[] factors) {
        lastNumber = i;
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i))
            return null;
        else
            return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}
