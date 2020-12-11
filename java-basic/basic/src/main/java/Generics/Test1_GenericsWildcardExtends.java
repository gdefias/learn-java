package Generics;
import Generics.element_generics.Pair;
import Generics.element_generics.PairE;
import org.testng.annotations.Test;
/**
 泛型通配符  extends

 * */

public class Test1_GenericsWildcardExtends {

    @Test
    public static void test1() {
        Pair<Integer> p = new Pair<>(123, 456);

        //编译错误
        //因为Pair<Integer>不是Pair<Number>的子类，因此add(Pair<Number>)不接受参数类型Pair<Integer>
        //int n = add(p); 

        //正确
        //可以传入Pair<Integer>、Pair<Double>、Pair<BigDecimal>等类型，因为Integer、Double、BigDecimal都是Number的子类
        int n = add2(p);

        System.out.println(n);
    }

    @Test
    public static void test2() {
        Pair<Integer> p = new Pair<>(123, 456);
        int n = setadd(p);
        System.out.println(n);
    }

    @Test
    public static void test3() {
        Pair<Number> p = new Pair<>(123, 456);
        int n = setadd2(p);
        System.out.println(n);
    }

    @Test
    public static void test4() {
        PairE<Number> p = new PairE<>(123, 456);
        //PairE<Integer> p = new PairE<>(123, 456);   //编译错误
        int n = setadd3(p);
        System.out.println(n);
    }

    
    static int add(Pair<Number> p) {
        Number first = p.getFirst();
        Number last = p.getSecond();
        return first.intValue() + last.intValue();
    }

    static int add2(Pair<? extends Number> p) {
        Number first = p.getFirst();
        Number last = p.getSecond();
        return first.intValue() + last.intValue();
    }

    static int setadd(Pair<? extends Number> p) {
        Number first = p.getFirst();
        Number last = p.getSecond();
        //编译错误
        //p.setFirst(new Integer(first.intValue() + 100));
        //p.setSecond(new Integer(last.intValue() + 100));
        return p.getFirst().intValue() + p.getFirst().intValue();
    }

    static int setadd2(Pair<Number> p) {
        Number first = p.getFirst();
        Number last = p.getSecond();
        p.setFirst(new Integer(first.intValue() + 100));
        p.setSecond(new Integer(last.intValue() + 100));
        return p.getFirst().intValue() + p.getFirst().intValue();
    }

    static int setadd3(PairE<Number> p) {
        Number first = p.getFirst();
        Number last = p.getSecond();
        p.setFirst(new Integer(first.intValue() + 100));
        p.setSecond(new Integer(last.intValue() + 100));
        return p.getFirst().intValue() + p.getFirst().intValue();
    }

}
