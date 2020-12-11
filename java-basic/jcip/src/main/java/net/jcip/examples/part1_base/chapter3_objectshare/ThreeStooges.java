package net.jcip.examples.part1_base.chapter3_objectshare;

import net.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * ThreeStooges

 final域

 不变性

 线程安全性是不可变对象的固有属性之一

 不可变对象不等于将对象中所有的域都声明为final类型，即使对象中所有的域都是final类型的，这个对象也仍然可能是可变的，因为在final类型
 的域中可以保持对可变对象的引用

 当满足下列条件时对象才是不可变的：
 1、对象创建以后其状态就是不能修改
 2、对象的所有域都是final类型
 3、对象时正确创建的（在对象的创建期间，this引用没有逸出）
 */

//不可变对象：只有一种状态（不是说只有一个属性），并且该状态由构造函数来控制（被创建后其状态就不能被修改）
//在可变对象的基础上构建的不可变类
//不可变对象一定是线程安全的

//事实不可变对象（stooges）： 对象从技术上看是可变的（HashSet），但其状态在发布后不会再改变（final）
//在没有额外的同步情况下，任何线程都可以安全的使用被安全不发的事实不可变对象


@Immutable
 public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<String>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }

    public String getStoogeNames() {
        List<String> stooges = new Vector<String>();
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
        return stooges.toString();
    }
}
