package Collection;

import java.util.LinkedList;

/**
 * Created by Defias on 2020/07.
 * Description:
 */
public class TestQueue_Deque {
    public static void main(String[] args) {
        Deque<Integer> di = new Deque<Integer>();
        fillTest(di);
        System.out.println(di);
        while(di.size() != 0)
            System.out.print(di.removeFirst() + " ");
        System.out.println();
        fillTest(di);
        while(di.size() != 0)
            System.out.print(di.removeLast() + " ");
    }


    static void fillTest(Deque<Integer> deque) {
        for(int i = 20; i < 27; i++)
            deque.addFirst(i);
        for(int i = 50; i < 55; i++)
            deque.addLast(i);
    }

}

class Deque<T> {
    private LinkedList<T> deque = new LinkedList<T>();
    public void addFirst(T e) { deque.addFirst(e); }
    public void addLast(T e) { deque.addLast(e); }
    public T getFirst() { return deque.getFirst(); }
    public T getLast() { return deque.getLast(); }
    public T removeFirst() { return deque.removeFirst(); }
    public T removeLast() { return deque.removeLast(); }
    public int size() { return deque.size(); }
    public String toString() { return deque.toString(); }
    // And other methods as necessary...
}