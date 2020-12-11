package TypeInfo;
import java.util.Random;
/**
 * Created by Defias on 2020/07.
 * Description: 对象的初始化时机
 *
 */

public class TestClass3 {
    public static Random rand = new Random(47);

    public static void main(String[] args) throws Exception {

        //当使用.class来创建对Class对象的引用时，不会自动初始化Initable类对象
        Class initable = Initable.class;
        System.out.println("----------------------");

        //因为staticFinal是编译期常量，无需初始化Initable对象就可以读取
        int a = Initable.staticFinal;
        System.out.println("----------------------");

        //因为staticFinal2不是编译期常量，需要初始化Initable对象完后才能读取
        int b = Initable.staticFinal2;
        System.out.println("----------------------");


        //Initable2不是通过.class创建的Class对象，执行该行会初始化Initable2对象
        int c = Initable2.staticNonFinal;
        System.out.println("----------------------");

        //执行该行会初始化Initable3对象
        Class initable3 = Class.forName("TypeInfo.Initable3");
        int d = Initable3.staticNonFinal;
    }
}

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = TestClass3.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticNonFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}