package Collection;

import Collection.generator.RandomGenerator;
import Generics.element_generics.Generator;
import Generics.element.StringAddress;
import Collection.choose.CountingIntegerList;
import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: Collections类操作及填充容器
 *
 * Collections类
 * 包含操作合集和线性表的静态方法
 *
 java.util.Collections类
 ---------------------------------------------------------------------------------------------------------------------
 +sort(list: List): void  对指定的线性表进行排序
 +sort(list: List, c: Comparator): void   使用比较器对指定的线性表进行排序
 +binarySearch(list: List, key: Object): int  采用二分查找来找到排好序的线性表中的键值
 +binarySearch(list: List, key: Object, c: Comparator): int   使用比较器，采用二分查找来找到排好序的线性表中的键值
 +reverse(list: List): void  对指定的线性表进行逆序排序
 +reverseOrder(): Comparator   返回一个逆序排序的比较器
 +shuffle(list: List): void   随机打乱指定的线性表
 +shuffle(list: List, rmd: Random): void   使用一个随机对象打乱指定的线性表
 +copy(des: List, src: List): void   复制源线性表到目标线性表中
 +nCopies(n: int, o: Object): List  返回一个有n个对象副本组成的线性表
 +fill(list: List, o: Object): void   使用对象填充线性表
 +max(c: Collection): Object   返回合集中的max对象
 +max(c: Collection, c: Comparator): Object    使用比较器返回合集中max对象
 +min(c: Collection): Object  返回合集中min对象
 +min(c: Collection, c: Comparator): Object   使用比较器返回合集中的min对象
 +disjoint(c1: Collection, c2: Collection): boolean    如果c1和c2没有共同的元素则返回真
 +frequency(c: Collection, o: Object): int   返回合集中指定元素的出现次数

 * 可以使用Collections类中的静态方法来创建单元素的集合、线性表和映射表，以及不可变集合、线性表和映射表
 * 静态方法：
 +singleton(o: Object): Set   返回一个包含了指定对象的不可修改的集合
 +singletonList(o: Object): List   返回一个包含了指定对象的不可修改的线性表
 +singletonMap(key: Object, value: Object): Map   返回一个具有键值对的不可修改的映射表
 +unmodifiableCollection(c: Collection): Collection   返回一个合集中的只读视图
 +unmodifiableList(list: List): List   返回一个线性表的只读视图
 +unmodifiableMap(m: Map): Map   返回一个映射表的只读视图
 +unmodifiableSet(s: Set): Set   返回一个集合的只读视图
 +unmodifiableSortedMap(s: SortedMap): SortedMap   返回一个排好序的映射表的只读视图
 +unmodifiableSortedSet(s: SortedSet): SortedSet   返回一个排好序的集合的只读视图
 */

public class TestList2 {

    public static void main(String[] args) {
        //test0();
        //test1();
        //test2();
        //test3();
        test4();
    }

    public static void test0() {
        List<String> list = Arrays.asList(new String[]{"Tom","Jack","Linda","Rose"});
        System.out.println(list);
        System.out.println(Collections.binarySearch(list,"Linda"));

        Collections.sort(list); //把List中的元素自然排序
        System.out.println(list);
        System.out.println(Collections.binarySearch(list,"Linda"));

        Collections.sort(list, Collections.reverseOrder()); //逆序
        System.out.println(list);
        System.out.println(Collections.binarySearch(list,"Linda"));  //??

        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));

        Collections.shuffle(list);  //打乱：重新随机调整List中元素的位置
        System.out.println(list);
        System.out.println(Collections.binarySearch(list,"Tom")); //??

    }

    public static void test1() {
        //Collections.nCopies: 返回由指定对象的n个副本组成的不可变列表
        List<Integer> list1 = Collections.nCopies(5, 8);
        //! list1.add(9);
        System.out.println(list1);

        List<StringAddress> list2 = new ArrayList<StringAddress>(
                Collections.nCopies(4, new StringAddress("Hello")));  //将不可变列表传递个构造器
        list2.add(new StringAddress("hehe"));
        System.out.println(list2);

        //Collections.fill： 使用指定元素替换指定列表中的所有元素
        Collections.fill(list2, new StringAddress("World!"));
        System.out.println(list2);
    }

    //使用生成器Generator
    public static void test2() {
        Set<String> set = new LinkedHashSet<String>(
                new CollectionData<String>(new Government(), 15));
        System.out.println(set);


        Set<String> set1 = new LinkedHashSet<String>();
        // Using the convenience method:
        set1.addAll(CollectionData.list(new Government(), 15));
        System.out.println(set1);
    }

    //数据生成器
    public static void test3() {
        System.out.println(new ArrayList<String>(
                CollectionData.list(new RandomGenerator.String(5), 10)));

        System.out.println(new HashSet<Integer>(
                new CollectionData<Integer>(new RandomGenerator.Integer(), 10)));
    }

    public static void test4() {
        CountingIntegerList ci = new CountingIntegerList(30);

//        System.out.println(ci.size());
//        for(int i=0; i<ci.size(); i++) {
//            System.out.println(ci.get(i));
//        }
        System.out.println(new CountingIntegerList(30));
    }
}


//一个生成器
class Government implements Generator<String> {
    String[] foundation = ("strange women lying in ponds " +
            "distributing swords is no basis for a system of " +
            "government").split(" ");
    private int index;
    public String next() {
        return foundation[index++];
    }
}



//使用Generator在容器中放置所需数量的对象，所产生的容器可以传递给任何Collection的构造器
//适配器设计模式，将Generator适配到Collection的构造器上
class CollectionData<T> extends ArrayList<T> {
    public CollectionData(Generator<T> gen, int quantity) {
        for(int i = 0; i < quantity; i++)
            add(gen.next());
    }

    // A generic convenience method:
    public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
        return new CollectionData<T>(gen, quantity);
    }
}
