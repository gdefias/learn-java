package Collection;

import java.util.Set;

/**
 * Created by Defias on 2017/2/28.
 *
 * 链式散列集LinkedHashSet
 * 用一个链表实现来扩展HashSet类，支持对集合元素排序
 *
 *
 java.util.LinkedHashSet<E>类
 --------------------------------------------------------
 +LinkedHashSet()
 +LinkedHashSet(c: Collection<? extends E>)
 +LinkedHashSet(initialCapacity: int)
 +LinkedHashSet(initialCapacity: int, loadFactor: float
 */

import java.util.*;

public class TestSet_LinkedHashSet {
    public static void main(String[] args) {
        // Create a hash set
        Set<String> set = new LinkedHashSet<String>();

        // Add strings to the set
        set.add("London");
        set.add("Paris");
        set.add("New York");
        set.add("San Francisco");
        set.add("Beijing");
        set.add("New York");

        System.out.println(set);

        // Display the elements in the hash set
        for (Object element: set)
            System.out.print(element.toString().toLowerCase() + " ");
    }

}

