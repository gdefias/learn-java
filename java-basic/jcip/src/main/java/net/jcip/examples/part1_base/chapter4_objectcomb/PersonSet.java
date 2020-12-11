package net.jcip.examples.part1_base.chapter4_objectcomb;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * PersonSet

 实例封闭

 将数据封装在对象内部，可以将数据的访问限制在对象的方法上，从而更容易确保线程在访问数据时总能持有正确的锁

 */

//通过封闭与加锁机制来确保线程安全

//虽然HashSet是非线程安全的，但由于mySet是私有的并且不会逸出，因此HashSet被封闭在PersonSet中

@ThreadSafe
public class PersonSet {
    @GuardedBy("this") private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

    interface Person {
    }
}
