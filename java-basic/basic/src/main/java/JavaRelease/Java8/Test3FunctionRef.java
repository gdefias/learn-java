package JavaRelease.Java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-07
 *
 * 方法引用
 * 方法引用提供了一个很有用的语义来直接访问类或者实例的已经存在的方法或者构造方法
 */

class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println( "Collided " + car.toString() );
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}

public class Test3FunctionRef {
    public static void main(String[] args) {
        //构造方法引用，语法是：Class::new ，对于泛型来说语法是：Class<T>::new
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car,car);

        //静态方法引用，语法是：Class::static_method
        cars.forEach(Car::collide);

        //类实例的方法引用，语法是：Class::method
        cars.forEach(Car::repair);
        //cars.forEach( (x)->System.out.println("Repaired "+x.toString()) );

        //引用特殊类的方法，语法是：instance::method
        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );


        List names = new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        names.forEach(System.out::println);  //将System.out::println方法作为静态方法来引用
    }
}
