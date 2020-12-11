package Collection;
import static Generics.element.Coffees.*;
import java.util.Arrays;
import static Basic.Print.*;
/**
 * Created by Defias on 2016/2/27.
 *
 * 数组
 *
 Java中定义数组的语法有两种：(这两种形式没有区别，使用效果完全一样)
 type arrayName[];
 type[] arrayName;
 与C、C++不同，Java在定义数组时并不为数组元素分配内存，因此[ ]中无需指定数组元素的个数，即数组长度
 */

public class TestArray {
    public static void main(String[] args) {
        //test0();
        test1();
    }

    //基本类型数组
    public static void test0() {
        //定义数组
        //int[] a = new int[10];  //type[] 变量名 = new type[数组中元素的个数];

        //数组初始化
        int[] intArray = new int[] {1,2,3,34,54};
        intArray[0] = 111;
        System.out.println(intArray[0]);
        System.out.println(Arrays.toString(intArray));   //打印数组字符串

        String[] stringArray = {"asdf大师傅","水电费asdfs","一切编程语言都是纸老虎"};
        String str = "123";
        stringArray[0] = str;
        str = "456";
        stringArray[1] = str;
        System.out.println(stringArray[1]);

        System.out.println(Integer.MIN_VALUE);

        //基本类型（byte、char、int、float、double、boolean）的数组存在默认的初始值
//        byte[] A = new byte[10];
//        for (byte a:A) {
//            System.out.println(a);
//        }

        //动态初始化
//        float[] floatArray = new float[3];
//        floatArray[0] = 1.0f;
//        floatArray[1] = 2f;
//        floatArray[2] = 23.34f;
        float[] floatArray = new float[] {1.0f, 2f, 23.34f};

        System.out.println("len of floatArray is : " + floatArray.length );

        // 数组遍历
        for(int i=0,len=stringArray.length; i<len; i++) {  //方式1
            System.out.println(stringArray[i]);
        }

        for(String vars: stringArray) {  //方式2
            System.out.println(vars);
        }

        // 二维数组
        int intArray2[][] = { {1,2}, {2,3}, {3,4,5} };  //Java语言中，由于把二维数组看作是数组的数组，数组空间不是连续分配的，所以不要求二维数组每一维的大小相同
        int a[][] = new int[2][];
        a[0] = new int[3];
        a[1] = new int[5];

        // 输出
        for(int i=0; i<intArray2[2].length; i++){
            System.out.printf("intArray2[2][%d]：%d\n", i, intArray2[2][i]);
        }

        String strr = "10";
        String[] strr1;
        strr1 = new String[2];
        strr1[0] = strr.split("\\.")[0];
        System.out.println(strr1[0]);
    }

    //对象数组与基本类型数组几乎相同
    //唯一的区别是对象数组保存的是引用，基本类型数组保存的基本类型的值
    public static void test1() {
        // Arrays of objects:
        Coffee[] a; // Local uninitialized variable
        Coffee[] b = new Coffee[5];
        // The references inside the array are
        // automatically initialized to null:
        print("b: " + Arrays.toString(b));
        Coffee[] c = new Coffee[4];
        for(int i = 0; i < c.length; i++)
            if(c[i] == null) // Can test for null reference
                c[i] = new Coffee();
        // Aggregate initialization: 集聚初始化
        Coffee[] d = { new Coffee(),
                new Coffee(), new Coffee()
        };
        // Dynamic aggregate initialization: 动态集聚初始化
        a = new Coffee[] {new Coffee(), new Coffee(), };
        // (Trailing comma is optional in both cases)
        print("a.length = " + a.length);
        print("b.length = " + b.length);
        print("c.length = " + c.length);
        print("d.length = " + d.length);
        a = d;
        print("a.length = " + a.length);

        // Arrays of primitives:
        int[] e; // Null reference
        int[] f = new int[5];
        // The primitives inside the array are
        // automatically initialized to zero:
        print("f: " + Arrays.toString(f));
        int[] g = new int[4];
        for(int i = 0; i < g.length; i++)
            g[i] = i*i;
        int[] h = { 11, 47, 93 };
        // Compile error: variable e not initialized:
        //!print("e.length = " + e.length);
        print("f.length = " + f.length);
        print("g.length = " + g.length);
        print("h.length = " + h.length);
        e = h;
        print("e.length = " + e.length);
        e = new int[]{ 1, 2 };
        print("e.length = " + e.length);
    }
}
