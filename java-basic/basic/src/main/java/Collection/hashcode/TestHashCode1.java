package Collection.hashcode;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: 散列与散列码  --Equals  Hash  Hashcode


 equals()
 用来判断两个对象是否相等，定义在JDK的Object.java中
 通过判断两个对象的地址是否相等(即，是否是同一个对象)来区分它们是否相等。源码如下：
 public boolean equals(Object obj) {
    return (this == obj);
 }


 hashCode()
 获取哈希码，也称为散列码；它实际上是返回一个int整数。这个哈希码的作用是确定该对象在哈希表中的索引位置。
 hashCode() 定义在JDK的Object.java中，这就意味着Java中的任何类都包含有hashCode() 函数，但是，仅仅当创建并某个“类的散列表”时，
 该类的hashCode()才有用，也就是说：hashCode()在散列表（HashMap，Hashtable，HashSet）中才有用，在其它情况下没用。在散列表中
 hashCode()的作用是获取对象的散列码，进而确定该对象在散列表中的位置


 hashCode() 和 equals()的关系？创建一个类A（继承类B，B可以是Object）时重写了equals()方法是否一定也需要重写hashcode()方法？
 1、可以确定不会出现A和B放入同一个散列表的情况
 即：不会在HashSet, Hashtable, HashMap等等这些本质是散列表的数据结构中同时加入A和B

 在这种情况下，类的“hashCode() 和 equals() ”没有半毛钱关系的！
 这种情况下，equals()用来比较该类的两个对象是否相等。而hashCode()则根本没有任何作用，所以，重写equals，可以不用理会hashCode()


 2、可以确定会出现A和B放入同一个散列表的情况
 即：会在HashSet, Hashtable, HashMap等等这些本质是散列表的数据结构中同时加入了A和B
 在这种情况下，该类的“hashCode() 和 equals() ”是有关系的：
 1)、如果两个对象相等，那么它们的hashCode()值一定相同。这里的相等是指，通过equals()比较两个对象时返回true
 2)、如果两个对象hashCode()相等，它们并不一定相等。因为在散列表中，hashCode()相等，即两个键值对的哈希值相等。然而哈希值相等，并不
 一定能得出键值对相等（哈希冲突：“两个不同的键值对，哈希值相等”)

 在这种情况下。若要判断两个对象是否相等，除了要覆盖equals()之外，也要覆盖hashCode()函数
 否则：假如两个Java对象A和B，A和B相等（eqauls结果为true），但A和B的哈希码不同，则A和B存入HashMap时的哈希码计算得到的HashMap的内
 部数组位置索引可能不同，那么A和B很有可能允许同时存入HashMap（违背了MAP的KEY不能重复的定义）

 */


public class TestHashCode1 {

    public static void main(String[] args) throws Exception {

        //Groundhog没有实现equal
        //detectSpring(Groundhog.class);

        //Groundhog2实现了equal
        detectSpring(Groundhog2.class);
    }


    //天气预报
    // Uses a Groundhog or class derived from Groundhog:
    public static <T extends Groundhog>
    void detectSpring(Class<T> type) throws Exception {
        Constructor<T> ghog = type.getConstructor(int.class);
        Map<Groundhog,Prediction> map =
                new HashMap<Groundhog,Prediction>();
        for(int i = 0; i < 10; i++)
            map.put(ghog.newInstance(i), new Prediction());
        print("map = " + map);

        Groundhog gh = ghog.newInstance(3);
        print("Looking up prediction for " + gh);
        if(map.containsKey(gh)) {
            print(map.get(gh).toString());
        } else {
            print("Key not found: " + gh);
        }
    }
}
