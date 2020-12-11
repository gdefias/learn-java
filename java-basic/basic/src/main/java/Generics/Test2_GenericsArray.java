package Generics;
import Generics.element_generics.Pair;
import org.testng.annotations.Test;
import java.util.Arrays;
/**
 泛型数组

 * */

public class Test2_GenericsArray {

    @Test
    public static void test1() {
        Pair[] arr = new Pair[2];
        Pair<String>[] ps = (Pair<String>[]) arr;

        ps[0] = new Pair<String>("a", "b");
        //ps[0] = new Pair<Integer>(1, 2);   //compile error!  编译器可以强制检查变量ps，因为它的类型是泛型数组
        arr[1] = new Pair<Integer>(1, 2);  //编译器不会检查变量arr，因为它不是泛型数组

        //compile error! ClassCastException
        Pair<String> p = ps[1];
        String s = p.getFirst();
    }

    //利用可变参数创建泛型数组T[]是危险的！
    @Test
    public static void test2() {
        String[] arr = asArray("one", "two", "three");
        System.out.println(Arrays.toString(arr));

        //String[] firstTwo = pickTwo("one", "two", "three");  //ClassCastException
        //因为擦除法，在pickTwo()方法内部，编译器无法检测K[]的正确类型，因此返回了Object[]
        Object[] firstTwo = pickTwo("one", "two", "three");

        System.out.println(Arrays.toString(firstTwo));
    }

    static <K> K[] pickTwo(K k1, K k2, K k3) {
        return asArray(k1, k2);
    }

    static <T> T[] asArray(T... objs) {
        return objs;
    }

}
