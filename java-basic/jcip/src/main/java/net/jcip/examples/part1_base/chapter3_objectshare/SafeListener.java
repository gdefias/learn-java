package net.jcip.examples.part1_base.chapter3_objectshare;

/**
 * SafeListener

 安全的对象构造构成

 不要在构造过程中使用this引用逸出

 */

//使用私有的构造函数+公共的工厂方法来防止this引用在构造过程中逸出

public class SafeListener {
    private final EventListener listener;

    private SafeListener() {  //私有的构造函数
        listener = new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }
        };
    }

    //只有当构造函数返回时，this引用才应该从线程中逸出，构造函数可以将this引用保存到某个地方，只要其他线程不会在构造函数完成之前使用它

    //公共的工厂方法
    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }

    void doSomething(Event e) {
    }


    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
}

