package Collection;

/**
 * Created by Defias on 2017/8/27.
 *
 * LinkedList
 *
 * LinkedList<E> extends AbstractSequentialList<E>
 *               implements List<E>, Deque<E>, Cloneable, java.io.Serializable
 *
 * LinkedList是一个继承于AbstractSequentialList的双向链表。它也可以被当作栈、队列或双端队列进行操作
 * LinkedList实现List接口，能对它进行队列操作
 * LinkedList实现Deque接口，即能将LinkedList当作双端队列使用
 * LinkedList实现了Cloneable接口，即覆盖了函数clone()，能克隆
 * LinkedList实现java.io.Serializable接口，这意味着LinkedList支持序列化，能通过序列化去传输
 * LinkedList是非同步的
 */
import java.util.LinkedList;

public class TestLinkedList {
    public static void main(String[] args) {
        // 测试LinkedList的API
        testLinkedListAPIs() ;

        // 将LinkedList当作 LIFO(后进先出)的堆栈
        useLinkedListAsLIFO();

        // 将LinkedList当作 FIFO(先进先出)的队列
        useLinkedListAsFIFO();
    }

    /*
     * 测试LinkedList中部分API
     */
    private static void testLinkedListAPIs() {
        String val = null;
        //LinkedList llist;
        //llist.offer("10");
        // 新建一个LinkedList
        LinkedList llist = new LinkedList();
        //---- 添加操作 ----
        // 依次添加1,2,3
        llist.add("1");
        llist.add("2");
        llist.add("3");

        // 将“4”添加到第一个位置
        llist.add(1, "4");


        System.out.println("\nTest \"addFirst(), removeFirst(), getFirst()\"");
        // (01) 将“10”添加到第一个位置。  失败的话，抛出异常！
        llist.addFirst("10");
        System.out.println("llist:"+llist);
        // (02) 将第一个元素删除。        失败的话，抛出异常！
        System.out.println("llist.removeFirst():"+llist.removeFirst());
        System.out.println("llist:"+llist);
        // (03) 获取第一个元素。          失败的话，抛出异常！
        System.out.println("llist.getFirst():"+llist.getFirst());


        System.out.println("\nTest \"offerFirst(), pollFirst(), peekFirst()\"");
        // (01) 将“10”添加到第一个位置。  返回true。
        llist.offerFirst("10");
        System.out.println("llist:"+llist);
        // (02) 将第一个元素删除。        失败的话，返回null。
        System.out.println("llist.pollFirst():"+llist.pollFirst());
        System.out.println("llist:"+llist);
        // (03) 获取第一个元素。          失败的话，返回null。
        System.out.println("llist.peekFirst():"+llist.peekFirst());


        System.out.println("\nTest \"addLast(), removeLast(), getLast()\"");
        // (01) 将“20”添加到最后一个位置。  失败的话，抛出异常！
        llist.addLast("20");
        System.out.println("llist:"+llist);
        // (02) 将最后一个元素删除。        失败的话，抛出异常！
        System.out.println("llist.removeLast():"+llist.removeLast());
        System.out.println("llist:"+llist);
        // (03) 获取最后一个元素。          失败的话，抛出异常！
        System.out.println("llist.getLast():"+llist.getLast());


        System.out.println("\nTest \"offerLast(), pollLast(), peekLast()\"");
        // (01) 将“20”添加到第一个位置。  返回true。
        llist.offerLast("20");
        System.out.println("llist:"+llist);
        // (02) 将第一个元素删除。        失败的话，返回null。
        System.out.println("llist.pollLast():"+llist.pollLast());
        System.out.println("llist:"+llist);
        // (03) 获取第一个元素。          失败的话，返回null。
        System.out.println("llist.peekLast():"+llist.peekLast());



        // 将第3个元素设置300。不建议在LinkedList中使用此操作，因为效率低！
        llist.set(2, "300");
        // 获取第3个元素。不建议在LinkedList中使用此操作，因为效率低！
        System.out.println("\nget(3):"+llist.get(2));


        // ---- toArray(T[] a) ----
        // 将LinkedList转行为数组
        String[] arr = (String[])llist.toArray(new String[0]);
        for (String str:arr)
            System.out.println("str:"+str);

        // 输出大小
        System.out.println("size:"+llist.size());
        // 清空LinkedList
        llist.clear();
        // 判断LinkedList是否为空
        System.out.println("isEmpty():"+llist.isEmpty()+"\n");

    }

     //将LinkedList当作 LIFO(后进先出)的栈
    private static void useLinkedListAsLIFO() {
        System.out.println("\nuseLinkedListAsLIFO");
        // 新建一个LinkedList
        LinkedList stack = new LinkedList();

        // 将1,2,3,4添加到堆栈中
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        // 打印“栈”
        System.out.println("stack:"+stack);

        // 删除“栈顶元素”
        System.out.println("stack.pop():"+stack.pop());

        // 取出“栈顶元素”
        System.out.println("stack.peek():"+stack.peek());

        // 打印“栈”
        System.out.println("stack:"+stack);
    }

    /**
     * 将LinkedList当作 FIFO(先进先出)的队列
     */
    private static void useLinkedListAsFIFO() {
        System.out.println("\nuseLinkedListAsFIFO");
        // 新建一个LinkedList
        LinkedList queue = new LinkedList();

        // 将10,20,30,40添加到队列。每次都是插入到末尾
        queue.add("10");
        queue.add("20");
        queue.add("30");
        queue.add("40");
        // 打印“队列”
        System.out.println("queue:"+queue);

        // 删除(队列的第一个元素)
        System.out.println("queue.remove():"+queue.remove());

        // 读取(队列的第一个元素)
        System.out.println("queue.element():"+queue.element());

        // 打印“队列”
        System.out.println("queue:"+queue);
    }
}
