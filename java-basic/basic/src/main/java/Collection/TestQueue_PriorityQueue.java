package Collection;

/**
 * Created by Defias on 2017/2/27.
 *
 * 优先队列PriorityQueue类
 * （实现了Queue接口，不允许放入null元素）
 * 在优先队列中，元素被赋予优先级，当访问元素时，拥有最高优先级的元素首先被删除
 *
 java.util.PriorityQueue<E>
 ----------------------------------------------------------------------------------------------------------------------
 +PriorityQueue() 创建一个初始容量为11的默认优先队列
 +PriorityQueue(initialCapacity: int)   创建一个初始容量为指定值得默认优先队列
 +PriorityQueue(c: Collection<? extendsE>)  创建一个具有指定合集的优先队列
 +PriorityQueue(initialCapacity: int, comparator: Comparator<? super E>)   创建一个初始容量为指定值得且具有比较器的优先队列

 方法：
 add()和offer()
 add(E e)和offer(E e)的语义相同，都是向优先队列中插入元素，只是Queue接口规定二者对插入失败时的处理不同，前者在插入失败时抛出异常，后则则会返回false

 element()和peek()
 element()和peek()的语义完全相同，都是获取但不删除队首元素，也就是队列中权值最小的那个元素，二者唯一的区别是当方法失败时前者抛出异常，后者返回null

 remove()和poll()
 remove()和poll()方法的语义也完全相同，都是获取并删除队首元素，区别是当方法失败时前者抛出异常，后者返回null

 remove(Object o)
 remove(Object o)方法用于删除队列中跟o相等的某一个元素（如果有多个相等，只删除一个），该方法不是Queue接口内的方法，而是Collection接口的方法

 Java中PriorityQueue通过二叉小顶堆实现，可以用一棵完全二叉树表示。
 优先队列的作用是能保证每次取出的元素都是队列中权值最小的（Java的优先队列每次取最小元素，C++的优先队列每次取最大元素）。这里牵涉到了大小关系，
 元素大小的评判可以通过元素本身的自然顺序（natural ordering），也可以通过构造时传入的比较器（Comparator，类似于C++的仿函数）
 */

import java.util.*;

public class TestQueue_PriorityQueue {
    public static void main(String[] args) {
        test0();
        //test1();
    }

    public static void test0() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        Random rand = new Random(47);
        for(int i = 0; i < 10; i++)
            priorityQueue.offer(rand.nextInt(i + 10));
        TestQueue.printQ(priorityQueue);  //默认最小的值拥有最高的优先级

        List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<Integer>(ints);
        TestQueue.printQ(priorityQueue);

        priorityQueue = new PriorityQueue<Integer>(ints.size(), Collections.reverseOrder()); //提供反序比较器-最大的值拥有最高的优先级
        priorityQueue.addAll(ints);
        TestQueue.printQ(priorityQueue);

        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPQ = new PriorityQueue<String>(strings);
        TestQueue.printQ(stringPQ);   //空格的优先级最高

        stringPQ = new PriorityQueue<String>(strings.size(), Collections.reverseOrder()); //提供反序比较器
        stringPQ.addAll(strings);
        TestQueue.printQ(stringPQ);

        //通过HashSet消除重复
        Set<Character> charSet = new HashSet<Character>();
        for(char c : fact.toCharArray())
            charSet.add(c); // Autoboxing
        PriorityQueue<Character> characterPQ = new PriorityQueue<Character>(charSet);
        TestQueue.printQ(characterPQ);

    }

    public static void test1() {
        PriorityQueue<String> queue1 = new PriorityQueue<String>();  //默认为小顶堆
        queue1.offer("Oklahoma");
        queue1.offer("Indiana");
        queue1.offer("Georgia");
        queue1.offer("Texas");

        System.out.println("Priority queue using Comparable:");  //字典序
        System.out.println(queue1.peek());
        System.out.println(queue1.peek());
        System.out.println(queue1.remove("Georgia"));
        System.out.println(queue1.peek());


        //while (queue1.size() > 0) {
        //    System.out.print(queue1.remove() + " ");
        //}

        //PriorityQueue<String> queue2 = new PriorityQueue<String>(4, Collections.reverseOrder());  //倒序
        //queue2.offer("Oklahoma");
        //queue2.offer("Indiana");
        //queue2.offer("Georgia");
        //queue2.offer("Texas");
        //
        //System.out.println("\nPriority queue using Comparator:");
        //while (queue2.size() > 0) {
        //    System.out.print(queue2.remove() + " ");
        //}


        //大顶堆
        PriorityQueue<Integer> queue3 = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        queue3.offer(1);
        queue3.offer(2);
        queue3.offer(4);
        queue3.offer(5);
        System.out.println(queue3.peek());
        System.out.println(queue3.peek());
        System.out.println(queue3.remove(5));
        System.out.println(queue3.peek());
        System.out.println(queue3.poll());
        System.out.println(queue3.peek());

        int num = 23;
        char a = Integer.toString(num).toCharArray()[0];
    }
}
