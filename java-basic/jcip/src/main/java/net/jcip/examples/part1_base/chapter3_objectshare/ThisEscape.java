package net.jcip.examples.part1_base.chapter3_objectshare;

/**
 * ThisEscape

 发布一个内部的类实例

 */

//隐式的使用this引用逸出

public class ThisEscape {
    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {  //当ThisEscape发布EventListener时也隐含的发布了ThisEscape实例本身
                                                       //因为在这个（匿名）内部类的实例中包含了对ThisEscape实例的隐含引用
            public void onEvent(Event e) {
                doSomething(e);
            }
        });
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

