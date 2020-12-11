package net.jcip.examples.part1_base.chapter3_objectshare;

import net.jcip.examples.part1_base.chapter3_objectshare.Holder;

/**
 * StuffIntoPublic

 在没有足够同步的情况下发布对象

 任何线程都可以不需要额外同步的情况下安全地访问不可变对象，即使在发布这些对象时没有使用同步。但是对于可变对象，则必须通过安全的方式来发布
 ，这意味着在发布和使用该对象的线程时都必须使用同步

 要安全地发布一个对象，对象的引用以及对象的状态必须同时对其他线程可见。一个正确构造的对象可以通过以下方式来安全地发布：
 1、在静态初始化函数中初始化一个对象引用；
 2、将对象的引用保存到volatile类型的域或者AtomicReferance对象中；
 3、将对象的引用保存到某个正确构造对象的final类型域中；
 4、将对象的引用保存到一个由锁保护的域中

 */


public class StuffIntoPublic {
    public Holder holder;  //不正确的发布 非线程安全的

    //使用静态初始化器进行安全的发布
    //public static Holder holder = new Holder(42);

    public void initialize() {
        holder = new Holder(42);
    }
}
