package Stream;
import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: 流式编程与命令式编程
 */

public class TestStream1 {
    public static void main(String[] args) {
        test1();
        test2();
    }

    //流式编程（声明式/函数式）
    public static void test1() {
        new Random(47)
                .ints(5, 20)
                .distinct()
                .limit(7)
                .sorted()
                .forEach(System.out::println);
    }

    //命令式编程
    public static void test2() {
        Random rand = new Random(47);
        SortedSet<Integer> rints = new TreeSet<>();
        while(rints.size() < 7) {
            int r = rand.nextInt(20);
            if(r < 5) continue;
            rints.add(r);
        }
        System.out.println(rints);
    }
}
