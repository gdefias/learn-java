package net.jcip.examples.part4_advancedtopics.chapter16_jmm;

import net.jcip.annotations.NotThreadSafe;

/**
 * DoubleCheckedLocking

 双重检查加锁     ---建议尽量不要使用这种方式，而是使用Holder技术（更好、更易理解）

 */
@NotThreadSafe
public class DoubleCheckedLocking {
    //private static Resource resource;   //不正确的
    private static volatile Resource resource;   //正确的

    public static Resource getInstance() {
        if (resource == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null)
                    resource = new Resource();
            }
        }
        return resource;
    }

    static class Resource {

    }
}
