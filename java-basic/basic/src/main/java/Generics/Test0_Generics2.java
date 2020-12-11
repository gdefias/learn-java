package Generics;
import Generics.element.Employee;
import Generics.element.Manager;
import Generics.element_generics.Pair;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * Description:  泛型的基本使用
 * User: Defias
 * Date: 2018-05
 */

public class Test0_Generics2 {

    public static void main(String[] args) {

        //test1();
        test2();
    }

    public static void test1() {

        //GregorianCalendar是一个计算函数，是 Calendar 的一个具体子类，提供了世界上大多数国家/地区使用的标准日历系统。是一种混合日历
        GregorianCalendar[] birthdays = new GregorianCalendar[] {
                new GregorianCalendar(1906, Calendar.DECEMBER, 9), // G. Hopper
                new GregorianCalendar(1815, Calendar.DECEMBER, 10), // A. Lovelace
                new GregorianCalendar(1903, Calendar.DECEMBER, 3), // J. von Neumann
                new GregorianCalendar(1910, Calendar.JUNE, 22), // K. Zuse
        };

        Pair<GregorianCalendar> mm = minmax(birthdays);
        System.out.println("min = " + mm.getFirst().getTime());
        System.out.println("max = " + mm.getSecond().getTime());
    }

    public static void test2() {
        Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
        Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
        Pair<Manager> buddies = new Pair<>(ceo, cfo);
        printPr(buddies);

        ceo.setBonus(1000000);
        cfo.setBonus(500000);
        Manager[] managers = { ceo, cfo };

        Pair<Employee> result = new Pair<>();
        minmaxBonus(managers, result);
        System.out.println("first: " + result.getFirst().getName() + ", second: " + result.getSecond().getName());

    }


    //求最小值和最大值
    //使用泛型类Pair<T>时对类型参数加限定，虽然Comparable是接口，但仍然使用关键字extends
    public static  <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0)
            return null;
        T min = a[0];
        T max = a[0];
        for (int i=1; i<a.length; i++) {
            if (min.compareTo(a[i]) > 0)
                min = a[i];
            if (max.compareTo(a[i]) < 0)
                max = a[i];
        }
        return new Pair<T>(min, max);
    }

    //打印一对儿
    public static void printPr(Pair<? extends Employee> p) {
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    //求最小值和最大值
    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
        if (a == null || a.length == 0)
            return;
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.getBonus() > a[i].getBonus())
                min = a[i];
            if (max.getBonus() < a[i].getBonus())
                max = a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }


    //检测一对儿是否存在空
    public static boolean hasNulls(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }


    public static void swap(Pair<?> p) {
        swapHelper(p);
    }

    //交换一对儿的位置
    public static <T> void swapHelper(Pair<T> p) {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}



