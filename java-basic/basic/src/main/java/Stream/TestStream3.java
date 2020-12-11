package Stream;
import java.util.stream.*;
import java.util.Random;
import java.util.function.Supplier;
import static java.util.stream.IntStream.range;

/**
 * Created by Defias on 2020/07.
 * Description: 流 - 创建流

 IntStream 类提供了静态方法

 range() 方法
 用于生成整数序列的流

 iterate()方法
 以种子（第一个参数）开头，并将其传给方法（第二个参数）。方法的结果将添加到流，并存储作为第一个参数用于下次调用 iterate()，依次类推
 */

public class TestStream3 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    public static void test1() {
        // 传统方法:
        int result = 0;
        for (int i = 10; i < 20; i++)
            result += i;
        System.out.println(result);

        // for-in 循环:
        result = 0;
        for (int i : range(10, 20).toArray())
            result += i;
        System.out.println(result);

        // 使用流:
        System.out.println(range(10, 20).sum());
        System.out.println();
    }

    public static void test2() {
        repeat(3, () -> System.out.println("Looping!"));
        repeat(2, Looping::hi);
        System.out.println();
    }

    public static void test3() {
        String word = Stream.generate(new Generator())
                .limit(30)
                .collect(Collectors.joining());
        System.out.println(word);
        System.out.println();
    }

    public static void test4() {
        Stream.generate(() -> "duplicate")
                .limit(3)
                .forEach(System.out::println);
        System.out.println();
    }

    public static void test5() {
        Stream.generate(Bubble::bubbler)
                .limit(5)
                .forEach(System.out::println);
        System.out.println();
    }


    public static void test6() {
        new Fibonacci().numbers()
                //.skip(20) // 过滤前 20 个
                .limit(10) // 然后取 10 个
                .forEach(System.out::println);
        System.out.println();
    }


    public static void repeat(int n, Runnable action) {
        range(0, n).forEach(i -> action.run());
    }

    static class Looping {
        static void hi() {
            System.out.println("Hi!");
        }
    }
}



class Generator implements Supplier<String> {
    Random rand = new Random(47);
    char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public String get() {
        return "" + letters[rand.nextInt(letters.length)];
    }
}


class Fibonacci {
    int x = 1;

    Stream<Integer> numbers() {
        return Stream.iterate(0, i -> {
            int result = x + i;
            x = i;
            return result;
        });
    }
}