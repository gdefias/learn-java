package net.jcip.examples.part4_advancedtopics.chapter16_jmm;

import net.jcip.annotations.ThreadSafe;

/**
 * ResourceFactory


 延长初始化占位类模式   ---使用Holder技术的懒汉式单例模式

 */
@ThreadSafe
public class ResourceFactory {
    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }

    public static Resource getResource() {
        return ResourceFactory.ResourceHolder.resource;
    }

    static class Resource {
    }
}
