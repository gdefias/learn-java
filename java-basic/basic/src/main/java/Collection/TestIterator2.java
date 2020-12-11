package Collection;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Defias on 2017/3/8.
 *
 * Iteratorr(迭代器)和Enumeration(枚举类-已经过时)
 *
 *
 *  Enumeration接口源码:
 *  public interface Enumeration<E> {
 *      boolean hasMoreElements();
 *      E nextElement();
 *  }
 *
 *  Iterator接口源码:
 *  public interface Iterator<E> {
 *      boolean hasNext();
 *      E next();
 *      void remove();
 *  }
 *
 *  Enumeration是JDK 1.0添加的接口。使用到它的函数包括Vector、Hashtable等类，这些类都是JDK 1.0中加入的，Enumeration存在的目的就是为
 *  它们提供遍历接口。Enumeration本身并没有支持同步，而在Vector、Hashtable实现Enumeration时，添加了同步
 *  Enumeration(枚举类)接口： 只能读取集合的数据，而不能对数据进行修改
 *
 *  【Enumeration已经过时了，缺点一大堆，除非老的库返回这种类型，不得已使用，其他情况能不使用就不要再用了】
 *
 *
 *  Iterator是JDK 1.2才添加的接口，它也是为了HashMap、ArrayList等集合提供遍历接口。Iterator是支持fail-fast机制的：当多个线程对同一个
 *  集合的内容进行操作时，就可能会产生fail-fast事件
 *  Iterator(迭代器)接口： 除了能读取集合的数据之外，也能数据进行删除操作
 *
 *
 *  Iterator支持fail-fast机制，而Enumeration不支持
 *  fail-fast机制： 错误检测机制。它只能被用来检测错误
 *  fail-fast事件： 抛ConcurrentModificationException异常
 *
 *  Enumeration比Iterator的遍历速度更快
 *  因为，Hashtable中Iterator是通过Enumeration去实现的，而且Iterator添加了对fail-fast机制的支持；所以执行的操作自然要多一些
 */

//测试分别通过Iterator和Enumeration去遍历Hashtable
public class TestIterator2 {
    public static void main(String[] args) {
        int val;
        Random r = new Random();
        Hashtable table = new Hashtable();
        for (int i=0; i<100000; i++) {
            val = r.nextInt(100); //随机获取一个[0,100)之间的数字
            table.put(String.valueOf(i), val);
        }

        //通过Iterator遍历Hashtable
        iterateHashtable(table);
        System.out.println("----------------");

        //通过Enumeration遍历Hashtable
        enumHashtable(table);
    }

    /*
    * 通过Iterator遍历Hashtable
    */
    private static void iterateHashtable(Hashtable table) {
        long startTime = System.currentTimeMillis();
        Iterator iter = table.entrySet().iterator();
        while(iter.hasNext()) {
            System.out.println("iter:"+iter.next());
            //iter.next();
        }
        long endTime = System.currentTimeMillis();
        countTime(startTime, endTime);
    }

    /*
    * 通过Enumeration遍历Hashtable
    */
    private static void enumHashtable(Hashtable table) {
        long startTime = System.currentTimeMillis();
        Enumeration enu = table.elements();
        while(enu.hasMoreElements()) {
            System.out.println("enu:"+enu.nextElement());
            //enu.nextElement();
        }

        long endTime = System.currentTimeMillis();
        countTime(startTime, endTime);
    }

    private static void countTime(long start, long end) {
        System.out.println("time: "+(end-start)+"ms");
    }
}


