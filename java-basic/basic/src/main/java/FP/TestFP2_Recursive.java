package FP;

/**
 * Created by Defias on 2020/07.
 * Description: 递归

 递归函数是一个自我调用的函数。可以编写递归的 Lambda 表达式，但需要注意：递归方法必须是实例变量或静态变量，否则会出现编译时错误
 */

public class TestFP2_Recursive {
    public static void main(String[] args) {
        RecursiveFactorial.main(args);
        //RecursiveFibonacci.main(args);
    }

}

//阶乘
class RecursiveFactorial {
    static IntCall fact;  //静态变量

    public static void main(String[] args) {
        fact = n -> n == 0 ? 1 : n * fact.call(n - 1);

        for(int i = 0; i <= 10; i++)
            System.out.println(fact.call(i));
    }
}


//斐波拉契数列
class RecursiveFibonacci {
    IntCall fib;  //实例变量

    RecursiveFibonacci() {
        fib = n -> n == 0 ? 0 :
                n == 1 ? 1 :
                        fib.call(n - 1) + fib.call(n - 2);
    }

    int fibonacci(int n) {
        return fib.call(n);
    }

    public static void main(String[] args) {
        RecursiveFibonacci rf = new RecursiveFibonacci();
        for(int i = 0; i <= 10; i++)
            System.out.println(rf.fibonacci(i));
    }
}


interface IntCall {
    int call(int arg);
}