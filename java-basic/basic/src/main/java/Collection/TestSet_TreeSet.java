package Collection;

import java.util.*;

/**
 * Created by Defias on 2017/2/28.
 *
 * TreeSet
 * SortedSet是Set的一个子接口，TreeSet实现了SortedSet接口和NavigableSet接口，可以确保集合中元素是有序的
 *
 * TreeSet是一个有序的集合，它的作用是提供有序的Set集合。它继承于AbstractSet抽象类，
 * 实现了NavigableSet<E>, Cloneable, java.io.Serializable接口
 *
 * TreeSet继承于AbstractSet，所以它是一个Set集合，具有Set的属性和方法
 * TreeSet实现了NavigableSet接口，意味着它支持一系列的导航方法。比如查找与指定目标最匹配项
 * TreeSet实现了Cloneable接口，意味着它能被克隆
 * TreeSet实现了java.io.Serializable接口，意味着它支持序列化
 *
 * TreeSet是基于TreeMap实现的。TreeSet中的元素支持2种排序方式：自然排序 或者 根据创建TreeSet时提供的Comparator进行排序。
 * 这取决于使用的构造方法
 *
 * TreeSet为基本操作（add、remove 和 contains）提供受保证的 log(n) 时间开销。另外TreeSet是非同步的。它的iterator方法返回的
 * 迭代器是fail-fast的

 java.util.SortedSet<E>接口
 --------------------------------------------------------
 +first(): E
 +last(): E
 +headSet(toElement: E): SortedSet<E>
 +tailSet(fromElement: E): SortedSet<E>


 java.util.NavigableSet<E>接口
 --------------------------------------------------------
 +pollFirst(): E
 +pollLast(): E
 +lower(e: E): E
 +higher(e: E):E
 +floor(e: E): E
 +ceiling(e: E): E


 java.util.TreeSet<E>
 --------------------------------------------------------
 +TreeSet()
 +TreeSet(c: Collection<? extends E>)
 +TreeSet(comparator: Comparator<?
 super E>)
 +TreeSet(s: SortedSet<E>)
 */


public class TestSet_TreeSet {
    public static void main(String[] args) {

        //testTreeSet0();
        //testTreeSetSort();
//        testTreeSetAPIs();
//        testTreeSet1();
//        testTreeSet2();
//        testTreeSet3();
        testSortSet();
    }

    public static void testTreeSet0() {
        Random rand = new Random(47);
        SortedSet<Integer> intset = new TreeSet<Integer>();
        for(int i = 0; i < 10000; i++)
            intset.add(rand.nextInt(30));
        System.out.println(intset);
    }

    public static void testTreeSetSort() {
        Set<String> words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER); //传入比较器按字母序排序（默认是按字典序排序）

        ArrayList<String> al = new ArrayList<>(Arrays.asList("31", "13", "0", "62", "abs", "Ds", "cdf"));
        words.addAll(al);
        System.out.println(words);
    }

    // 测试TreeSet的api
    public static void testTreeSetAPIs() {
        String val;

        // 新建TreeSet
        TreeSet tSet = new TreeSet();
        // 将元素添加到TreeSet中
        tSet.add("aaa");
        // Set中不允许重复元素，所以只会保存一个“aaa”
        tSet.add("aaa");
        tSet.add("bbb");
        tSet.add("eee");
        tSet.add("ddd");
        tSet.add("ccc");
        System.out.println("TreeSet:"+tSet);


        // 打印TreeSet的实际大小
        System.out.printf("size : %d\n", tSet.size());

        // 导航方法
        // floor(小于/等于)xxx的最大值
        System.out.printf("floor ccc: %s\n", tSet.floor("ccc"));
        // lower(小于)xxx的最大值
        System.out.printf("lower ccc: %s\n", tSet.lower("ccc"));
        // ceiling(大于/等于)xxx的最小值
        System.out.printf("ceiling bbb: %s\n", tSet.ceiling("bbb"));
        System.out.printf("ceiling eee: %s\n", tSet.ceiling("eee"));
        // higher(大于)xxx的最小值
        System.out.printf("higher bbb: %s\n", tSet.higher("bbb"));
        // subSet()
        System.out.printf("subSet(aaa, true, ccc, true): %s\n", tSet.subSet("aaa", true, "ccc", true));
        System.out.printf("subSet(aaa, true, ccc, false): %s\n", tSet.subSet("aaa", true, "ccc", false));
        System.out.printf("subSet(aaa, false, ccc, true): %s\n", tSet.subSet("aaa", false, "ccc", true));
        System.out.printf("subSet(aaa, false, ccc, false): %s\n", tSet.subSet("aaa", false, "ccc", false));
        // headSet()
        System.out.printf("headSet(ccc, true): %s\n", tSet.headSet("ccc", true));
        System.out.printf("headSet(ccc, false): %s\n", tSet.headSet("ccc", false));
        // tailSet()
        System.out.printf("tailSet(ccc, true): %s\n", tSet.tailSet("ccc", true));
        System.out.printf("tailSet(ccc, false): %s\n", tSet.tailSet("ccc", false));


        // 删除“ccc”
        tSet.remove("ccc");
        // 将Set转换为数组
        String[] arr = (String[])tSet.toArray(new String[0]);
        for (String str:arr)
            System.out.printf("for each : %s\n", str);

        // 打印TreeSet
        System.out.printf("TreeSet:%s\n", tSet);

        // 遍历TreeSet
        for(Iterator iter = tSet.iterator(); iter.hasNext(); ) {
            System.out.printf("iter : %s\n", iter.next());
        }

        // 删除并返回第一个元素
        val = (String)tSet.pollFirst();
        System.out.printf("pollFirst=%s, set=%s\n", val, tSet);

        // 删除并返回最后一个元素
        val = (String)tSet.pollLast();
        System.out.printf("pollLast=%s, set=%s\n", val, tSet);

        // 清空HashSet
        tSet.clear();

        // 输出HashSet是否为空
        System.out.printf("%s\n", tSet.isEmpty()?"set is empty":"set is not empty");
    }

    public static void testTreeSet1() {
        //自然排序（使用了Comparable接口对元素进行排序，只能向TreeSet集合中加入同类型的对象，并且这些对象的类必须实现了Comparable接口，否则抛ClassCastException异常）
        Set<Customer> set = new TreeSet<Customer>();
        set.add(new Customer("Tom",15));
        set.add(new Customer("Tom",20));
        set.add(new Customer("Tom",15));
        set.add(new Customer("Mike",15));

        Iterator<Customer> it = set.iterator();
        while(it.hasNext()){
            Customer customer = it.next();
            System.out.println(customer.getName()+" "+customer.getAge());
        }

    }

    public static void testTreeSet2() {
        Set<Customer> set = new TreeSet<Customer>();
        Customer c1 = new Customer("Tom",15);
        Customer c2 = new Customer("Tom",20);
        set.add(c1);
        set.add(c2);
        c1.setAge(30);  //修改了集合中的对象属性，TreeSet不会对集合进行重新排序

        //for循环集合的方式
        for(Customer customer: set) {
            System.out.println(customer.getName()+" "+customer.getAge());
        }

    }

    public static void testTreeSet3() {
        //自定义排序（使用自定义的比较器）
        Set<Customer> set = new TreeSet<Customer>(new CustomerComparator());
        Customer customer1 = new Customer("Tom",15);
        Customer customer3 = new Customer("Jack",16);
        Customer customer2 = new Customer("Mike",26);
        set.add(customer1);
        set.add(customer2);
        set.add(customer3);

        Iterator<Customer> it = set.iterator();
        while(it.hasNext()){
            Customer customer=it.next();
            System.out.println(customer.getName()+" "+customer.getAge());
        }
    }

    public static void testSortSet() {
        SortedSet<String> sortedSet = new TreeSet<String>();
        Collections.addAll(sortedSet,
                "one two three four five six seven eight"
                        .split(" "));
        System.out.println(sortedSet);
        String low = sortedSet.first();
        String high = sortedSet.last();
        System.out.println(low);
        System.out.println(high);

        //sortedSet：按对象的比较函数对元素排序，而不是指元素的插入顺序
        Iterator<String> it = sortedSet.iterator();
        for(int i = 0; i <= 6; i++) {
            //System.out.println(it.next());
            if(i == 3) low = it.next();
            if(i == 6) high = it.next();
            else it.next();
        }
        System.out.println(low);
        System.out.println(high);
        System.out.println(sortedSet.subSet(low, high));
        System.out.println(sortedSet.headSet(high));
        System.out.println(sortedSet.tailSet(low));
    }
}



//比较器
class CustomerComparator implements Comparator<Customer> {
    public int compare(Customer c1, Customer c2) {
        //按照Customer对象的name属性降序排序
        if (c1.getName().compareTo(c2.getName()) > 0)
            return -1; //x < y
        if (c1.getName().compareTo(c2.getName()) < 0)
            return 1;  //x > y
        return 0; //x == y
    }
}