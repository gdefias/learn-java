package Generics;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: 泛型 - 擦除的补偿
 *
 */

public class Test3_GenericsTypeErased2 {

    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    public static void test1() {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<Building>(Building.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));

        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<House>(House.class);
        System.out.println(ctt2.f(new Building()));
        System.out.println(ctt2.f(new House()));
    }

    public static void test2() {
        ClassAsFactory<Employ> fe = new ClassAsFactory<Employ>(Employ.class);
        print("ClassAsFactory<Employee> succeeded");

        //使用Integer失败的原因：Integer没有任何的默认构造器
        try {
            ClassAsFactory<Integer> fi = new ClassAsFactory<Integer>(Integer.class);
        } catch(Exception e) {
            print("ClassAsFactory<Integer> failed");
        }
    }

    //Integer问题的解决办法：使用显示的工厂
    public static void test3() {
        new Foo2<Integer>(new IntegerFactory());
        new Foo2<Widget>(new Widget.Factory());
    }
}



class Building {}
class House extends Building {}

//引入类型标签对擦除进行补偿
class ClassTypeCapture<T> {
    Class<T> kind;
    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }
    public boolean f(Object arg) {
        return kind.isInstance(arg);
    }
}



class ClassAsFactory<T> {
    T x;
    public ClassAsFactory(Class<T> kind) {
        try {
            x = kind.newInstance();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Employ {}


//显示的工厂
interface FactoryI<T> {
    T create();
}



class Foo2<T> {
    private T x;
    public <F extends FactoryI<T>> Foo2(F factory) {  //限制类型
        x = factory.create();
    }
    // ...
}

class IntegerFactory implements FactoryI<Integer> {
    public Integer create() {
        return new Integer(0);
    }
}

class Widget {
    public static class Factory implements FactoryI<Widget> {
        public Widget create() {
            return new Widget();
        }
    }
}

