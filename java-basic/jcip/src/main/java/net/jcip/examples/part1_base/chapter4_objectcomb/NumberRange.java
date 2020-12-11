package net.jcip.examples.part1_base.chapter4_objectcomb;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * NumberRange

 仅靠委托并不足以实现线程安全性

 这种包含多个变量的不变性条件带来原子性需求：这些相关变量必须在单个原子操作中进行读取或更新

 */

//线程不安全的 （虽然两个状态是AtomicInteger类型的是线程安全的）
public class NumberRange {
    // 不变性条件: lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        // Warning -- 不安全的"先检查后执行"
        if (i > upper.get())
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        lower.set(i);
    }

    public void setUpper(int i) {
        // Warning -- 不安全的"先检查后执行"
        if (i < lower.get())
            throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }
}

