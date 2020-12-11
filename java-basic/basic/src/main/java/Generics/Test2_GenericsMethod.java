package Generics;
import Generics.element.Coffees.Coffee;
import Collection.generator.RandomGenerator;
import Generics.element_generics.Generator;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Defias on 2020/07.
 * Description: 泛型方法
 *
 */


public class Test2_GenericsMethod {


    //使用泛型方式可以不必指明参数类型，编译器会自动找到具体类型（类型参数推断），就像f方法被无限次重载过一样
    @Test
    public static void test0() {
        Test2_GenericsMethod gm = new Test2_GenericsMethod();
        gm.f("");
        gm.<String>f("");   //显示指明参数类型也没问题
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('c');
        gm.f(gm);
    }

    //泛型方法
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }


    //泛型方法
    @Test
    public static void test1() {
        Integer[] integers = {1, 2, 3, 4, 5};
        String[] strings = {"London", "Paris", "New York", "Austin"};

        print(integers);
        print(strings);
    }

    public static <E> void print(E[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }


    @Test
    public static void test2() {
        List<String> ls = makeList("A");
        System.out.println(ls);

        ls = makeList("A", "B", "C");
        System.out.println(ls);

        ls = makeList("ABCDEFFHIJKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(ls);
    }

    //可变参数与泛型
    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<T>();
        for(T item : args)
            result.add(item);
        return result;
    }

    //传入不同的生成器来填充集合
    @Test
    public static void test3() {
        Collection<Coffee> coffee = fill(new ArrayList<Coffee>(), new CoffeeGenerator(), 4);
        for(Coffee c : coffee)
            System.out.println(c);

        Collection<Integer> fnumbers = fill(new ArrayList<Integer>(), new Fibonacci(), 12);
        for(int i : fnumbers)
            System.out.print(i + ", ");
    }

    //泛型方法
    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for(int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

}
