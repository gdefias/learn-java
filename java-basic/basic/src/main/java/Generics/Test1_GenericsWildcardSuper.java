package Generics;
import Generics.element_generics.Pair;
import org.testng.annotations.Test;
/**
 泛型通配符  super

 * */

public class Test1_GenericsWildcardSuper {


    @Test
    public static void test1() {
        Pair<Number> p1 = new Pair<>(12.3, 4.56);
        Pair<Integer> p2 = new Pair<>(123, 456);
        setSame(p1, 100);
        setSame(p2, 200);
        System.out.println(p1.getFirst() + ", " + p1.getSecond());
        System.out.println(p2.getFirst() + ", " + p2.getSecond());
    }


    static void setSame(Pair<? super Integer> p, Integer n) {
        p.setFirst(n);
        //Integer x = p.getSecond();    //编译错误
    }
}
