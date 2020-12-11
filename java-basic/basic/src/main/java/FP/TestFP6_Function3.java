package FP;

/**
 * Created by Defias on 2020/07.
 * Description: 多参数函数式接口

 java.util.functional中的接口是有限的。比如有了 BiFunction，但它不能变化。 如果需要三参数函数的接口怎么办？
 其实这些接口非常简单，很容易查看 Java 库源代码并自行创建
 */
public class TestFP6_Function3 {
    static int f(int i, long l, double d) {
        return 99;
    }

    public static void main(String[] args) {
        TriFunction<Integer, Long, Double, Integer> tf =
                TestFP6_Function3::f;
        tf = (i, l, d) -> 12;
    }
}

@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}