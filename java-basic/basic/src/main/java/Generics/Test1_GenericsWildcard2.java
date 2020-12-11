package Generics;

import Generics.element_generics.Plate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Generics.element.Fruits.*;

/**
 * Created by Defias on 2020/07.
 * Description: 泛型 - 逆变
 *
 */

public class Test1_GenericsWildcard2 {
    public static void main(String[] args) {
        test0();
        //test1();
    }

    public static void test0() {
        Plate<Fruit> pf = new Plate<Fruit>(new Fruit());
        Plate<Apple> pa = new Plate<Apple>(new Apple());
        Plate<Jonathan> pj = new Plate<Jonathan>(new Jonathan());

        //Plate<? super Apple>引用可以赋予Apple及其父类的Plate对象
        Plate<? super Apple> pa2;
        pa2 = pf;
        pa2 = pa;
        //! pa2 = pj;

        //下界通配符的副作用： <? super Apple>会使从盘子里取出的东西只能放在Object对象里
        pa2.set(new Apple());
        pa2.set(new Jonathan());
        //! pa2.set(new Fruit());    //向Plate<? super Apple>对象添加成员时只能添加Apple及其子类对象成员

        //! Apple fa = pa2.get();
        //! Fruit ff = pa2.get();
        Object fo = pa2.get();

        //依次类推：
        List<? super Apple> apples = new ArrayList<>();
        //apples中可以安全的添加Apple及Apple的子类对象
        apples.add(new Apple());
        apples.add(new Jonathan());
        //apples.add(new Fruit()); //Error
    }

    public static void test1() {
        GenericWriting.f1();
        GenericWriting.f2();
    }

    public static void test2() {
        //GenericReading.f1();
        GenericReading.f2();
        GenericReading.f3();
    }
}


class GenericWriting {
    static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }

    static List<Apple> apples = new ArrayList<Apple>();
    static List<Fruit> fruit = new ArrayList<Fruit>();

    static void f1() {
        writeExact(apples, new Apple());
        // writeExact(fruit, new Apple()); // Error:
        // Incompatible types: found Fruit, required Apple
    }

    static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }

    static void f2() {
        writeWithWildcard(apples, new Apple());

        //由第一个参数可知：list中可以放Fruit及Fruit的导出类对象，因此放Apple对象是OK的
        writeWithWildcard(fruit, new Apple());

    }
}


class GenericReading {
    static <T> T readExact(List<T> list) {
        return list.get(0);
    }

    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruit = Arrays.asList(new Fruit());

    // A static method adapts to each call:
    static void f1() {
        Apple a = readExact(apples);
        Fruit f = readExact(fruit);
        f = readExact(apples);
    }

    // If, however, you have a class, then its type is
    // established when the class is instantiated:
    static class Reader<T> {
        T readExact(List<T> list) {
            return list.get(0);
        }
    }

    static void f2() {
        Reader<Fruit> fruitReader = new Reader<Fruit>();
        Fruit f = fruitReader.readExact(fruit);
        // Fruit a = fruitReader.readExact(apples); // Error:
        // readExact(List<Fruit>) cannot be
        // applied to (List<Apple>).
    }

    static class CovariantReader<T> {
        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void f3() {
        CovariantReader<Fruit> fruitReader = new CovariantReader<Fruit>();
        Fruit f = fruitReader.readCovariant(fruit);
        Fruit a = fruitReader.readCovariant(apples);
    }
}