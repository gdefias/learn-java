package Collection;
import Collection.generator.Countries;
/**
 * Created by Jeff on 2016/3/5.
 *
 * Collection接口
 * 处理对象合集的根接口，继承了Iterable接口（每种合集都是可迭代的）
 * 包含了处理合集中元素的方法，并且可以得到一个迭代器对象用于遍历合集中的元素
 *
 java.util.Collection<E>接口
 ------------------------------------------------------------------------------------------------------------
 +add(o: E): boolean   添加一个新的元素到合集中
 +addAll(c: Collection<? extends E>): boolean   将合集c中的所有元素添加到该合集中
 +clear(): void   从该合集删除所有元素
 +contains(o: Object): boolean 如果该合集包含元素o，则返回true
 +containsAll(c: Collection<?>): boolean  如果该合集包含c中所有元素，则返回true
 +equals(o: Object): boolean  如果该合集等同于另外一个合集o，则返回true
 +hashCode(): int  返回该合集的哈希码
 +isEmpty(): boolean   如果该合集没有包含元素，则返回true
 +remove(o: Object): boolean  从该合集中移除元素o
 +removeAll(c: Collection<?>): boolean   从该合集中移除c中的所有元素
 +retainAll(c: Collection<?>): boolean   保留同时位于c和该合集中的元素
 +size(): int  返回该合集中的元素数目
 +toArray(): Object[]   为该合集中的元素返回一个Object数组
 +toArray(T[] a): 返回T[] 返回数组的运行时类型是指定数组的运行时类型

 java.util.Iterator<E>接口
 ---------------------------------------------------------------------------------
 +hasNext(): boolean  如果该迭代器还要遍历更多元素，则返回true
 +next(): E    返回该迭代器中的下一个元素
 +remove(): void   移除使用next方法获取的上一个元素

 */

import java.util.*;

public class TestCollection {
    public static void main(String[] args) {

        //test0();
        test1();
    }

    public static void test0() {
        //Collection
        Collection<String> c = new ArrayList<String>();
        //Collection<? extends Object> c = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            c.add(Integer.toString(i));  //向合集中加入元素

        printC(c);

        //向合集中加入元素 - 更简洁的方式
        Collection<String> c2 = new ArrayList<String>(Arrays.asList("zhangsan", "lisi", "wangwu"));
        printC(c2);

        //向合集中加入元素 - 多次追加
        Collection<Integer> c3 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] moreInts = { 6, 7, 8, 9, 10 };
        c3.addAll(Arrays.asList(moreInts));
        printC(c3);
        System.out.println("---------");

        Collections.addAll(c3, 11, 12, 13, 14, 15);
        Collections.addAll(c3, moreInts);
        printC(c3);
        System.out.println("---------");

        //容器的打印
        System.out.println(fill(new ArrayList<String>()));
        System.out.println(fill(new LinkedList<String>()));
        System.out.println(fill(new HashSet<String>()));
        System.out.println(fill(new TreeSet<String>()));
        System.out.println(fill(new LinkedHashSet<String>()));
        System.out.println(fill(new HashMap<String,String>()));
        System.out.println(fill(new TreeMap<String,String>()));
        System.out.println(fill(new LinkedHashMap<String,String>()));
    }

    public static void test1() {
        Collection<String> c = new ArrayList<String>();
        c.addAll(Countries.names(6));
        c.add("ten");
        c.add("eleven");
        System.out.println(c);

        // Make an array from the List:
        Object[] array = c.toArray();
        System.out.println(array.length);
        // Make a String array from the List:
        String[] str = c.toArray(new String[0]);
        System.out.println(str.length);

        // Find max and min elements; this means
        // different things depending on the way
        // the Comparable interface is implemented:
        System.out.println("Collections.max(c) = " + Collections.max(c));
        System.out.println("Collections.min(c) = " + Collections.min(c));

        // Add a Collection to another Collection
        Collection<String> c2 = new ArrayList<String>();
        c2.addAll(Countries.names(6));
        c.addAll(c2);
        System.out.println(c);
        c.remove(Countries.DATA[0][0]);
        System.out.println(c);
        c.remove(Countries.DATA[1][0]);
        System.out.println(c);

        // Remove all components that are
        // in the argument collection:
        c.removeAll(c2);
        System.out.println(c);
        c.addAll(c2);
        System.out.println(c);

        // Is an element in this Collection?
        String val = Countries.DATA[3][0];
        System.out.println("c.contains(" + val  + ") = " + c.contains(val));

        // Is a Collection in this Collection?
        System.out.println("c.containsAll(c2) = " + c.containsAll(c2));
        Collection<String> c3 =
                ((List<String>)c).subList(3, 5);

        // Keep all the elements that are in both
        // c2 and c3 (an intersection of sets):
        c2.retainAll(c3);
        System.out.println(c2);

        // Throw away all the elements
        // in c2 that also appear in c3:
        c2.removeAll(c3);
        System.out.println("c2.isEmpty() = " +  c2.isEmpty());
        c = new ArrayList<String>();
        c.addAll(Countries.names(6));
        System.out.println(c);
        c.clear(); // Remove all elements
        System.out.println("after c.clear():" + c);
    }

    public static void printC(Collection<? extends Object> c) {
        Iterator<? extends Object> it = c.iterator();  //通过合集获得一个迭代器

        //遍历集合中元素
        while(it.hasNext()) {
            Object ele = it.next();
            System.out.println(ele);
        }
    }

    public static Collection fill(Collection<String> collection) {
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("dog");
        return collection;
    }

    public static Map fill(Map<String,String> map) {
        map.put("rat", "Fuzzy");
        map.put("cat", "Rags");
        map.put("dog", "Bosco");
        map.put("dog", "Spot");
        return map;
    }

}
