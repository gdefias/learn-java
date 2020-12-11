package Collection;
import static Basic.Print.*;
import static Generics.element.Coffees.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by Defias on 2020/07.
 * Description: 数组与泛型
 *
 */


public class TestArray3 {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }

    public static void test1() {
        //数组与泛型不能很好的结合，不能实例化具有参数化类型的数组
        //! ClassParameter<Coffee>[] cofs = new ClassParameter<Coffee>[10];

        //可以参数化数组本身的类型
        Integer[] ints = { 1, 2, 3, 4, 5 };
        Double[] doubles = { 1.1, 2.2, 3.3, 4.4, 5.5 };

        Integer[] ints2 = new ClassParameter<Integer>().f(ints);
        Double[] doubles2 = new ClassParameter<Double>().f(doubles);

        ints2 = MethodParameter.f(ints);
        doubles2 = MethodParameter.f(doubles);
        System.out.println(Arrays.deepToString(ints2));
        System.out.println(Arrays.deepToString(doubles2));
    }

    public static void test2() {
        List<String>[] ls;  //泛型数组（具有参数化类型的数组）
        List[] la = new List[10];
        ls = (List<String>[])la; // "Unchecked" warning  将非泛型数组转型

        //Arrays.asList: 返回一个受指定数组支持的固定大小的列表
        ls[0] = new ArrayList<String>(Arrays.asList("abc", "dsd"));
        // Compile-time checking produces an error:
        //! ls[1] = new ArrayList<Integer>();
        ls[1] = new ArrayList<String>(Arrays.asList("123", "232"));
        System.out.println(Arrays.deepToString(ls));

        // The problem: List<String> is a subtype of Object
        Object[] objects = ls; // So assignment is OK
        // Compiles and runs without complaint:
        objects[1] = new ArrayList<Integer>();

        // However, if your needs are straightforward it is
        // possible to create an array of generics, albeit
        // with an "unchecked" warning:
        List<Coffee>[] spheres = (List<Coffee>[])new List[10];
        for(int i = 0; i < spheres.length; i++)
            spheres[i] = new ArrayList<Coffee>(Arrays.asList(new Coffee(), new Coffee()));

        System.out.println(Arrays.deepToString(spheres));
    }

    //填充数组
    //Arrays.fill：用同一个值填充各个位置，对对象而言就是复制同一个引用进行填充
    public static void test3() {
        int size = 6;
        boolean[] a1 = new boolean[size];
        byte[] a2 = new byte[size];
        char[] a3 = new char[size];
        short[] a4 = new short[size];
        int[] a5 = new int[size];
        long[] a6 = new long[size];
        float[] a7 = new float[size];
        double[] a8 = new double[size];
        String[] a9 = new String[size];

        Arrays.fill(a1, true);
        print("a1 = " + Arrays.toString(a1));
        Arrays.fill(a2, (byte)11);
        print("a2 = " + Arrays.toString(a2));
        Arrays.fill(a3, 'x');
        print("a3 = " + Arrays.toString(a3));
        Arrays.fill(a4, (short)17);
        print("a4 = " + Arrays.toString(a4));
        Arrays.fill(a5, 19);
        print("a5 = " + Arrays.toString(a5));
        Arrays.fill(a6, 23);
        print("a6 = " + Arrays.toString(a6));
        Arrays.fill(a7, 29);
        print("a7 = " + Arrays.toString(a7));
        Arrays.fill(a8, 47);
        print("a8 = " + Arrays.toString(a8));
        Arrays.fill(a9, "Hello");
        print("a9 = " + Arrays.toString(a9));
        // Manipulating ranges: 只填充数组的某个区域
        Arrays.fill(a9, 3, 5, "World");
        print("a9 = " + Arrays.toString(a9));
    }

    //复制数组：System.arraycopy(原数组、原数组的偏移量、目的数组、目的数组的偏移量、需要复制的元素个数)
    public static void test4() {
        int[] i = new int[7];
        int[] j = new int[10];
        Arrays.fill(i, 47);
        Arrays.fill(j, 99);
        print("i = " + Arrays.toString(i));
        print("j = " + Arrays.toString(j));
        System.arraycopy(i, 0, j, 0, i.length);
        print("j = " + Arrays.toString(j));

        int[] k = new int[5];
        Arrays.fill(k, 103);
        System.arraycopy(i, 0, k, 0, k.length);
        print("k = " + Arrays.toString(k));

        Arrays.fill(k, 103);
        System.arraycopy(k, 0, i, 0, k.length);
        print("i = " + Arrays.toString(i));

        // Objects:
        Integer[] u = new Integer[10];
        Integer[] v = new Integer[5];
        Arrays.fill(u, new Integer(47));
        Arrays.fill(v, new Integer(99));
        print("u = " + Arrays.toString(u));
        print("v = " + Arrays.toString(v));
        System.arraycopy(v, 0, u, u.length/2, v.length);
        print("u = " + Arrays.toString(u));
    }
}



class ClassParameter<T> {
    public T[] f(T[] arg) { return arg; }
}

class MethodParameter {
    public static <T> T[] f(T[] arg) { return arg; }
}