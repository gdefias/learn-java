package net.jcip.examples.part1_base.chapter5_buildmodel;
import net.jcip.annotations.GuardedBy;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * HiddenIterator

 隐藏在字符串连接中的迭代操作

 虽然加锁可以防止抛出ComcurrentModificationException，但必须记住在所有对共享容器进行迭代的地方都需要加锁
 */

//非线程安全的
public class HiddenIterator {
    @GuardedBy("this") private final Set<Integer> set = new HashSet<Integer>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    //可能会抛出异常ComcurrentModificationException
    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++)
            add(r.nextInt());

        //编译器会将字符串连接操作转换为调用StringBuilder.append(Object)
        //而这个方法会调用容器的toString方法，toString方法将迭代容器，在每个元素上调用toString来生成容器内容的格式化表示
        System.out.println("DEBUG: added ten elements to " + set);
    }
}
