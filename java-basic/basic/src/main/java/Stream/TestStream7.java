package Stream;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Defias on 2020/07.
 * Description: 流 - 终端操作

 匹配（Matching）
 allMatch(Predicate) ：如果流的每个元素根据提供的 Predicate 都返回 true 时，结果返回为 true。这个操作将会在第一个 false 之后短路；
 也就是不会在发生 false 之后继续执行计算
 anyMatch(Predicate)：如果流中的一个元素根据提供的 Predicate 返回 true 时，结果返回为 true。这个操作将会在第一个 true 之后短路；
 也就是不会在发生 true 之后继续执行计算
 noneMatch(Predicate)：如果流的每个元素根据提供的 Predicate 都返回 false 时，结果返回为 true。这个操作将会在第一个 true 之后短路；
 也就是不会在发生 true 之后继续执行计算


 选择元素
 findFirst()：返回一个含有第一个流元素的 Optional，如果流为空返回 Optional.empty
 findAny(：返回含有任意流元素的 Optional，如果流为空返回 Optional.empty


 信息（Informational）
 count()：流中的元素个数
 max(Comparator)：根据所传入的 Comparator 所决定的“最大”元素
 min(Comparator)：根据所传入的 Comparator 所决定的“最小”元素

 数字流信息（Information for Numeric Streams）
 average() ：求取流元素平均值
 max() 和 min()：因为这些操作在数字流上面，所以不需要 Comparator
 sum()：对所有流元素进行求和
 summaryStatistics()：生成可能有用的数据。目前还不太清楚他们为什么觉得有必要这样做，但是你可以直接使用方法产生所有的数据

 */
public class TestStream7 {
    public static void main(String[] args) throws Exception {
        test1();
        test2();
        test3();
        test4();
    }

    public static void test1() {
        Matching.show(Stream::allMatch, 10);
        Matching.show(Stream::allMatch, 4);
        Matching.show(Stream::anyMatch, 2);
        Matching.show(Stream::anyMatch, 0);
        Matching.show(Stream::noneMatch, 5);
        Matching.show(Stream::noneMatch, 0);
        System.out.println();
    }

    public static void test2() {
        System.out.println(rands().findFirst().getAsInt());
        System.out.println(rands().parallel().findFirst().getAsInt());
        System.out.println(rands().findAny().getAsInt());
        System.out.println(rands().parallel().findAny().getAsInt());
        System.out.println(" -----");

        //如果必须选择流中最后一个元素就使用reduce()
        OptionalInt last = IntStream.range(10, 20).reduce((n1, n2) -> n2);
        System.out.println(last.orElse(-1));

        // Non-numeric object:
        Optional<String> lastobj =
                Stream.of("one", "two", "three")
                        .reduce((n1, n2) -> n2);
        System.out.println(
                lastobj.orElse("Nothing there!"));
        System.out.println();
    }

    public static void test3() throws Exception {
        System.out.println(
                FileToWords.stream("base/src/Cheese.dat").count());

        System.out.println(
                FileToWords.stream("base/src/Cheese.dat")
                        .min(String.CASE_INSENSITIVE_ORDER)
                        .orElse("NONE"));

        System.out.println(
                FileToWords.stream("base/src/Cheese.dat")
                        .max(String.CASE_INSENSITIVE_ORDER)
                        .orElse("NONE"));
        System.out.println();
    }

    public static void test4() {
        System.out.println(rands().average().getAsDouble());
        System.out.println(rands().max().getAsInt());
        System.out.println(rands().min().getAsInt());
        System.out.println(rands().sum());
        System.out.println(rands().summaryStatistics());
        System.out.println();
    }


    public static IntStream rands() {
        return TestStream6.test1();
    }
}



interface Matcher extends BiPredicate<Stream<Integer>, Predicate<Integer>> {}

class Matching {
    static void show(Matcher match, int val) {
        System.out.println(
                match.test(
                        IntStream.rangeClosed(1, 9)
                                .boxed()
                                .peek(n -> System.out.format("%d ", n)),
                        n -> n < val));
    }
}