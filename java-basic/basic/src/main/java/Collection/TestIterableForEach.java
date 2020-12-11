package Collection;

import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description:  Foreach与迭代器接口Iterable

 Iterable接口被Foreach用于在序列中移动，任何实现了Iterable的类都可以用于Foreach语法

 java.lang.Iterable<E>接口
 ---------------------------------------------------------------------------------
 Iterator<T>	iterator()
 返回类型为 T元素的迭代器（迭代器对象实现了Iterator接口）

 default void	forEach(Consumer<? super T> action)
 对Iterable的每个元素执行给定的操作，直到所有元素都被处理或动作引发异常。

 default Spliterator<T>	spliterator()
 在Iterable描述的元素上创建一个Iterable


 foreach循环中进行集合元素的remove/add操作的陷阱
 如果要进行remove操作，可以调用迭代器的 remove 方法而不是集合类的 remove 方法。因为如果列表在任何时间从结构上修改创建迭代器之后，以任
 何方式除非通过迭代器自身remove/add方法，迭代器都将抛出一个ConcurrentModificationException,这就是单线程状态下产生的 fail-fast 机制

 fail-fast机制：多个线程对fail-fast集合进行修改的时，可能会抛出ConcurrentModificationException，单线程下也会出现这种情况
 java.util包下面的所有的集合类都是fail-fast的
 java.util.concurrent包下面的所有的类都是fail-safe的

 https://www.jianshu.com/p/724f763fd242

 */

public class TestIterableForEach {

    public static void main(String[] args) {
        //test0();
        //test1();
        //test2();
        test3();
    }

    public static void test0() {
        Collection<String> cs = new LinkedList<String>();
        Collections.addAll(cs, "Take the long way home".split(" "));
        for(String s : cs)
            System.out.print("'" + s + "' ");
    }

    //显示所有操作系统的环境变量
    public static void test1() {
        for(Map.Entry entry: System.getenv().entrySet()) {
            System.out.println(entry.getKey() + ": " +
                    entry.getValue());
        }
    }

    public static void test2() {
        for(String s : new IterableClass())
            System.out.print(s + " ");
    }


    public static void test3() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        //不要在 foreach 循环里进行元素的 remove/add 操作
        //for (String item: list) {
        //    if("2".equals(item)) {
        //        list.remove(item);
        //    }
        //}
        //System.out.println(list);

        //正确的姿势
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if("2".equals(item)) {
                iterator.remove();
            }
        }
        System.out.println(list);

        //或Collection#removeIf()
        List<Integer> list2 = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            list2.add(i);
        }
        list2.removeIf(filter -> filter % 2 == 0);  /* 删除list2中的所有偶数 */
        System.out.println(list2); /* [1, 3, 5, 7, 9] */

    }





}

//实现Iterable接口
class IterableClass implements Iterable<String> {
    protected String[] words = ("And that is how " +
            "we know the Earth to be banana-shaped.").split(" ");
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;
            public boolean hasNext() {
                return index < words.length;
            }
            public String next() { return words[index++]; }

            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }
}
