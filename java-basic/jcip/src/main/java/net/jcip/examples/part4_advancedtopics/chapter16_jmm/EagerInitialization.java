package net.jcip.examples.part4_advancedtopics.chapter16_jmm;

import net.jcip.annotations.ThreadSafe;

/**
 * EagerInitialization

 提前初始化    ---饿汉式单例模式

 */
@ThreadSafe
        public class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getResource() {
        return resource;
    }

    static class Resource {
    }
}
