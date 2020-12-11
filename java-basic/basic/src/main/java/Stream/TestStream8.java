package Stream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

/**
 * Created with IntelliJ IDEA.
 * Description: 流式编程 - 使用流
 * User: Defias
 * Date: 2019-07

 */

public class TestStream8 {
    public static void main(String[] args) throws IOException {
        //获取集合的流对象
        List<Person> list = new ArrayList<Person>();
        Stream<Person> stream = list.stream();
        Stream<Person> pstream = list.parallelStream();

        //获取数组的流对象
        String[] names = {"chaimm","peter","john","john1","john2","john3","john1"};
        Stream<String> stream2 = Arrays.stream(names);

        //直接获取序列的流对象
        Stream<String> stream3 = Stream.of("chaimm","peter","john");

        //重用流：把流保存起来以重用
        Supplier<Stream<String>> supplier = () -> Arrays.stream(names);

        //将流再转化成为集合
        List<String> list2 = supplier.get().collect(toList());

        //filter函数接收一个Lambda表达式作为参数，该表达式返回boolean，在执行过程中，流将元素逐一输送给filter，并筛选出执行结果为true的元素
        long count = supplier.get().filter(x -> x.startsWith("john")).count();
        System.out.println(count);

        //distinc 去重
        count = supplier.get().filter(x -> x.startsWith("john")).distinct().count();
        System.out.println(count);

        //limit 截取流的前N个元素
        count = supplier.get().filter(x -> x.startsWith("john"))
                .distinct()
                .limit(2)
                .count();
        System.out.println(count);

        //skip  跳过流的前N个元素
        count = supplier.get().filter(x -> x.startsWith("john"))
                .distinct()
                .skip(1)
                .count();
        System.out.println(count);
        System.out.println(" --------");

        //map
        supplier.get().filter(x -> x.startsWith("john"))
                .distinct()
                .map(x -> "test_" + x)
                .collect(toList())
                .forEach(n -> System.out.println(n));

        //flatMap  将一条一条的小流，汇聚成一条大流
        List<Integer> together = Stream.of(Arrays.asList(1,2),Arrays.asList(3,4))
                .flatMap(numbers -> numbers.stream())
                .collect(toList());
        together.forEach(n -> System.out.print(n+" "));
        System.out.println();

        //max和min
        List<Person> listp = Arrays.asList(new Person("xiaom", 12),
                new Person("xiaohong", 22),
                new Person("dayong", 31),
                new Person("ligang", 28));
        System.out.println(listp);

        //静态方法comparing，使用它可以方便的实现一个比较器，这个方法接受一个函数作为参数，并且返回另一个函数
        Person maxp = listp.stream()
                .max(Comparator.comparing(x -> x.getAge()))  //max()方法返回的是一个Optional对象
                .get();
        System.out.println(maxp);


        //reduce 归约
        //上述例子中用到的count，min，max方法，因为经常使用所以被纳入了标准库里，实际上，这些方法都是由reduce操作实现的
        //reduce函数接收两个参数：初始值、进行归约操作的Lambda表达式
        count = Stream.of(1,2,3,4,5,6)
                .reduce(0, (acc,element) -> acc + element); //acc参数代表当前的数值总和,element代表下一个元素
        System.out.println(count);
        System.out.println(" --------");

        //IO Stream
        final Path path = new File( "base/src/test.txt" ).toPath();
        try(Stream<String> lines =
                    Files.lines(path, StandardCharsets.UTF_8) ) {

            //onClose: 当Stream的close()方法被调用的时候被执行
            lines
                    .onClose(() -> System.out.println("Done!"))
                    .forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "name: " + this.name + " age: " + this.age;
    }
}
