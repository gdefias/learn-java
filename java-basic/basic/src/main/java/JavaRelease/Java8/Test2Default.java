package JavaRelease.Java8;

import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 * Description: Default关键字 -  Java 8引入
 * User: Defias
 * Date: 2019-07

 也可称为Virtual extension methods——虚拟扩展方法。是指，在接口内部包含了一些默认的方法实现（也就是接口中可以包含方法体，
 这打破了Java之前版本对接口的语法限制），从而使得接口在进行扩展的时候，不会破坏与接口相关的实现类代码

 接口的默认方法
 默认方法和抽象方法的区别是抽象方法必须要被实现，默认方法不是作为替代方式，接口可以提供一个默认的方法实现，所有这个接口的实现类都会
 通过继承得到这个方法（如果有需要也可以重写这个方法）

 一个类实现了多个接口，且这些接口有相同的默认方法，这种情况的解决方法：
    1、第一个解决方案是创建自己的默认方法，来覆盖重写接口的默认方法
    2、第二种解决方案可以使用super来调用指定接口的默认方法

 不能定义静态的默认方法
 */

public class Test2Default {
    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1() {
        Defaulable d1 = new DefaultableImpl();
        System.out.println(d1.notRequired());

        Defaulable d2 = new OverridableImpl();
        System.out.println(d2.notRequired());


        Defaulable defaulable = DefaulableFactory.create( DefaultableImpl::new );
        System.out.println( defaulable.notRequired() );

        defaulable = DefaulableFactory.create( OverridableImpl::new );
        System.out.println( defaulable.notRequired() );
        System.out.println("------------------");
    }

    public static void test2() {
        Carr vehicle = new Carr();
        vehicle.print();

        //Carrr carrrr = new Carrr();
        //carrrr.print();
    }

}


interface Defaulable {
     default String notRequired() {  //默认方法
        return "Default implementation";
    }
}

class DefaultableImpl implements Defaulable {
}

class OverridableImpl implements Defaulable {
    @Override
    public String notRequired() {
        return "Overridden implementation";
    }
}


interface DefaulableFactory {
    static Defaulable create(Supplier<Defaulable> supplier ) {
        return supplier.get();
    }
}


interface Vehicle {
    default void print() {
        System.out.println("我是一辆车!");
    }

    static void blowHorn() {
        System.out.println("按喇叭!!!");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车!");
    }
}

class Carr implements Vehicle, FourWheeler {
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车!");
    }
}

class Carrr implements FourWheeler {

}