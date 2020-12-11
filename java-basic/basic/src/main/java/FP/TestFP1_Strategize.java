package FP;

/**
 * Created by Defias on 2020/07.
 * Description:  函数式编程

 Java8的方法引用
 由::区分。在 :: 的左边是类或对象的名称，在 :: 的右边是方法的名称，但没有参数列表

 在Java 8之前，已经能够通过 [1] 和 [2] 的方式传递功能。然而，它的读写语法非常笨拙。方法引用 [4] 和 Lambda 表达式 [3]的出现让可以在
 需要时传递功能，而不是仅在必要才这么做

 */

public class TestFP1_Strategize {
    Strategy strategy;
    String msg;

    public static void main(String[] args) {
        Strategy[] strategies = {
                new Strategy() { // [2]
                    public String approach(String msg) {
                        return msg.toUpperCase() + "!";
                    }
                },
                msg -> msg.substring(0, 5), // [3]
                Unrelated::twice // [4]
        };

        TestFP1_Strategize s = new TestFP1_Strategize("Hello there");
        s.communicate();

        for(Strategy newStrategy : strategies) {
            s.changeStrategy(newStrategy);
            s.communicate();
        }
    }

    TestFP1_Strategize(String msg) {
        strategy = new Soft(); // [1]
        this.msg = msg;
    }

    void communicate() {
        System.out.println(strategy.approach(msg));
    }

    void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}


interface Strategy {
    String approach(String msg);
}

class Soft implements Strategy {
    public String approach(String msg) {
        return msg.toLowerCase() + "?";
    }
}

class Unrelated {
    static String twice(String msg) {
        return msg + " " + msg;
    }
}