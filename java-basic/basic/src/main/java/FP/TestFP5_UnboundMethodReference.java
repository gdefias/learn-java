package FP;

/**
 * Created by Defias on 2020/07.
 * Description: 未绑定方法引用

 未绑定的方法引用是指没有关联对象的普通（非静态）方法。 使用未绑定的引用之前，我们必须先提供对象

 使用未绑定的引用时，函数方法的签名（接口中的单个方法）不再与方法引用的签名完全匹配。 因为需要一个对象参数来调用方法
 */

public class TestFP5_UnboundMethodReference {
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    public static void test1() {
        //即使MakeString的make()与f()具有相同的签名，编译也会报“invalid method reference”（无效方法引用）错误
        //因为实际上还有另一个隐藏的参数：老朋友this。 不能在没有X对象的前提下调用f()
        //X::f表示未绑定的方法引用，因为它尚未“绑定”到对象
        //MakeString ms = X::f; // [1]
        TransformX sp = X::f;
        X x = new X();
        System.out.println(sp.transform(x)); // [2]
        System.out.println(x.f()); // 同等效果
    }

    //未绑定的方法与多参数的结合运用
    public static void test2() {
        TwoArgs twoargs = DiyThis::two;
        ThreeArgs threeargs = DiyThis::three;
        FourArgs fourargs = DiyThis::four;
        DiyThis athis = new DiyThis();
        twoargs.call2(athis, 11, 3.14);
        threeargs.call3(athis, 11, 3.14, "Three");
        fourargs.call4(athis, 11, 3.14, "Four", 'Z');
    }

    //构造方法的引用
    public static void test3() {
        MakeNoArgs mna = Dog::new; // [1]
        Make1Arg m1a = Dog::new;   // [2]
        Make2Args m2a = Dog::new;  // [3]
        Dog dn = mna.make();
        Dog d1 = m1a.make("Comet");
        Dog d2 = m2a.make("Ralph", 4);
    }
}


class X {
    String f() {
        return "X::f()";
    }
}

interface MakeString {
    String make();
}

interface TransformX {
    String transform(X x);
}



class DiyThis {
    void two(int i, double d) {}
    void three(int i, double d, String s) {}
    void four(int i, double d, String s, char c) {}
}

interface TwoArgs {
    void call2(DiyThis athis, int i, double d);
}

interface ThreeArgs {
    void call3(DiyThis athis, int i, double d, String s);
}

interface FourArgs {
    void call4(DiyThis athis, int i, double d, String s, char c);
}



class Dog {
    String name;
    int age = -1; // For "unknown"
    Dog() { name = "stray"; }
    Dog(String nm) { name = nm; }
    Dog(String nm, int yrs) { name = nm; age = yrs; }
}

interface MakeNoArgs {
    Dog make();
}

interface Make1Arg {
    Dog make(String nm);
}

interface Make2Args {
    Dog make(String nm, int age);
}