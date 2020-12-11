package Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static Generics.element.Coffees.*;

/**
 * Created by Defias on 2017/8/16.
 *
 * 多维数组
 */

public class TestArray2 {

    public static void main(String[] args){
        //test0();
        test1();
        //test2();
    }

    //基本类型多维数组
    public static void test0() {
        //定义二维数组
        int[][] arr= new int[][] {{1,2,3},{4,5,6}};
        //或： int[][] arr= {{1,2,3},{4,5,6}};

        //打印出二维数组
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[i].length;j++) {
                System.out.print(arr[i][j]+" ");
            }
            //输出一行后就回车空格
            System.out.println();
        }
        System.out.println("--------------------");


        //定义二维锯齿数组
        int[][] arr2 = new int[][] {{1,2}, {4,5,6}, {7,8,9,10}};
        //或： int[][] arr = {{1,2}, {4,5,6}, {7,8,9,10}};
        for(int i=0; i<arr2.length; i++) {
            for(int j=0; j<arr2[i].length; j++) {
                System.out.print(arr2[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(Arrays.deepToString(arr2));  //Arrays.deepToString：将多维数组转换为String
        System.out.println("--------------------");


        //分开定义二维数组：存放一维数组的一维数组
        int[][] arr3 = new int[2][];
        arr3[0] = new int[2];
        arr3[1] = new int[3];

        //赋值
        arr3[0][0] = 4;
        arr3[0][1] = 5;

        arr3[1][0] = 3;
        arr3[1][1] = 6;
        arr3[1][2] = 9;

        System.out.println(Arrays.deepToString(arr3));
        System.out.println("--------------------");

        //三维数组：存放二维数组的一维数组
        int[][][] arr4 = new int[2][][];
        arr4[0] = new int[][] {{1,2}, {4,5,6}, {7,8,9,10}};
        arr4[1] = new int[][] {{1,2}, {4,5,6}, {7,8,9,10}};

        System.out.println(Arrays.deepToString(arr4));
        System.out.println("--------------------");

        //数组中构成矩阵的每个向量都可以具有任意的长度 --粗糙数组
        Random rand = new Random(47);
        // 3-D array with varied-length vectors:
        int[][][] a = new int[rand.nextInt(7)][][];
        for(int i = 0; i < a.length; i++) {
            a[i] = new int[rand.nextInt(5)][];
            for(int j = 0; j < a[i].length; j++)
                a[i][j] = new int[rand.nextInt(5)];
        }
        System.out.println(Arrays.deepToString(a));
    }

    //非基本类型多维对象数组
    public static void test1() {
        Coffee[][] spheres = {
                { new Coffee(), new Coffee() },
                { new Coffee(), new Coffee(),
                        new Coffee(), new Coffee() },
                { new Coffee(), new Coffee(),
                        new Coffee(), new Coffee(),
                        new Coffee(), new Coffee(),
                        new Coffee(), new Coffee() },
        };
        System.out.println(Arrays.deepToString(spheres));
        System.out.println("--------------------");

        Integer[][] a;
        a = new Integer[3][];
        for(int i = 0; i < a.length; i++) {
            a[i] = new Integer[3];
            for(int j = 0; j < a[i].length; j++)
                a[i][j] = i * j; // Autoboxing
        }
        System.out.println(Arrays.deepToString(a));
        System.out.println("--------------------");

        Integer[][] a1 = { // Autoboxing
                { 1, 2, 3, },
                { 4, 5, 6, },
        };

        Double[][][] a2 = { // Autoboxing
                { { 1.1, 2.2 }, { 3.3, 4.4 } },
                { { 5.5, 6.6 }, { 7.7, 8.8 } },
                { { 9.9, 1.2 }, { 2.3, 3.4 } },
        };

        String[][] a3 = {
                { "The", "Quick", "Sly", "Fox" },
                { "Jumped", "Over" },
                { "The", "Lazy", "Brown", "Dog", "and", "friend" },
        };

        System.out.println("a1: " + Arrays.deepToString(a1));
        System.out.println("a2: " + Arrays.deepToString(a2));
        System.out.println("a3: " + Arrays.deepToString(a3));
        System.out.println("--------------------");

    }


    public static void test2() {
        List<List<Integer>>[][] dp = new ArrayList[5][4];

        List<List<Integer>> item = new ArrayList<>();
        List<Integer> i = new ArrayList<>();
        i.add(2);
        i.add(3);
        item.add(i);
        dp[0][0] = item;

        System.out.println(dp[0][0]);
    }


}
