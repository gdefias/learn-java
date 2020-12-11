package Stream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Defias on 2020/07.
 * Description:  流 - 终端操作

 这些操作获取一个流并产生一个最终结果；它们不会像后端流提供任何东西。因此，终端操作总是你在管道中做的最后一件事情

 转化成数组
 toArray()：将流转换成适当类型的数组
 toArray(generator)：在特殊情况下，生成器用于分配你自己的数组存储

 对每个元素应用最终操作
 forEach(Consumer)：已经看到很多次 System.out::println 作为 Consumer 函数
 forEachOrdered(Consumer)： 这个版本保证了 forEach 的操作顺序是原始流顺序

 收集（Collecting）
 collect(Collector)：使用 Collector 来累计流元素到结果集合中
 collect(Supplier, BiConsumer, BiConsumer)：同上，但是 Supplier 创建了一个新的结果集合，第一个 BiConsumer 是将下一个元素包含
 在结果中的函数，而第二个 BiConsumer 是用于将两个值组合起来


 组合所有流元素（Combining All Stream Elements）
 reduce(BinaryOperator)：使用 BinaryOperator 来组合所有流中的元素。因为流可能为空，其返回值为 Optional
 reduce(identity, BinaryOperator)：功能同上，但是使用 identity 作为其组合的初始值。因此如果流为空，identity 就是结果
 reduce(identity, BiFunction, BinaryOperator)：这个版本更为复杂（所以我们不会介绍它），在这里被提到是因为它使用起来会更有效。
 通常，你可以显示的组合 map() 和 reduce() 来更简单的表达这一点

 */

public class TestStream6 {
    private static int[] rints = new Random(47)
            .ints(0, 1000)
            .limit(100)
            .toArray();

    static final int SZ = 14;

    public static void main(String[] args) throws Exception {
        test2();
        test3();
        test4();
    }


    public static IntStream test1() {
        return Arrays.stream(rints);
    }

    public static void test2() {
        test1().limit(SZ)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();
        test1().limit(SZ)
                .parallel()
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();
        test1().limit(SZ)
                .parallel()
                .forEachOrdered(n -> System.out.format("%d ", n));
        System.out.println();
    }

    public static void test3() throws Exception {
        Set<String> words2 =
                Files.lines(Paths.get("base/src/main/java/Stream/TestStream6.java"))
                        .flatMap(s -> Arrays.stream(s.split("\\W+")))
                        .filter(s -> !s.matches("\\d+")) // No numbers
                        .map(String::trim)
                        .filter(s -> s.length() > 2)
                        .limit(100)
                        .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(words2);
        System.out.println(" -----");

        Map<Integer, Character> map =
                new RandomPair().stream()
                        .limit(8)
                        .collect(
                                Collectors.toMap(Pair::getI, Pair::getC));
        System.out.println(map);
        System.out.println(" -----");

        ArrayList<String> words =
                FileToWords.stream("base/src/Cheese.dat")
                        .collect(ArrayList::new,
                                ArrayList::add,
                                ArrayList::addAll);
        words.stream()
                .filter(s -> s.equals("cheese"))
                .forEach(System.out::println);
        System.out.println();
    }

    public static void test4() {
        Stream.generate(Frobnitz::supply)
                .limit(10)
                .peek(System.out::println)
                .reduce((fr0, fr1) -> fr0.size < 50 ? fr0 : fr1)
                .ifPresent(System.out::println);
        System.out.println();
    }

}



class Pair {
    public final Character c;
    public final Integer i;
    Pair(Character c, Integer i) {
        this.c = c;
        this.i = i;
    }
    public Character getC() { return c; }
    public Integer getI() { return i; }
    @Override
    public String toString() {
        return "Pair(" + c + ", " + i + ")";
    }
}

class RandomPair {
    Random rand = new Random(47);
    // An infinite iterator of random capital letters:
    Iterator<Character> capChars = rand.ints(65,91)
            .mapToObj(i -> (char)i)
            .iterator();

    public Stream<Pair> stream() {
        return rand.ints(100, 1000).distinct()
                .mapToObj(i -> new Pair(capChars.next(), i));
    }
}


class Frobnitz {
    int size;

    Frobnitz(int sz) { size = sz; }

    @Override
    public String toString() {
        return "Frobnitz(" + size + ")";
    }
    // Generator:
    static Random rand = new Random(47);

    static final int BOUND = 100;

    static Frobnitz supply() {
        return new Frobnitz(rand.nextInt(BOUND));
    }
}


