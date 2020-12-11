package net.jcip.examples.part1_base.chapter3_objectshare;

/**
 * UnsafeStates

 内部的可变状态逸出

 发布对象指：使对象能够在当前作用域之外的代码中使用
 逸出指：当某个不应该发布的对象发布时

 当发布某个对象时可能会间接地发布其他对象，如：集合、数组

 */
class UnsafeStates {
    private String[] states = new String[]{
        "AK", "AL" /*...*/
    };

    public String[] getStates() {
        return states;
    }
}
