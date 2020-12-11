package FP;
import java.util.function.*;
/**
 * Created by Defias on 2020/07.
 * Description:  柯里化

 柯里化和部分求值
 */

public class TestFP9_Currying {
    // 未柯里化:
    static String uncurried(String a, String b) {
        return a + b;
    }

    public static void main(String[] args) {
        test1();
//        test2();
//        test3();

    }

    public static void test1() {
        // 柯里化的函数:
        Function<String, Function<String, String>> sum =
                a -> b -> a + b; // [1]
        System.out.println(uncurried("Hi ", "Ho"));

        Function<String, String>
                hi = sum.apply("Hi "); // [2]
        System.out.println(hi.getClass().getName());
        System.out.println(hi.apply("Ho"));

        // 部分应用:
        Function<String, String> sumHi =
                sum.apply("Hup ");
        System.out.println(sumHi.apply("Ho"));
        System.out.println(sumHi.apply("Hey"));

    }

    public static void test2() {
        //     一个三参数函数
        Function<String,
                Function<String,
                        Function<String, String>>> sum2 =
                a -> b -> c -> a + b + c;
        Function<String,
                Function<String, String>> hi2 =
                sum2.apply("Hi ");
        Function<String, String> ho2 =
                hi2.apply("Ho ");
        System.out.println(ho2.apply("Hup"));
    }

    public static void test3() {
        //处理基本类型和装箱
        IntFunction<IntUnaryOperator>
                curriedIntAdd = a -> b -> a + b;
        IntUnaryOperator add4 = curriedIntAdd.apply(4);
        System.out.println(add4.applyAsInt(5));
    }
}
