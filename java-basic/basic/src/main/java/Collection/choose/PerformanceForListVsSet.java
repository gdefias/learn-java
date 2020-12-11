package Collection.choose;

/**
 * Created by Defias on 2017/2/28.
 *
 * 比较集合Set和线性表List的性能
 */

import java.util.*;

public class PerformanceForListVsSet {
    public static void main(String[] args) {
        // Create a hash set, and test its performance
        Collection<Integer> set1 = new HashSet<Integer>();
        System.out.println("Time for hash set is " + getTestTime(set1, 500000) + " milliseconds");

        // Create a linked hash set, and test its performance
        Collection<Integer> set2 = new LinkedHashSet<Integer>();
        System.out.println("Time for linked hash set is " + getTestTime(set2, 500000) + " milliseconds");

        // Create a tree set, and test its performance
        Collection<Integer> set3 = new TreeSet<Integer>();
        System.out.println("Time for tree set is " + getTestTime(set3, 500000) + " milliseconds");

        // Create an array list, and test its performance
        Collection<Integer> list1 = new ArrayList<Integer>();
        System.out.println("Time for array list is " + getTestTime(list1, 60000) + " milliseconds");

        // Create a linked list, and test its performance
        Collection<Integer> list2 = new LinkedList<Integer>();
        System.out.println("Time for linked list is " + getTestTime(list2, 60000) + " milliseconds");
    }

    public static long getTestTime(Collection<Integer> c, int size) {
        long startTime = System.currentTimeMillis();

        // Add numbers 0, 1, 2, ..., size - 1 to the array list
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++)
            list.add(i);

        Collections.shuffle(list); // Shuffle the array list

        // Add the elements to the container
        for (int element: list)
            c.add(element);

        Collections.shuffle(list); // Shuffle the array list

        // Remove the element from the container
        for (int element: list)
            c.remove(element);  //remove的操作比ArrayList还慢，而且慢的多
                                //LinkedList的remove(int index)和remove(Object o)这两个方法都做不到O(1)的时间，而是O(n)
                                //这是因为O(1)时间是对于某个已经确定的节点。而LinkedList中，首先必须通过一个循环，找到第一个
                                //出现的Object o，或者走到index这个位置，再进行操作。也就是，有一个get的过程

        long endTime = System.currentTimeMillis();
        return endTime - startTime; // Return the execution time
    }
}
