package Collection;
import static Basic.Print.*;
/**
 * Created by Jeff on 2016/3/5.
 *
 * 集合Set接口（Collection接口的子接口）
 * 没有引入新的方法或常量，只是规定set的实例不包含重复的元素
 *
 * set接口的三个具体类：
 * 散列类HashSet  --采用散列实现    无序    元素必须定义hashCode方法
 * 链式散列集LinkedHashSet  --采用Linkedlist实现    有序 - 按元素添加顺序保存    元素必须定义hashCode方法
 * 树形集TreeSet   --采用红黑树实现    有序 - 按元素比较结果的升序保存   元素必须实现Comparable方法
 *
 * HashSet（HashSet的初始容量为16，默认负载因子(元素个数size/初始容量capacity)为0.75，即：除非哈希表的3/4已被填满，否则不会自动成倍的增加哈希表的容量））
 * 用来存储互不相同的任何元素，添加到散列表中的对象必须以一种正确分散散列码的方式来实现hashcode方法
 *
 *
 java.util.HashSet<E>类
 --------------------------------------------------------
 +HashSet()
 +HashSet(c: Collection<? extends E>)
 +HashSet(initialCapacity: int)
 +HashSet(initialCapacity: int, loadFactor: float)
 *
 * HashSet是由HashMap实现的，不保证元素的顺序，而且HashSet允许使用null元素
 *
 * HashSet是非同步的。如果多个线程同时访问一个哈希set，而其中至少一个线程修改了该set，那么它必须保持外部同步
 * 这通常是通过对自然封装该set的对象执行同步操作来完成的。如果不存在这样的对象，则应该使用Collections.synchronizedSet方法来包装set
 * 最好在创建时完成这一操作，以防止对该set进行意外的不同步访问： Set s = Collections.synchronizedSet(new HashSet(...));
 *
 * HashSet通过iterator()返回的迭代器是fail-fast的
 */

import java.util.*;

public class TestSet_HashSet {
	public static void main(String[] args) {

	    //test0();
        testOperation();
	    //test1();
        //test2();
        //test3();
	}

	public static void test0() {
        // 新建HashSet
        HashSet set = new HashSet();
        // 将元素添加到Set中
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("a");
        // 打印HashSet的实际大小
        System.out.printf("size : %d\n", set.size());

        // 判断HashSet是否包含某个值
        System.out.printf("HashSet contains a :%s\n", set.contains("a"));
        System.out.printf("HashSet contains g :%s\n", set.contains("g"));

        // 删除HashSet中的指定元素
        set.remove("e");

        // 将Set转换为数组
        String[] arr = (String[])set.toArray(new String[0]);
        for (String str:arr)
            System.out.printf("for each : %s\n", str);

        // 新建一个包含b、c、f的HashSet
        HashSet otherset = new HashSet();
        otherset.add("b");
        otherset.add("c");
        otherset.add("f");

        // 克隆一个set
        HashSet removeset = (HashSet)set.clone();

        // 删除set中属于另一个set的元素
        removeset.removeAll(otherset);
        System.out.printf("removeset : %s\n", removeset);


        HashSet retainset = (HashSet)set.clone();

        //保留set中属于另一个set的元素
        retainset.retainAll(otherset);
        System.out.printf("retainset : %s\n", retainset);


        // 迭代器遍历HashSet
        for(Iterator iterator = set.iterator();
            iterator.hasNext(); )
            System.out.printf("iterator : %s\n", iterator.next());

        // 清空HashSet
        set.clear();

        // 输出HashSet是否为空
        System.out.printf("%s\n", set.isEmpty()?"set is empty":"set is not empty");


        //随机数据set
        Random rand = new Random(100);
        Set<Integer> intset = new HashSet<Integer>();
        for(int i = 0; i < 10000; i++)
            intset.add(rand.nextInt(80));
        System.out.println(intset);
    }

    public static void testOperation() {
        Set<String> set1 = new HashSet<String>();
        Collections.addAll(set1, "A B C D E F G H I J K L".split(" "));
        set1.add("M");
        print("H: " + set1.contains("H"));
        print("N: " + set1.contains("N"));

        Set<String> set2 = new HashSet<String>();
        Collections.addAll(set2, "H I J K L".split(" "));
        print("set2 in set1: " + set1.containsAll(set2));
        set1.remove("H");
        print("set1: " + set1);
        print("set2 in set1: " + set1.containsAll(set2));
        set1.removeAll(set2);
        print("set2 removed from set1: " + set1);
        Collections.addAll(set1, "X Y Z".split(" "));
        print("'X Y Z' added to set1: " + set1);
    }

	public static void test1() {
		Set<String> set = new HashSet<>();
		String s1 = "hello";
		String s2 = s1;  //同一个对象
		String s3 = "world";

		set.add(s1);
		set.add(s2);
		set.add(s3);

		System.out.println(set.size());  //2 not 3 ？ 因为存放的是对象的引用
		printC(set);
        System.out.println();

	}

	public static void test2() {
		Set<String> set = new HashSet<>();
		String s1 = new String("nihao");
		String s2 = new String("nihao"); //s1和s2引用的是不同的内存地址不同的string对象（值相等）

		set.add(s1);
		set.add(s2);

		System.out.println(set.size());  //1 not 2 ？ 由于s2.equals(s1)比较结果为true，因此Set认为它们是相同的对象
		printC(set);
        System.out.println();

	}

	public static void test3() {
		Set<Customer> set = new HashSet<>();
		Customer o1 = new Customer("Tom",15);
		Customer o2 = new Customer("Tom",15);

		set.add(o1);
		set.add(o2);

		System.out.println(set.size());  //2 not 1 ？虽然o2.equals(o1)比较结果为true 但是o1和o2的哈希码不一样
		printC(set);
        System.out.println();
	}

	//打印集合
	public static void printC(Collection<? extends Object> c) {
		Iterator<? extends Object> it = c.iterator();
		while(it.hasNext()) {
			Object ele = it.next();
			System.out.println(ele);
		}
	}

}

class Customer implements Comparable {
    private String name;
    private int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int compareTo(Object o) { //与equals方法按照相同的规则（equals结果为true时compareTo的结果为0）
        Customer other=(Customer)o;

        //先按照name属性排序
        if(this.name.compareTo(other.getName())>0)return 1;
        if(this.name.compareTo(other.getName())<0)return -1;

        //再按照age属性排序
        if(this.age>other.getAge())return 1;
        if(this.age<other.getAge())return -1;
        return 0;
    }

    public boolean equals(Object o) {  //如果覆盖了equals方法，还应该覆盖hashCode方法，保证相等的对象hashcode也一样
        if(this==o) {
            return true;
        }

        if(! (o instanceof Customer)) {
            return false;
        }

        Customer other = (Customer)o;
        if(this.name.equals(other.getName()) && this.age==other.getAge()) {
            return true;
        } else {
            return false;
        }

    }

    public int hashCode(){
        int result;
        result= (name==null?0:name.hashCode());
        result = 29 * result + age;
        return result;
    }

}
