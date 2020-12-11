/**
 * Created by Defias on 2016/2/27.
 *
 * 多态
 *
 * 多态存在的三个必要条件：要有继承、要有重写、父类变量引用子类对象
 *
 * 多态方式调用方法时:
 * 首先检查父类中是否有该方法，如果没有，则编译错误；如果有，则检查子类是否覆盖了该方法，如果子类覆盖了该方法，就调用子类的方法，
 * 否则调用父类方法
 *
 * 多态的一个好处是当子类比较多时，也不需要定义多个变量，可以只定义一个父类类型的变量来引用不同子类的实例
 *
 * 在继承链中，将子类向父类转换称为“向上转型”，将父类向子类转换称为“向下转型”，对象的类型转换在程序运行时检查
 */
package Basic;

public class DuoTai {
    public static void main(String[] args) {
        Animal obj = new Animal(); //父类的变量obj可以引用父类的实例
        Animal obj1 = new Cat();  //父类的变量obj也可以引用子类的实例
        Animal obj2 = new Dog();

        cry(obj);
        cry(obj1);
        cry(obj2);
    }

    public static void cry(Animal animal) {
        animal.cry();
    }
}


class Animal {
    // 动物的叫声
    public void cry() {
        System.out.println("不知道怎么叫");
    }

}


class Cat extends Animal {
    // 猫的叫声
    public void cry() {
        System.out.println("喵喵~");
    }
}


class Dog extends Animal {
    // 狗的叫声
    public void cry() {
        System.out.println("汪汪~");
    }
}


