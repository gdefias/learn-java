package net.jcip.examples.part1_base.chapter2_threadsafe;
import net.jcip.annotations.NotThreadSafe;

/**
 * LazyInitRace

 延迟初始化中的竞态条件

 */


@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null)
            instance = new ExpensiveObject();
        return instance;
    }
}

class ExpensiveObject { }

