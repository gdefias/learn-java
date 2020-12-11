package JVM.bytecoderun;

/**
 * Created by Defias on 2020/09.
 * Description:  方法的重载
 *
 * 静态分派  根据变量的静态类型分派   编译器由编辑器执行而不由java虚拟机执行
 */
public class JavaOverloadAssign {

    // 测试代码
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        JavaOverloadAssign test = new JavaOverloadAssign();

        //是根据变量（man、woman）的静态类型（Human）确定重载sayHello()中参数为Human guy的方法，即sayHello(Human guy)
        test.sayHello(man);
        test.sayHello(woman);

        //强制类型转换 改变 变量的静态类型
        test.sayHello((Man)man);


    }

    // 类定义
    static abstract class Human {
    }

    // 继承自抽象类Human
    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    // 可供重载的方法
    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello lady!");
    }

}
