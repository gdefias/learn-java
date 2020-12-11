package Collection;
import Generics.element.Egg;
import Generics.element.Userr;
/**
 * Created by Defias on 2017/2/27.
 *
 * List接口
 * 继承自Collection接口， 一个用于顺序存储元素的合集
 *
 * List接口的两个具体类：
 *
 * ArrayList： 使用数组实现的List。作为一个常规用途的对象容器使用，用于替换原先的Vector。允许快速访问元素，但在从列表中
 * 部插入和删除元素时，速度却稍慢。不要用它删除和插入元素；与LinkedList相比，它的效率要低许多
 *
 * LinkedList： 使用链表实现的List。提供优化的顺序访问性能，同时可以高效率地在列表中部进行插入和删除操作。但在进行随机访问时，速度却相当慢，
 * 此时应换用ArrayList。也提供了addFirst()， addLast()， getFirst()，getLast()， removeFirst()以及removeLast()（未在任何接口或基
 * 础类中定义），以便将其作为一个队列以及一个双向队列使用
 *
 *
 java.util.List<E>接口
 -----------------------------------------------------------------------------------------------------------
 +add(index: int, element: Object): boolean  在指定索引位置处增加一个新元素
 +addAll(index: int, c: Collection<? extends E>): boolean  在指定索引位置处添加c中的所有元素
 +get(index: int): E  返回该线性表指定索引位置处的元素
 +indexOf(element: Object): int   返回第一个匹配元素的索引
 +lastIndexOf(element: Object): int   返回最后一个匹配的元素的索引
 +listIterator(): ListIterator<E>  返回针对该线性表元素的迭代器
 +listIterator(startIndex: int): ListIterator<E>   返回针对从startIndex开始的元素的迭代器
 +remove(index: int): E   移除指定索引位置处的元素
 +set(index: int, element: Object): Object   设置指定索引处的元素
 +subList(fromIndex: int, toIndex: int): List<E>  返回从fromIndex到toIndex-1的子线性表

 List接口的两个实现类：
 java.util.ArrayList<E>
 -------------------------------------------------------------------------
 +ArrayList()   使用默认的初始容量创建一个空的线性表
 +ArrayList(c: Collection<? extends E>)   从已经存在的合集中创建一个线性表
 +ArrayList(initialCapacity: int)   创建一个指定初始容量的空的数组线性表
 +trimToSize(): void  将该ArrayList实例的容量裁剪到该线性表的当前大小


 java.util.LinkedList<E>
 ------------------------------------------------------------------------
 +LinkedList()   创建一个默认的空的线性表
 +LinkedList(c: Collection<? extends E>)   从已经存在的合集中创建一个线性表
 +addFirst(element: E): void  添加元素到该线性表的头部
 +addLast(element: E): void  添加元素到该线性表的尾部
 +getFirst(): E  返回该线性表的第一个元素
 +getLast(): E   返回该线性表的最后一个元素
 +removeFirst(): E   从该线性表中返回并删除第一个元素
 +removeLast(): E  从该线性表中返回并删除最后一个元素

 */

import java.util.*;
import java.util.stream.*;

public class TestList {
    public static void main(String[] args) {
        //test0();
        //test1();
        test2();
    }

    //List基本操作
    public static void test0() {
        ArrayList<Egg> myList = new ArrayList<Egg>();

        //加入元素
        Egg s = new Egg();
        myList.add(s);

        Egg b = new Egg();
        myList.add(b);

        Egg c = new Egg();
        myList.add(0,c);

        //查询大小
        int theSize = myList.size();
        System.out.println(theSize);

        //查询特定元素
        Boolean isIn = myList.contains(s);
        System.out.println(isIn);

        //查询特定元素的位置
        int idx = myList.indexOf(c);
        System.out.println(idx);
        System.out.println("-------------1");

        //判断集合是否为空
        Boolean empty = myList.isEmpty();
        System.out.println(empty);

        //删除某个元素
        myList.remove(s);
        isIn = myList.contains(s);
        System.out.println(isIn);

        theSize = myList.size();
        System.out.println(theSize);

        //查询元素
        System.out.println(myList.get(0));
        System.out.println(myList.get(1));

        //删除某个位置上的元素
        myList.remove(0);
        theSize = myList.size();
        System.out.println(theSize);
        System.out.println(myList.get(0));

        //修改元素
        Egg d = new Egg();
        myList.set(0,d);
        theSize = myList.size();
        System.out.println(theSize);
        System.out.println(myList.get(0));
        System.out.println("-------------2");

        ArrayList<Integer> MyintList = new ArrayList<Integer>();
        MyintList.add(0);
        MyintList.add(23);
        MyintList.add(1);
        System.out.println(MyintList.get(0));
        System.out.println(MyintList.get(1));
        System.out.println(MyintList.get(2));
        //MyintList.remove(1);  //根据位置删除元素
        MyintList.remove(new Integer(1));  //删除指定的元素对象
        System.out.println(MyintList.get(0));
        System.out.println(MyintList.get(1));

        System.out.println("-------------3");

        //存放List的List
        List<List<Integer>> testList = new ArrayList<>();
        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();

        path1.add(1);
        path1.add(2);
        path1.add(3);
        testList.add(path1);

        path2.add(4);
        path2.add(5);
        path2.add(6);
        testList.add(path2);

        List<Integer> path3 = new ArrayList<>(path2);
        testList.add(path3);

        path2.set(0, 10);
        path2.remove(2);

        List<List<Integer>> result = new ArrayList<>();
        for(List<Integer> path: testList) {
            int sum = 0;
            for(int i: path) {
                sum = sum + i;
            }
            if(sum != 0) {
                result.add(path);
            }
        }
        System.out.println(result);
    }

    public static void test1() {
        List<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1); // 1 is autoboxed to new Integer(1)  自动装箱
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(4);
        arrayList.add(0, 10);
        arrayList.add(3, 30);
        System.out.println("A list of integers in the array list: " + arrayList);


        //用作linkedList
        LinkedList<Object> linkedList = new LinkedList<Object>(arrayList);
        linkedList.add(1, "red");
        linkedList.removeLast();
        linkedList.addFirst("green");
        System.out.println("linkedList: " + linkedList);

        //子集
        List<Integer> arrayList2 = arrayList.subList(0,4);
        System.out.println("A list of integers in the arraylist2: " + arrayList2);

        //交集
        arrayList.retainAll(arrayList2);
        System.out.println("A list of integers in the arraylist: " + arrayList);

        //使用流求List中的数值的和、最大、最小值
        Integer sum = arrayList.stream().reduce(Integer::sum).orElse(0);
        System.out.println("sum of array list:" + sum);

        Integer max = arrayList.stream().reduce(Integer::max).orElse(0);
        System.out.println("max of array list:" + max);

        Integer min = arrayList.stream().reduce(Integer::min).orElse(0);
        System.out.println("min of array list:" + min);


        //使用流求List中对象属性的和、最大、最小、平均值
        List<Userr> userlist = new ArrayList<Userr>();
        userlist.add(new Userr("xiaoming", 168));
        userlist.add(new Userr("lidayong", 170));
        userlist.add(new Userr("yefei", 159));
        userlist.add(new Userr("xiaohong", 165));
        System.out.println("A list of userlist: " + userlist);

        System.out.println(userlist.stream().mapToDouble(Userr::getHeight).sum());     //和
        System.out.println(userlist.stream().mapToDouble(Userr::getHeight).max());     //最大
        System.out.println(userlist.stream().mapToDouble(Userr::getHeight).min());     //最小
        System.out.println(userlist.stream().mapToDouble(Userr::getHeight).average());   //平均值


        //使用流 Object容器转String容器
        //ArrayList<Object> to ArrayList<String>
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(1);
        list.add("Java");
        list.add(3.14);
        System.out.println(list);

        List<String> strlist = list.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());
        System.out.println(strlist);
    }


    public static void test2(){
        List<Integer> list = new LinkedList<Integer>();  //创建一个链接列表
        list.add(new Integer(3));
        list.add(new Integer(2));
        list.add(new Integer(5));
        list.add(new Integer(9));
        list.add(new Integer(21));
        list.add(new Integer(13));
        list.add(new Integer(15));
        System.out.println(list);


        //顺序迭代输出
        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }
        System.out.println();

        //倒序迭代输出
        listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }
        System.out.println();

        //排序
        Collections.sort(list);
        System.out.println(list);

        //插入
        insert(list,6);
        System.out.println(list);
    }



    //向升序List中按插入数据
    public static void insert(List<Integer> list, int data) {
        ListIterator<Integer> it = list.listIterator();
        while(it.hasNext()){
            Integer in = it.next();
            if(data <= in.intValue()){
                it.previous(); //返回到列表中的上一个元素
                it.add(new Integer(data));  //插入元素
                break;
            }
        }
    }

}
