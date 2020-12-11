package net.jcip.examples.part4_advancedtopics.chapter15_atomicvar;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * LinkedQueue

 Michael-Scott提出的非阻塞链接队列算法中的插入部分

 ConcurrentLinkedQueue中使用的正是该算法

 如果当B线程到达时发现A线程正在修改数据结构，那么在数据结构中应该有足够多的信息，使得B线程能完成A线程的更新操作。如果B线程“帮助”A
 线程完成了更新操作，那么B就可以执行自己的操作，而不用等待A的操作完成。当A恢复后再试图完成其操作时，会发现B线程已经替它完成了


 在ConcurrentLinkedQueue的实际实现中没有使用原子引用来表示每个Node ，而是使用普通的volatile类型引用，并通过基于反射的
 AtomicReferenceFieldUpdater来进行更新


 */
@ThreadSafe
public class LinkedQueue <E> {

    private static class Node <E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, LinkedQueue.Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }

    private final LinkedQueue.Node<E> dummy =
            new LinkedQueue.Node<E>(null, null);
    private final AtomicReference<Node<E>> head =
            new AtomicReference<Node<E>>(dummy);
    private final AtomicReference<Node<E>> tail =
            new AtomicReference<Node<E>>(dummy);

    public boolean put(E item) {
        LinkedQueue.Node<E> newNode = new LinkedQueue.Node<E>(item, null);
        while (true) {
            LinkedQueue.Node<E> curTail = tail.get();
            LinkedQueue.Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {
                    // 队列处于中间状态，推进尾节点
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // 处于稳定状态，尝试插入新节点
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // 插入操作成功，尝试推进尾节点
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                }
            }
        }
    }
}
