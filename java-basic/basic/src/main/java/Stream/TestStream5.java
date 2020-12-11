package Stream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.LongStream.iterate;
import static java.util.stream.LongStream.rangeClosed;
/**
 * Created by Defias on 2020/07.
 * Description: 流 - 中间操作

 peek()
 操作的目的是帮助调试。它允许你无修改地查看流中的元素

 应用函数到元素
 map(Function)：将函数操作应用在输入流的元素中，并将返回值传递到输出流中
 mapToInt(ToIntFunction)：操作同上，但结果是 IntStream
 mapToLong(ToLongFunction)：操作同上，但结果是 LongStream
 mapToDouble(ToDoubleFunction)：操作同上，但结果是 DoubleStream

 flatMap()
 做了两件事情：它获取你的流产生（ stream-producing）函数，并将其应用于新到的元素（正如 map() 所做的），然后获取每一个流并将其
 “展平”为元素。所以它的输出只是元素
 flatMap(Function)：当 Function 产生流时使用
 flatMapToInt(Function)：当 Function 产生 IntStream 时使用
 flatMapToLong(Function)：当 Function 产生 LongStream 时使用
 flatMapToDouble(Function)：当 Function 产生 DoubleStream 时使用

 concat()
 它以参数顺序组合了两个流

 */

public class TestStream5 {
    static Random rand = new Random(47);

    public static void main(String[] args) throws Exception {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
    }

    //跟踪和调试
    public static void test1() throws Exception {
        FileToWords.stream("base/src/Cheese.dat")
                .skip(2)
                .limit(4)
                .map(w -> w + " ")
                .peek(System.out::print)
                .map(String::toUpperCase)
                .peek(System.out::print)
                .map(String::toLowerCase)
                .forEach(System.out::print);
        System.out.println("\n");
    }

    //流元素排序
    public static void test2() throws Exception {
        FileToWords.stream("base/src/Cheese.dat")
                .skip(10)
                .limit(10)
                .sorted(Comparator.reverseOrder())
                .map(w -> w + " ")
                .forEach(System.out::print);
        System.out.println("\n");
    }


    public static void test3() {
        numbers()
                .limit(10)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        numbers()
                .skip(90)
                .limit(10)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println("\n");
    }

    public static void test4() {
        FunctionMap.test("add brackets", s -> "[" + s + "]");
        FunctionMap.test("Increment", s -> {
                    try {
                        return Integer.parseInt(s) + 1 + "";
                    }
                    catch(NumberFormatException e) {
                        return s;
                    }
                }
        );
        FunctionMap.test("Replace", s -> s.replace("2", "9"));
        FunctionMap.test("Take last digit", s -> s.length() > 0 ?
                s.charAt(s.length() - 1) + "" : s);
        System.out.println("\n");

    }


    //可以产生和接收类型完全不同的类型，从而改变流的数据类型
    public static void test5() {
        Stream.of(1, 5, 7, 9, 11, 13)
                .map(Numbered::new)  //引用Numbered的构造方法，转化成为 Numbered 类型
                .forEach(System.out::println);
        System.out.println("\n");
    }

    //如果使用Function返回的结果是数值类型的一种，必须使用合适的mapTo数值类型进行替代
    public static void test6() {
        Stream.of("5", "7", "9")
                .mapToInt(Integer::parseInt)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        Stream.of("17", "19", "23")
                .mapToLong(Long::parseLong)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        Stream.of("17", "1.9", ".23")
                .mapToDouble(Double::parseDouble)
                .forEach(n -> System.out.format("%f ", n));
        System.out.println("\n");
    }

    public static void test7() {
        //流的流
        Stream.of(1, 2, 3)
                .map(i -> Stream.of("Gonzo", "Kermit", "Beaker"))
                .map(e-> e.getClass().getName())
                .forEach(System.out::println);
        System.out.println("-------------");

        Stream.of(1, 2, 3)
                .flatMap(i -> Stream.of("Gonzo", "Fozzie", "Beaker"))
                .forEach(System.out::println);
        System.out.println("\n");
    }

    public static void test8() {
        //在每个随机Integer流的末尾添加一个 -1 作为标记
        Stream.of(1, 2, 3, 4, 5)
                .flatMapToInt(i -> IntStream.concat(
                        rand.ints(0, 100).limit(i), IntStream.of(-1)))
                .forEach(n -> System.out.format("%d ", n));
        System.out.println("\n");
    }

    public static void test9() throws Exception {
        FileToWords.stream("base/src/Cheese.dat")
                .limit(7)
                .forEach(s -> System.out.format("%s ", s));
        System.out.println();
        FileToWords.stream("base/src/Cheese.dat")
                .skip(7)
                .limit(2)
                .forEach(s -> System.out.format("%s ", s));
    }



    //过滤器函数，用于检测质数
    public static Boolean isPrime(long n) {
        return rangeClosed(2, (long)Math.sqrt(n))
                .noneMatch(i -> n % i == 0);
    }

    public static LongStream numbers() {
        return iterate(2, i -> i + 1)
                .filter(TestStream5::isPrime);
    }

}



class FileToWords {
    public static Stream<String> stream(String filePath) throws Exception {
        return Files.lines(Paths.get(filePath))
                .skip(1) // First (comment) line
                .flatMap(line ->
                        Pattern.compile("\\W+").splitAsStream(line));
    }
}

//应用函数到元素
class FunctionMap {
    static String[] elements = { "12", "", "23", "45" };

    static Stream<String> testStream() {
        return Arrays.stream(elements);
    }

    static void test(String descr, Function<String, String> func) {
        System.out.println(" ---( " + descr + " )---");
        testStream()
                .map(func)
                .forEach(System.out::println);
    }
}


class Numbered {
    final int n;

    Numbered(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "Numbered(" + n + ")";
    }
}