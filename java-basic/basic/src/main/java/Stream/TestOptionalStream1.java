package Stream;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TestOptionalStream1 {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        OptionalFilter.test("true", str -> true);
        OptionalFilter.test("false", str -> false);
        OptionalFilter.test("str != \"\"", str -> str != "");
        OptionalFilter.test("str.length() == 3", str -> str.length() == 3);
        OptionalFilter.test("startsWith(\"B\")", str -> str.startsWith("B"));
        System.out.println();
    }
}


class OptionalFilter {
    static String[] elements = {"Foo", "", "Bar", "Baz", "Bingo"};

    static Stream<String> testStream() {
        return Arrays.stream(elements);
    }

    static void test(String descr, Predicate<String> pred) {
        System.out.println(" ---( " + descr + " )---");
        for(int i = 0; i <= elements.length; i++) {
            System.out.println(testStream().skip(i).findFirst().filter(pred));
        }
    }
}