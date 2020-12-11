package FP;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 函数式编程 - 方法引用
 *
 方法引用组成：类名或对象名，后面跟 :: 然后跟方法名称
 */

public class TestFP3_MethodReferences {
    static void hello(String name) { // [3]  hello()也符合call()的签名
        System.out.println("Hello, " + name);
    }

    static class Description {
        String about;

        Description(String desc) {
            about = desc;
        }

        void help(String msg) { // [4]  help()也符合call()的签名
            System.out.println(about + " " + msg);
        }
    }

    static class Helper {
        static void assist(String msg) { // [5]   assist()也符合call()的签名
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        Describe d = new Describe();
        Callable c = d::show; // [6] 方法引用
        c.call("call()"); // [7] call()映射到show()
        c = TestFP3_MethodReferences::hello; // [8] 方法引用
        c.call("Bob");
        c = new Description("valuable")::help; // [9] 方法引用
        c.call("information");
        c = Helper::assist; // [10] 方法引用
        c.call("Help!");
    }
}


interface Callable { // [1]
        void call(String s);
}

class Describe {
    void show(String msg) { // [2]  show()的签名（参数类型和返回类型）符合 Callable 的 call() 的签名
        System.out.println(msg);
    }
}