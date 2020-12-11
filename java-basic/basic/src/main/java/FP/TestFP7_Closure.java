package FP;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
/**
 * Created by Defias on 2020/07.
 * Description:  闭包（Closure）

 利用闭包可以轻松生成函数

 一个更复杂的Lambda，它使用函数作用域之外的变量。 返回该函数

 */
public class TestFP7_Closure {
    public static void main(String[] args) {
        testSharedStorage();
        testClosure3();
    }

    //为i共享相同的存储空间
    public static void testSharedStorage() {
        //Closure1 c1 = new Closure1();
        Closure2 c1 = new Closure2();
        //AnonymousClosure c1= new AnonymousClosure();
        IntSupplier f1 = c1.makeFun(0);
        IntSupplier f2 = c1.makeFun(0);
        IntSupplier f3 = c1.makeFun(0);
        System.out.println(f1.getAsInt());
        System.out.println(f2.getAsInt());
        System.out.println(f3.getAsInt());
        System.out.println("---------");
    }

    public static void testClosure3() {
        Closure3 c3 = new Closure3();
        List<Integer>
                l1 = c3.makeFun().get(),
                l2 = c3.makeFun().get();
        System.out.println(l1);
        System.out.println(l2);
        l1.add(42);
        l2.add(96);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println("---------");
    }
}




class Closure1 {
    int i;  //i是成员变量
    IntSupplier makeFun(int x) {
        return () -> x + i++;
    }
}


class Closure2 {
    IntSupplier makeFun(int x) {
        int i = 0;  //final int i = 0;
        //! i = i + 1; //编译器非常智能它能识别变量i的值正在被更改 编译错误
        //! return () -> x++ + i++;  //若递增i会产生编译时错误
        return () -> x + i;
    }
}

//每次调用makeFun()时，其实都会创建并返回一个全新的ArrayList
//也就是说，每个闭包都有自己独立的 ArrayList 他们不能互相干扰和共享
class Closure3 {
    Supplier<List<Integer>> makeFun() {
        final List<Integer> ai = new ArrayList<>();
        ai.add(1);
        return () -> ai;
    }
}


//作为闭包的内部类
class AnonymousClosure {
    IntSupplier makeFun(int x) {
        int i = 0;
        // 同样规则的应用:
        // i++; // 非等同 final 效果
        // x++; // 同上
        return new IntSupplier() {
            public int getAsInt() { return x + i; }
        };
    }
}