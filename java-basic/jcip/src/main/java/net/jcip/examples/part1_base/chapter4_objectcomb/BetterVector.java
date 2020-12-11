package net.jcip.examples.part1_base.chapter4_objectcomb;

import net.jcip.annotations.ThreadSafe;

import java.util.Vector;

/**
 * BetterVector

 在现有线程安全类中添加功能

 扩展Vector并增加一个"若没有则添加"的方法

 并非所有的类都像Vector那样将状态向子类公开


 扩展方法比直接将代码添加到线程安全类中（很多情况不一定允许这么做）更加脆弱，因为现在的同步策略实现被分布到多个单独维护的源代码文件中，
 如果底层类改变了同步策略，那么子类会被破坏
 */
@ThreadSafe
public class BetterVector <E> extends Vector<E> {
    // When extending a serializable class, you should redefine serialVersionUID
    static final long serialVersionUID = -3963416950630760754L;

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent)
            add(x);
        return absent;
    }
}
