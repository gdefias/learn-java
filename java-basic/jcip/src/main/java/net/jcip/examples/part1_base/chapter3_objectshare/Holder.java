package net.jcip.examples.part1_base.chapter3_objectshare;

/**
 * Holder

 不正确的发布：正确的对象被破坏

 由于未被正确发布（StuffIntoPublic中），这个类可能出现故障
 */


public class Holder {
    private int n;   //Holder是可变的
    //private final int n;   //Holder是不可变的， 即使Holder没有被正确的发布，assertSanity中也不会抛出AssertionError

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n)
            throw new AssertionError("This statement is false.");
    }
}
