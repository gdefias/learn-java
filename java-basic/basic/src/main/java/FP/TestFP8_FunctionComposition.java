package FP;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by Defias on 2020/07.
 * Description:  函数组合


 */
public class TestFP8_FunctionComposition {

    public static void main(String[] args) {
        testFunction();
        testPredicate();
    }

    public static void testFunction() {
        System.out.println(FunctionComposition.f4.apply("GO AFTER ALL AMBULANCES"));
        System.out.println("---------");
    }

    public static void testPredicate() {
        Stream.of("bar", "foobar", "foobaz", "fongopuckey")
                .filter(PredicateComposition.p4)
                .forEach(System.out::println);
    }
}



class FunctionComposition {
    static Function<String, String> f1 = s -> {
        System.out.println(s);
        return s.replace('A', '_');
    },
            f2 = s -> s.substring(3),
            f3 = s -> s.toLowerCase(),
            f4 = f1.compose(f2).andThen(f3);
}


class PredicateComposition {
    static Predicate<String>
            p1 = s -> s.contains("bar"),
            p2 = s -> s.length() < 5,
            p3 = s -> s.contains("foo"),
            p4 = p1.negate().and(p2).or(p3);
}