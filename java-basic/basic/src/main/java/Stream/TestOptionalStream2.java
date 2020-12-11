package Stream;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class TestOptionalStream2 {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        // If Optional is not empty, map() first extracts
        // the contents which it then passes
        // to the function:
        OptionalMap.test("Add brackets", s -> "[" + s + "]");
        OptionalMap.test("Increment", s -> {
            try {
                return Integer.parseInt(s) + 1 + "";
            } catch (NumberFormatException e) {
                return s;
            }
        });
        OptionalMap.test("Replace", s -> s.replace("2", "9"));
        OptionalMap.test("Take last digit", s -> s.length() > 0 ?
                s.charAt(s.length() - 1) + "" : s);
        System.out.println();
    }
    // After the function is finished, map() wraps the
    // result in an Optional before returning it
}


class OptionalMap {
    static String[] elements = {"12", "", "23", "45"};

    static Stream<String> testStream() {
        return Arrays.stream(elements);
    }

    static void test(String descr, Function<String, String> func) {
        System.out.println(" ---( " + descr + " )---");
        for (int i = 0; i <= elements.length; i++) {
            System.out.println(
                    testStream()
                            .skip(i)
                            .findFirst() // Produces an Optional
                            .map(func));
        }
    }
}