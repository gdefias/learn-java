package Generics;

import Generics.element_generics.Pair;
import org.testng.annotations.Test;

public class Test1_GenericsWildcardWenhao {

    @Test
    public static void test1() {
        Pair<Integer> p = new Pair<>(123, 456);
        System.out.println(isNull(p));
    }

    @Test
    public static void test2() {
        Pair<Integer> p = new Pair<>(123, 456);
        System.out.println(isNull2(p));
    }

    @Test
    public static void test3() {
        Pair<Integer> p = new Pair<>(123, 456);
        Pair<?> p2 = p; // 安全地向上转型
        System.out.println(p2.getFirst() + ", " + p2.getSecond());
    }

    static boolean isNull(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

    //引入泛型参数<T>消除<?>通配符
    static <T> boolean isNull2(Pair<T> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

}
