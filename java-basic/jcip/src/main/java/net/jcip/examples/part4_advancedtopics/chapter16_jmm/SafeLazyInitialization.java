package net.jcip.examples.part4_advancedtopics.chapter16_jmm;

import net.jcip.annotations.ThreadSafe;

/**
 * SafeLazyInitialization

 线程安全的延迟初始化  ---性能不高的懒汉式单例模式

 */
@ThreadSafe
public class SafeLazyInitialization {
    private static Resource resource;

    public synchronized static Resource getInstance() {
        if (resource == null)
            resource = new Resource();
        return resource;
    }

    static class Resource {
    }
}
