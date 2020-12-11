package Collection;

/**
 * Created by Defias on 2016/3/14.

 队列Queue接口（Collection接口的子接口  一端进另一端出）
 队列是一种先进先出的数据结构，元素被追加到队末尾，然后从队列头删除

 实现：由于删除是在线性表的起始位置进行的，所以用链表来实现队列比用数组线性表实现效率更高


 双端队列Deque接口（Queue接口的子接口  两端都可进出 可作堆栈）

 此接口既支持有容量限制的双端队列，也支持没有固定大小限制的双端队列，此接口定义在双端队列两端访问元素的方法。提供插入、移除和检查元素的方
 法。每种方法都存在两种形式：一种形式在操作失败时抛出异常，另一种形式返回一个特殊值（null 或 false，具体取决于操作）。插入操作的后一种
 形式是专为使用有容量限制的 Deque 实现设计的；在大多数实现中，插入操作不能失败



 Queue的实现：
    LinkedList类：实现了Deque接口，因此可以把LinkedList当成Queue或Deque来用
    PriorityQueue类：与LinkedList类的区别仅在于排序行为而非性能

 java.util.Queue<E>
 ------------------------------------------------------
 +add(E e)  插入一个元素到队列中(尾)
 +offer(element: E): boolean   插入一个元素到队列中(尾)
 +poll(): E  获取并移除队列的头元素，如果队列为空则返回null
 +remove(): E   获取并移除队列的头元素，如果队列为空则抛出异常
 +peek(): E  获取但不移除队列的头元素，如果队列为空则返回null
 +element(): E  获取但不移除队列的头元素，如果队列为空则抛出异常


 java.util.Deque<E> extends Queue<E>
 ------------------------------------------------------
 addFirst(E e);
 addLast(E e);
 offerFirst(E e);
 offerLast(E e);
 removeFirst();
 removeLast();
 pollFirst();
 pollLast();
 getFirst();
 getLast();
 peekFirst();
 peekLast();
 removeFirstOccurrence(Object o);
 removeLastOccurrence(Object o);
 add(E e);
 offer(E e);
 remove();
 poll();
 element();
 peek();    堆栈方法  peekFirst()
 push(E e);  堆栈方法 addFirst(e)
 pop();     堆栈方法  removeFirst()
 remove(Object o);
 contains(Object o);
 size();
 iterator();
 descendingIterator();

 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TestQueue {
    public static void main(String[] args) {
        test0();
        //test1();
    }

    public static void test0() {
        Queue<Integer> queue = new LinkedList<Integer>();
        Random rand = new Random(47);
        for(int i = 0; i < 10; i++)
            queue.offer(rand.nextInt(i + 10));
        printQ(queue);
        printQ(queue);

        Queue<Character> qc = new LinkedList<Character>();
        for(char c : "Brontosaurus".toCharArray())
            qc.offer(c);
        printQ(qc);
    }

    public static void test1() {
        Queue<String> queue = new LinkedList<>();

        //添加元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");



        System.out.println(queue.size()); //长度
        queue.add("www");
        System.out.println(queue.peek());

        for(String q : queue){
            System.out.println(q);
        }

        System.out.println("===");
        System.out.println("poll="+queue.poll()); //返回第一个元素，并在队列中删除
        for(String q : queue){
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("element="+queue.element()); //返回第一个元素，未删除
        for(String q : queue){
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("peek="+queue.peek()); //返回第一个元素，未删除
        for(String q : queue){
            System.out.println(q);
        }
    }


    public static void printQ(Queue queue) {
        while(queue.peek() != null)
            System.out.print(queue.remove() + " ");
        System.out.println();
    }

}
