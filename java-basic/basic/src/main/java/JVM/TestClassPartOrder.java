package JVM;

/**
 * Created by Defias on 2016/2/27.

 基本运行顺序

 父类静态成员变量
 父类静态块
 子类静态成员变量
 子类静态块
 父类普通成员变量
 父类普通块
 父类构造器
 子类普通成员变量
 子类普通块
 子类构造器
 *
 */
public class TestClassPartOrder extends Father {
    public String value="son1";
    public static String vsc = "son";

    public static void  main(String[] args){
        TestClassPartOrder ro = new TestClassPartOrder();
        //ro.say();
    }

    public TestClassPartOrder() {
        System.out.println("子类构造器中value--->"+value);
        value="son3";
        System.out.println("子类构造器中赋值后的value--->"+value);
    }

    static {
        System.out.println("子类的静态块vcd---->" + vsc);
    }

    public void say() {
        System.out.println("say方法中value---->"+value);
    }

    {
        System.out.println("子类块中value--->"+value);
        value="son2";
        System.out.println("子类块中赋值后的value--->"+value);
    }
}


class Father {
    public String value="father1";
    public static String vsc = "fu";
    
    public Father() {
        System.out.println("父类构造器中赋值前的value-->"+value);
        value = "father3";
        System.out.println("父类构造器中赋值后的value--->"+value);
    }

    static {
        System.out.println("父类的静态块vcd---->" + vsc);
    }

    {
        System.out.println("父类块中value--->"+value);
        value="father2";
        System.out.println("父类块中赋值后的value--->"+value);
    }
}


