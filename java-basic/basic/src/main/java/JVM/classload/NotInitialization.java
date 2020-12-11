package JVM.classload;
/**
 * Created by Defias on 2020/09.
 * Description:  被动引用例子
 *
 */

public class NotInitialization {
    public static void main(String[]args) {
        //test1();
        //test2();
        test3();
    }

    /**
     * 被动使用类字段演示一：
     * 通过子类引用父类的静态字段, 不会导致子类初始化
     **/
    public static void test1() {
        System.out.println(SubClass.value);
    }


    /**
     * 被动使用类字段演示二：
     * 通过数组定义来引用类, 不会触发此类的初始化
     **/
    public static void test2() {
        SuperClass[] sca = new SuperClass[10];
    }


    /**
     * 被动使用类字段演示三：
     * 常量在编译阶段会存入调用类的常量池中, 本质上并没有直接引用到定义常量的类, 因此不会触发定义常量的类的初始化
     *
     * 详解
     * 虽然引用了ConstClass类中的常量HELLOWORLD，但其实在编译阶段通过常量传播优化，已经将此常量的值"hello world"存储到了
     * NotInitialization类的常量池中，以后NotInitialization对常量ConstClass.HELLOWORLD的引用实际都被转化为NotInitialization类
     * 对自身常量池的引用了。也就是说，实际上NotInitialization的Class文件之中并没有ConstClass类的符号引用入口，这两个类在编译成
     * Class之后就不存在任何联系了
     **/
    public static void test3() {
        System.out.println(ConstClass.HELLOWORLD);
    }
}


class SuperClass{
    static {
        System.out.println("SuperClass init!");
    }
    public static int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}

class ConstClass {
    static{
        System.out.println("ConstClass init!");
    }
    public static final String HELLOWORLD = "hello world";
}