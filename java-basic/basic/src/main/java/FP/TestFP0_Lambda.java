package FP;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by Defias on 2017/8/24.

 Lambda表达式

 */
public class TestFP0_Lambda {

    public static void main(String[] args) {
//        test0();
//        test1();
//        test2();
//        test3();
        test4();
    }

    public static void test0() {
        //lambda表达式
        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));

        //编译器会根据上下文来推测参数的类型，或者也可以显示地指定参数类型，只需要将类型包在括号里
        //相当于：
        Consumer<String> consumer =  e -> System.out.println(e);
        Arrays.asList("a", "b", "d").forEach(consumer);

        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.println(e));

        Arrays.asList("a", "b", "d").forEach((String e) -> {
            System.out.println(e);
        });

        //Lambda表达式可能会引用类的成员或者局部变量（会被隐式地转变成final类型）
        //lambda表达式的局部变量可以不用声明为final，但是必须不可被后面的代码修改（即隐性的具有final的语义）
        String separator = ",";
        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.print(e + separator));

        //Lambda表达式可能会有返回值，编译器会根据上下文推断返回值的类型。如果lambda的语句块只有一行，不需要return关键字
        Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));
        //相当于：
        Arrays.asList("a", "b", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });

        //函数接口隐式地转换成lambda表达式
        Runnable r = () -> System.out.println("hello world");
    }


    //Consumer接口
    public static void test1() {
        Consumer<String> printString = s -> System.out.println(s);
        printString.accept("helloWorld!");
    }

    //Supplier接口
    public static void test2() {
        Supplier<String> getInstance = () -> "HelloWorld!";
        System.out.println(getInstance.get());
    }


    //Predicate接口
    public static void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("输出所有数据:");
        eval(list, n->true);

        System.out.println("输出所有偶数:");
        eval(list, n-> n%2 == 0 );

        System.out.println("输出大于3的所有数字:");
        eval(list, n-> n > 3 );
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list) {
            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }


    //Fcuntion接口
    public static void test4() {
        int x = 1;
        int y = oper(x, m -> addOne(m));
        System.out.printf("x= %d, y = %d", x, y);

        //也可以使用lambda表达式来表示这段行为,只要保证一个参数,一个返回值就能匹配
        y = oper(x, m -> m + 3 );
        y = oper(x, m -> m * 3 );
    }

    //下面这个方法接受一个int类型参数a,返回a+1,符合Function接口的定义
    public static final int addOne(int a){
        return a+1;
    }

    public static int oper(int a, Function<Integer,Integer> action){
        return action.apply(a);
    }

}


