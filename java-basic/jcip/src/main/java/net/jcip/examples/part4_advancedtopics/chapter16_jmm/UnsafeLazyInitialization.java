package net.jcip.examples.part4_advancedtopics.chapter16_jmm;

import net.jcip.annotations.NotThreadSafe;

/**
 * UnsafeLazyInitialization

 不安全的延迟初始化

 */
@NotThreadSafe
public class UnsafeLazyInitialization {
    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null)
            resource = new Resource(); // unsafe publication
        return resource;
    }

    static class Resource {
    }
}
