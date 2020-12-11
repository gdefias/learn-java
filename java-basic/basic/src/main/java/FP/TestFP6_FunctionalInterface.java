package FP;

/**
 * Created by Defias on 2020/07.
 * Description: Lambda表达式 - 函数接口 - FunctionalInterface注解
 *
 */

public class TestFP6_FunctionalInterface {

    public String goodbye(String arg) {
        return "Goodbye, " + arg;
    }

    public static void main(String[] args) {
        TestFP6_FunctionalInterface fa = new TestFP6_FunctionalInterface();
        Functional f = fa::goodbye;
        FunctionalNoAnn fna = fa::goodbye;

        // Functional fac = fa; // Incompatible
        Functional fl = a -> "Goodbye, " + a;
        FunctionalNoAnn fnal = a -> "Goodbye, " + a;
    }
}


//@FunctionalInterface 注解是可选的
@FunctionalInterface
interface Functional {
    String goodbye(String arg);
}

interface FunctionalNoAnn {
    String goodbye(String arg);
}

/*
@FunctionalInterface
interface NotFunctional {
  String goodbye(String arg);
  String hello(String arg);
}
产生错误信息:
NotFunctional is not a functional interface
multiple non-overriding abstract methods
found in interface NotFunctional
*/