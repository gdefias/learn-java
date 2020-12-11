package Collection.generator;

import static Basic.Print.*;

import java.util.Arrays;

/**
 * Created by Defias on 2020/07.
 * Description: 从Generator中创建数组
 *
 */

public class TestArrayFromGenerator {
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    public static void test1() {
        Integer[] a = { 9, 8, 7, 6 };
        System.out.println(Arrays.toString(a));

        a = Generated.array(a,new CountingGenerator.Integer());
        System.out.println(Arrays.toString(a));

        Integer[] b = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);
        System.out.println(Arrays.toString(b));
    }

    public static void test2() {
        Integer[] a = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);
        int[] b = ConvertTo.primitive(a);
        System.out.println(Arrays.toString(b));

        boolean[] c = ConvertTo.primitive(Generated.array(Boolean.class, new CountingGenerator.Boolean(), 7));
        System.out.println(Arrays.toString(c));
    }


    //用RandomGenerator中类测试数组生成工具
    public static void test3() {
        int size = 6;
        boolean[] a1 = ConvertTo.primitive(Generated.array(
                Boolean.class, new RandomGenerator.Boolean(), size));
        print("a1 = " + Arrays.toString(a1));

        byte[] a2 = ConvertTo.primitive(Generated.array(
                Byte.class, new RandomGenerator.Byte(), size));
        print("a2 = " + Arrays.toString(a2));

        char[] a3 = ConvertTo.primitive(Generated.array(
                Character.class,
                new RandomGenerator.Character(), size));
        print("a3 = " + Arrays.toString(a3));

        short[] a4 = ConvertTo.primitive(Generated.array(
                Short.class, new RandomGenerator.Short(), size));
        print("a4 = " + Arrays.toString(a4));

        int[] a5 = ConvertTo.primitive(Generated.array(
                Integer.class, new RandomGenerator.Integer(), size));
        print("a5 = " + Arrays.toString(a5));

        long[] a6 = ConvertTo.primitive(Generated.array(
                Long.class, new RandomGenerator.Long(), size));
        print("a6 = " + Arrays.toString(a6));

        float[] a7 = ConvertTo.primitive(Generated.array(
                Float.class, new RandomGenerator.Float(), size));
        print("a7 = " + Arrays.toString(a7));

        double[] a8 = ConvertTo.primitive(Generated.array(
                Double.class, new RandomGenerator.Double(), size));
        print("a8 = " + Arrays.toString(a8));
    }

}



