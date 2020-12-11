package Stream;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class TestOptionalStream {

    static void test(Optional<String> optString) {
        if(optString.isPresent())
            System.out.println(optString.get());
        else
            System.out.println("Nothing inside!");
    }

    //对空流进行操作
    //当流为空的时候会获得一个Optional.empty对象，而不是抛出异常
    public static void test1() {
        System.out.println(Stream.empty().findFirst());

        System.out.println(Stream.<String>empty().findAny());

        System.out.println(Stream.<String>empty().max(String.CASE_INSENSITIVE_ORDER));

        System.out.println(Stream.<String>empty().min(String.CASE_INSENSITIVE_ORDER));

        System.out.println(Stream.<String>empty().reduce((s1, s2) -> s1 + s2));

        System.out.println(IntStream.empty().average());
        System.out.println();
    }

    public static void test2() {
        test(Stream.of("Epithets").findFirst());
        test(Stream.<String>empty().findFirst());
        System.out.println();
    }

    public static void test3() {
        Optionals.test("basics", Optionals::basics);
        Optionals.test("ifPresent", Optionals::ifPresent);
        Optionals.test("orElse", Optionals::orElse);
        Optionals.test("orElseGet", Optionals::orElseGet);
        Optionals.test("orElseThrow", Optionals::orElseThrow);
        System.out.println();
    }


}


// Optionals提供便利函数   ---预定义了简单的所需的函数接口
// ifPresent(Consumer)：当值不为null时调 Consumer，否则什么也不做
// orElse(otherObject)：如果值不为null则直接返回，否则生成otherObject
// orElseGet(Supplier)：如果值不为null直接生成对象，否则使用Supplier函数生成一个可替代对象
// orElseThrow(Supplier)：如果值不为null直接生成对象，否则使用Supplier函数生成一个异常
class Optionals {
    static void basics(Optional<String> optString) {
        if(optString.isPresent())
            System.out.println(optString.get());
        else
            System.out.println("Nothing inside!");
    }

    static void ifPresent(Optional<String> optString) {
        optString.ifPresent(System.out::println);
    }

    static void orElse(Optional<String> optString) {
        System.out.println(optString.orElse("Nada"));
    }

    static void orElseGet(Optional<String> optString) {
        System.out.println(optString.orElseGet(() -> "Generated"));
    }

    static void orElseThrow(Optional<String> optString) {
        try {
            System.out.println(optString.orElseThrow(() -> new Exception("Supplied")));
        } catch(Exception e) {
            System.out.println("Caught " + e);
        }
    }

    static void test(String testName, Consumer<Optional<String>> cos) {
        System.out.println(" === " + testName + " === ");
        cos.accept(Stream.of("Epithets").findFirst());
        cos.accept(Stream.<String>empty().findFirst());
    }
}