package Collection;

import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: Iteratorr(迭代器) - 只能单向移动
 */

public class TestIterator {
    public static void main(String[] args) {
        //testCollectionsIter();
        testFailFast();

    }

    public static void testCollectionsIter() {
        ArrayList<String> pets = new ArrayList<>(Arrays.asList("123", "abc", "def"));
        LinkedList<String> petsLL = new LinkedList<String>(pets);
        HashSet<String> petsHS = new HashSet<String>(pets);
        TreeSet<String> petsTS = new TreeSet<String>(pets);
        display(pets.iterator());
        display(petsLL.iterator());
        display(petsHS.iterator());
        display(petsTS.iterator());
    }


    //不必关心容器的确切类型
    public static void display(Iterator<String> it) {
        while(it.hasNext()) {
            String p = it.next();
            System.out.print(p+" ");
        }
        System.out.println();
    }

    //Java容器的保护机制：快速报错机制 防止多个进程同时修改同一个容器
    //如果在迭代遍历某个容器过程中，另一个进程介入其中并且修改/插入/删除容器内的对象，就会出问题
    //用单进程模拟快速报错机制
    public static void testFailFast() {
        Collection<String> c = new ArrayList<String>();
        Iterator<String> it = c.iterator();
        c.add("An object"); //在获取容器的迭代器之后又又东西放入容器，因此会产生异常
        try {
            String s = it.next();
        } catch(ConcurrentModificationException e) {
            System.out.println(e);
        }
    }
}
