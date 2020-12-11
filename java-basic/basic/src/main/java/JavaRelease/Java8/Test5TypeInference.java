package JavaRelease.Java8;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-07
 *
 * 更好的类型推断
 */

class Value<T> {
    public static<T> T defaultValue() {
        return null;
    }

    public T getOrDefault( T value, T defaultValue ) {
        return ( value != null ) ? value : defaultValue;
    }
}

public class Test5TypeInference {
    public static void main(String[] args) {
        final Value<String> value = new Value<>();

        //参数Value.defaultValue()的类型被编译器推断出来，不需要显式地提供类型
        //在java 7, 相同的代码不会被编译，需要写成：Value.<String>defaultValue()
        String vv= value.getOrDefault("22", Value.defaultValue());
        System.out.println(vv);
    }
}
