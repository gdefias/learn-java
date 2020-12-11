package TypeInfo;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: instanceof/isInstance 与 ==/equals
 *
 * instanceof和isInstance生成的结果一样，指的是：你是这个类吗？ 或者你是这个类的派生类吗？
 * 而==/equals是没有考虑继承的，比较实际的Class对象
 */

public class TestClass5 {
    public static void main(String[] args) {

        //test0();
        test1();
    }

    public static void test0() {
        Base b1 = new Base();
        Base b2 = new Derived();
        Derived b3 = new Derived();

        Class<? extends Base> derivedtype = Derived.class;

        //isInstance： 判定指定的参数对象b是否与此Class所表示的对象赋值兼容
        //（能够在不引发 ClassCastException 的情况下被强制转换成该Class对象所表示的引用类型）
        System.out.println(derivedtype.isInstance(b1));  //父类对象不一定能转换成子类
        System.out.println(derivedtype.isInstance(b2));

        //子类对象肯定能转换成父类
        Class<? super Derived> basetype = Base.class;
        System.out.println(basetype.isInstance(b2));
        System.out.println(basetype.isInstance(b3));
    }


    public static void test1() {
        //test(new Base());
        test(new Derived());
    }

    public static void test(Object x) {
        print("Testing x of type " + x.getClass());
        print("x instanceof Base " + (x instanceof Base));
        print("x instanceof Derived "+ (x instanceof Derived));
        print("Base.isInstance(x) "+ Base.class.isInstance(x));
        print("Derived.isInstance(x) " + Derived.class.isInstance(x));

        print("x.getClass() == Base.class " + (x.getClass() == Base.class));
        print("x.getClass() == Derived.class " + (x.getClass() == Derived.class));
        print("x.getClass().equals(Base.class)) "+ (x.getClass().equals(Base.class)));
        print("x.getClass().equals(Derived.class)) " + (x.getClass().equals(Derived.class)));
    }
}


class Base {}
class Derived extends Base {}