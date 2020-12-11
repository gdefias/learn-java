package Stream;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.regex.Pattern;
/**
 * Created by Defias on 2020/07.
 * Description: 流 - 创建流

 Stream.Builder

 Arrays类中含有一个名为stream()的静态方法用于把数组转换成为流Stream

 stream()同样可以产生IntStream，LongStream 和 DoubleStream

 java.util.regex.Pattern中增加了一个新的方法 splitAsStream()
 这个方法可以根据传入的公式将字符序列转化为流
 但是有一个限制，输入只能是CharSequence，因此不能将流作为splitAsStream()的参数
 */

public class TestStream4 {
    public static void main(String[] args) throws Exception {
        test1();
        test2();
        test3();
        test4();
    }

    //流的创建者模式
    public static void test1() throws Exception {
        new FileToWordsBuilder("base/src/Cheese.dat")
                .stream()
                .limit(7)
                .map(w -> w + " ")
                .forEach(System.out::print);
        System.out.println();
    }

    //Arrays.stream()
    public static void test2() {
        Arrays.stream(new Operations[] {  //动态创建Operations对象的数组，实现execute方法
                () -> Operations.show("Bing"),
                () -> Operations.show("Crack"),
                () -> Operations.show("Twist"),
                () -> Operations.show("Pop")
        }).forEach(Operations::execute);
        System.out.println();
    }


    public static void test3() {
        Arrays.stream(new double[] { 3.14159, 2.718, 1.618 })
                .forEach(n -> System.out.format("%f ", n));
        System.out.println();

        Arrays.stream(new int[] { 1, 3, 5 })
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        Arrays.stream(new long[] { 11, 22, 44, 66 })
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        // 选择一个子域:
        Arrays.stream(new int[] { 1, 3, 5, 7, 15, 28, 37 }, 3, 6)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println("\n");
    }

    public static void test4() throws Exception {
        FileToWordsRegexp fw = new FileToWordsRegexp("base/src/Cheese.dat");
        fw.stream()
                .limit(7)
                .map(w -> w + " ")
                .forEach(System.out::print);

        //可以多次调用 stream() 在已存储的字符串中创建一个新的流
        //这里有个限制，整个文件必须存储在内存中；在大多数情况下这并不是什么问题，但是这损失了流操作非常重要的优势：
        //  流“不需要存储”。当然它们需要一些内部存储，但是这只是序列的一小部分，和持有整个序列并不相同
        //  它们是懒加载计算的
        fw.stream()
                .skip(7)
                .limit(2)
                .map(w -> w + " ")
                .forEach(System.out::print);
    }
}

class FileToWordsBuilder {
    Stream.Builder<String> builder = Stream.builder();

    public FileToWordsBuilder(String filePath) throws Exception {
        Files.lines(Paths.get(filePath))
                .skip(1) // 略过开头的注释行
                .forEach(line -> {
                    for (String w : line.split("[ .?,]+"))
                        builder.add(w);  //只要不调用builder的build()方法，就可以继续向builder对象中添加单词
                });
    }

    Stream<String> stream() {
        return builder.build();
    }
}


interface Operations {
    void execute();

    static void runOps(Operations... ops) {
        for (Operations op: ops) {
            op.execute();
        }
    }

    static void show(String msg) {
        System.out.println(msg);
    }
}


class FileToWordsRegexp {
    private String all;

    public FileToWordsRegexp(String filePath) throws Exception {
        all = Files.lines(Paths.get(filePath))
                .skip(1) // First (comment) line
                .collect(Collectors.joining(" "));
    }

    public Stream<String> stream() {
        return Pattern.compile("[ .,?]+").splitAsStream(all);
    }
}