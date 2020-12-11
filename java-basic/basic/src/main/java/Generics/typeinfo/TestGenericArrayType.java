package Generics.typeinfo;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: GenericArrayType接口
 * User: Defias
 * Date: 2018-05
 */

public class TestGenericArrayType <T> {
    public TestGenericArrayType() {
    }

    public static void main(String[] args) throws Exception {
        Method[] method = Test.class.getDeclaredMethods();
        System.out.println(method.length);
        System.out.println(method[0]);

        Type[] types = method[0].getGenericParameterTypes();
        for (Type type : types) {
            System.out.println(type);
            System.out.println(type instanceof GenericArrayType);
        }
    }
}


class Test<T> {
    /*
     * List<String>[]的组成元素List<String>是：ParameterizedType类型 true
     * T[]的组成元素T是：TypeVariable类型 true
     * List<String>不是数组 false
     * String[]的组成元素String是：普通对象, 没有泛型  false
     * int[]的组成元素int是：原生类型, 也没有泛型 false
     * */
    public void show(List<String>[] pTypeArray, T[] vTypeArray, List<String> list, String[] strings, int[] ints) {
    }
}
