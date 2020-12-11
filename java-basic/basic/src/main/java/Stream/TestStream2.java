package Stream;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Created by Defias on 2020/07.
 * Description: 流 - 创建流
 * 
 */
public class TestStream2 {

    //forEach聚合操作
    @Test
    public static void test1() {
        Stream.of(new Bubble(1), new Bubble(2), new Bubble(3))
                .forEach(System.out::println);

        Stream.of("It's ", "a ", "wonderful ", "day ", "for ", "pie!")
                .forEach(System.out::print);

        System.out.println();
        Stream.of(3.14159, 2.718, 1.618)
                .forEach(System.out::println);
    }


    //通过集合创建流
    @Test
    public static void test2() {
        //List
        List<Bubble> bubbles = Arrays.asList(new Bubble(1), new Bubble(2), new Bubble(3));
        System.out.println(bubbles.stream()
                .mapToInt(b -> b.i)
                .sum());
        System.out.println("-----");

        //Set
        Set<String> w = new HashSet<>(Arrays.asList("It's a wonderful day for pie!".split(" ")));
        w.stream().map(x -> "|" + x + "|").forEach(System.out::print);
        System.out.println("\n-----");

        //Map
        Map<String, Double> m = new HashMap<>();
        m.put("pi", 3.14159);
        m.put("e", 2.718);
        m.put("phi", 1.618);
        m.entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .forEach(System.out::println);
    }

    //随机数流
    @Test
    public static void test3() {
        Random rand = new Random(47);
        show(rand.ints().boxed());  //ints()方法：生成IntStream，表示无限个int类型范围内的数据
                                    //boxed()方法：IntStream ---> Stream<Integer>
        show(rand.longs().boxed());
        show(rand.doubles().boxed());
        System.out.println("===================");

        //ints(int randomNumberOrigin, int randomNumberBound)方法：控制上限和下限
        //生成IntStream，表示无限个[10,20)范围内的数据
        show(rand.ints(10, 20).boxed());

        show(rand.longs(50, 100).boxed());
        show(rand.doubles(20, 30).boxed());
        System.out.println("===================");

        //ints(long streamSize)方法：控制流大小
        //生成IntStream，表示2个int类型范围内的数据
        show(rand.ints(2).boxed());

        show(rand.longs(5).boxed());
        show(rand.doubles(8).boxed());
        System.out.println("===================");

        //ints(long streamSize, int randomNumberOrigin,int randomNumberBound)方法：控制流的大小和界限
        //生成IntStream，表示3个[3,9)范围内的数据
        show(rand.ints(3, 3, 9).boxed());

        show(rand.longs(3, 12, 22).boxed());
        show(rand.doubles(3, 11.5, 12.3).boxed());
    }

    public static <T> void show(Stream<T> stream) {
        stream.limit(4).forEach(System.out::println);
        System.out.println("--------------");
    }



    @Test
    public static void test4() throws Exception {
        //Stream.generate(Supplier s)方法：返回无限顺序无序流（Long.MAX_VALUE高达922亿个），其中每个元素由提供的供给型对象生成
        System.out.println(
                Stream.generate(new RandomWords("base/src/Cheese.dat"))
                        .limit(10)
                        .collect(Collectors.joining(" : ")));
    }

}


//文件 ---> Supplier供给型对象
class RandomWords implements Supplier<String> {
    List<String> words = new ArrayList<>();
    Random rand = new Random(47);

    RandomWords(String fname) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fname));
        // 略过第一行
        for (String line : lines.subList(1, lines.size())) {
            for (String word : line.split("[ .?,]+"))
                words.add(word.toLowerCase());
        }
    }

    //随机取一个单词
    public String get() {
        return words.get(rand.nextInt(words.size()));
    }

    @Override
    public String toString() {
        return words.stream()
                .collect(Collectors.joining(" "));
    }
}