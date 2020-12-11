package Stream;
import java.util.*;
import java.util.function.*;
/**
 * Created by Defias on 2020/07.
 * Description: Optional容器

 */
public class TestOptional1 {
    public static void main(String[] args) {
        test1();
    }

    static void test(String testName, Optional<String> opt) {
        System.out.println(" === " + testName + " === ");
        System.out.println(opt.orElse("Null"));
    }

    public static void test1() {
        test("empty", Optional.empty());
        test("of", Optional.of("Howdy"));
        try {
            test("of", Optional.of(null));  //对于of方法来说，传入null会抛异常
        } catch(Exception e) {
            System.out.println(e);
        }
        test("ofNullable", Optional.ofNullable("Hi"));
        test("ofNullable", Optional.ofNullable(null));
        System.out.println();
    }


}





