package Basic;

/**
 * Created by Defias on 2017/12/11.
 *
 * 静态内部类 - 嵌套类
 * 不需要内部类对象与外部类对象之间有联系
 *
 * 理解静态内部类：
 * - 只有将某个内部类修饰为静态类，然后才能够在这个类中定义静态的成员变量与成员方法
 * - 非静态内部类的特权：非静态内部类可以随意的访问外部类中的成员变量与成员方法，即使这些成员方法被修饰为private(私有的成员变量或者方法)
 * - 不能够从静态内部类的对象中访问外部类的非静态成员(包括成员变量与成员方法)，在静态内部类中，无论在成员方法内部还是在其他地方都只能够引用外部类中的静态的变量，而不能够访问非静态的变量
 * - 创建静态内部类时不需要将静态内部类的实例绑定在外部类的实例上
 */

public class JavaInnerC3 {

    public static class innerClass{   //静态内部类
        public innerClass(){
            System.out.println("innerClass");
        }
    }

    public static void main(String[] args)
    {
        //outClass a = new JavaOuterC2();   //可以不创建外部类
        innerClass b = new innerClass();    //直接创建内部类
        innerClass c = new innerClass();
    }
}
