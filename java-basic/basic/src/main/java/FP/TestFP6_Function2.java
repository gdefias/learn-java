package FP;
import java.util.*;
import java.util.function.*;
/**
 * Created by Defias on 2020/07.
 * Description: Lambda表达式 - 函数接口

 */

public class TestFP6_Function2 {


    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1() {
        BiConsumer<In1,In2> bic;

        bic = MethodConversion::accept;
        bic.accept(new In1(), new In2());

        //在使用函数接口时，名称无关紧要——只要参数类型和返回类型相同
        bic = MethodConversion::someOtherName;
        bic.accept(new In1(), new In2());
    }

    public static void test2() {
        Supplier<AA> s = ClassFunctionals::f1;
        s.get();

        Comparator<AA> c = ClassFunctionals::f2;
        c.compare(new AA(), new AA());

        Consumer<AA> cons = ClassFunctionals::f3;
        cons.accept(new AA());

        BiConsumer<AA,BB> bicons = ClassFunctionals::f4;
        bicons.accept(new AA(), new BB());

        Function<AA,CC> f = ClassFunctionals::f5;
        CC cc = f.apply(new AA());

        BiFunction<AA,BB,CC> bif = ClassFunctionals::f6;
        cc = bif.apply(new AA(), new BB());

        Predicate<AA> p = ClassFunctionals::f7;
        boolean result = p.test(new AA());

        BiPredicate<AA,BB> bip = ClassFunctionals::f8;
        result = bip.test(new AA(), new BB());

        UnaryOperator<AA> uo = ClassFunctionals::f9;
        AA aa = uo.apply(new AA());

        BinaryOperator<AA> bo = ClassFunctionals::f10;
        aa = bo.apply(new AA(), new AA());
    }

    public static void test3() {
        BiConsumerPermutations.bicid.accept(47, 11.34);
        BiConsumerPermutations.bicdi.accept(22.45, 92);
        BiConsumerPermutations.bicil.accept(1, 11L);
    }
}


class MethodConversion {
    static void accept(In1 i1, In2 i2) {
        System.out.println("accept()");
    }

    static void someOtherName(In1 i1, In2 i2) {
        System.out.println("someOtherName()");
    }
}

class In1 {}
class In2 {}


class ClassFunctionals {
    static AA f1() { return new AA(); }
    static int f2(AA aa1, AA aa2) { return 1; }
    static void f3(AA aa) {}
    static void f4(AA aa, BB bb) {}
    static CC f5(AA aa) { return new CC(); }
    static CC f6(AA aa, BB bb) { return new CC(); }
    static boolean f7(AA aa) { return true; }
    static boolean f8(AA aa, BB bb) { return true; }
    static AA f9(AA aa) { return new AA(); }
    static AA f10(AA aa1, AA aa2) { return new AA(); }
}


class AA {}
class BB {}
class CC {}


class BiConsumerPermutations {
    static BiConsumer<Integer, Double> bicid = (i, d) ->
            System.out.format("%d, %f%n", i, d);
    static BiConsumer<Double, Integer> bicdi = (d, i) ->
            System.out.format("%d, %f%n", i, d);
    static BiConsumer<Integer, Long> bicil = (i, l) ->
            System.out.format("%d, %d%n", i, l);
}