package FP;
import java.util.function.*;
/**
 * Created by Defias on 2020/07.
 * Description: 高阶函数（Higher-order Function）

 高阶函数: 一个消费或产生函数的函数

 Function接口中名为andThen()的方法
 该方法专门用于操作函数。 顾名思义，在调用 in 函数之后调用 toThen()
 （还有 compose()，它在 in 函数之前应用新函数）
 */
public class TestFP6_Function4 {
    public static void main(String[] args) {
//        testProduce();
//        testConsume();
        testTransform();

    }

    public static void testProduce() {
        FuncSS f = ProduceFunction.produce();
        System.out.println(f.apply("YELLING"));
    }

    public static void testConsume() {
        Two two = ConsumeFunction.consume(one -> new Two());
    }

    public static void testTransform() {
        Function<I,O> f2 = TransformFunction.transform(i -> {
            System.out.println(i);
            System.out.println("---");
            return new O();
        });
        O o = f2.apply(new I());
    }

}


//使用继承，可以轻松地为你的专用接口创建别名
interface FuncSS extends Function<String, String> {}

class ProduceFunction {
    static FuncSS produce() {  //produce()是高阶函数
        return s -> s.toLowerCase(); //使用Lambda表达式，在方法中轻松创建和返回一个函数
    }
}


class One {}
class Two {}

class ConsumeFunction {
    static Two consume(Function<One,Two> onetwo) {
        return onetwo.apply(new One());
    }
}



class I {
    @Override
    public String toString() {
        return "I";
    }
}

class O {
    @Override
    public String toString() {
        return "O";
    }
}

//根据消费函数生成新函数
class TransformFunction {
    //transform()生成一个与传入的函数具有相同签名的函数，但是可以生成任何你想要的类型
    static Function<I,O> transform(Function<I,O> in) {
        return in.andThen(o -> {
            System.out.println(o);
            return o;
        });
    }
}