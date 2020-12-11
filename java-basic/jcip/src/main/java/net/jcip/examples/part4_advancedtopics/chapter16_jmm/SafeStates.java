package net.jcip.examples.part4_advancedtopics.chapter16_jmm;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * SafeStates

 不可变对象的初始化安全性

 初始化安全性只能保证通过final域可达的值，且从构造函数完成时开始的可见性

 对于通过非final域可达的值，或构造函数完成后在其他方法中可能改变的值，必须采用同步来确保可见性

 */
@ThreadSafe
public class SafeStates {
    private final Map<String, String> states;

    public SafeStates() {
        states = new HashMap<String, String>();
        states.put("alaska", "AK");
        states.put("alabama", "AL");
        /*...*/
        states.put("wyoming", "WY");
    }

    public String getAbbreviation(String s) {
        return states.get(s);
    }
}
