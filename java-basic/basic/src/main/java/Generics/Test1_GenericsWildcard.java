package Generics;
import Generics.element_generics.Holder;
import Generics.element_generics.Plate;
import Generics.element_generics.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static Generics.element.Fruits.*;

/**
 * Created by Defias on 2020/07.
 * Description: 泛型 - 通配符
 *
 */

public class Test1_GenericsWildcard {

    public static void main(String[] args) {
        //test0();
       //test1();
        //test2();
        test3();
    }

    //泛型通配符
    public static void test0() {
        Point<Integer, Integer> p1 = new Point<Integer, Integer>();
        p1.setX(10);
        p1.setY(20);
        printPoint(p1);
        printPoint2(p1);
        printNumPoint(p1);

        Point<String, String> p2 = new Point<String, String>();
        p2.setX("东京180度");
        p2.setY("北纬210度");
        printPoint(p2);
        printPoint2(p2);

        printStrPoint(p2);
        printStrPoint2(p2);

    }

    public static void test1() {
        Plate<Fruit> pf;
        Plate<Apple> pa = new Plate<Apple>(new Apple());
        //! pf = pa; //装苹果的盘子无法转换成装水果的盘子

        //Plate<? extends Fruit>引用可以赋予Fruit及其子类的Plate对象
        Plate<? extends Fruit> pf2;
        pf2 = pa;  //这样就ok了

        pf2 = new Plate<Jonathan>(new Jonathan());
        pf2 = new Plate<Fruit>(new Fruit());
        //! pf2 = new Plate<Food>(new Food());


        //上界通配符的副作用： <? extends Fruit>会使往盘子里放东西的set()方法失效，取东西get()方法有效
        //! pf2.set(new Apple());
        //! pf2.set(new Fruit());

        //读取出来的东西只能存放在Fruit或它的父类里，原因是编译器只知道容器内是Fruit或者它的子类，但具体是什么类型不知道
        Fruit f = pf2.get();
        Food f1 = pf2.get();
        Object f2 = pf2.get();
        //! Apple f3 = pf2.get();
        //! Jonathan f4 = pf2.get();
    }


    public static void test2() {
        //基类型的数组引用可以被赋予其子类型的数组对象
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple(); // OK
        fruit[1] = new Jonathan(); // OK

        // Runtime type is Apple[], not Fruit[] or Orange[]:
        try {
            //编译期不会报异常，运行期的数组机制知道它处理的是Apple[],因此会在向数组中放置异构类型时抛异常
            // Compiler allows you to add Fruit:
            fruit[0] = new Fruit(); // ArrayStoreException
        } catch(Exception e) {
            System.out.println(e);
        }

        try {
            // Compiler allows you to add Oranges:
            fruit[0] = new Orange(); // ArrayStoreException
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void test3() {
        //如果将使用泛型容器来代替数组，在编译期时就会报错
        // Compile Error: incompatible types:
        //List<Fruit> flist = new ArrayList<Apple>();

        //使用通配符，编译到时没报错，却除了null啥也不能放入
        // Wildcards allow covariance:
        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Compile Error: can't add any type of object:
        // flist.add(new Apple());
        // flist.add(new Fruit());
        // flist.add(new Object());
        flist.add(null); // Legal but uninteresting
        // We know that it returns at least Fruit:
        Fruit f = flist.get(0);
        System.out.println(f);
        System.out.println("------------");

        //好不容易放进去了，却被当成是Object
        List<? extends Fruit> flist2 = Arrays.asList(new Apple(), new Fruit(), new Orange());
        System.out.println(flist2.contains(new Apple())); // Argument is 'Object'
        System.out.println(flist2.indexOf(new Apple())); // Argument is 'Object'
        Apple a = (Apple)flist2.get(0); // No warning
        System.out.println(a);
    }

    public static void test4() {
        Holder<Apple> happle = new Holder<Apple>(new Apple());
        Apple d = happle.get();
        happle.set(d);
        // Holder<Fruit> HFruit = HApple; // Cannot upcast
        Holder<? extends Fruit> hfruit = happle; // OK
        Fruit p = hfruit.get();
        d = (Apple)hfruit.get(); // Returns 'Object'
        try {
            Orange c = (Orange)hfruit.get(); // No warning
        } catch(Exception e) { System.out.println(e); }
        // fruit.set(new Apple()); // Cannot call set()
        // fruit.set(new Fruit()); // Cannot call set()
        System.out.println(hfruit.equals(d)); // OK
    }



    public static <T1, T2> void printPoint(Point<T1, T2> p){
        System.out.println("This point is: " + p.getX() + ", " + p.getY());
    }

    //printPoint的等价方法
    public static void printPoint2(Point<?, ?> p){
        //参数p的类型使用了非受通配符:? 表示任意的数据类型
        System.out.println("This point is: " + p.getX() + ", " + p.getY());
    }

    public static void printNumPoint(Point<? extends Number, ? extends Number> p) {
        //参数p的类型使用了受通配符:? extends Number 表示参数类型只能是Number及其子类
        System.out.println("x: " + p.getX() + ", y: " + p.getY());
    }

    public static void printStrPoint(Point<? extends String, ? extends String> p) {
        //参数p的类型使用了受通配符:? extends String 表示参数类型只能是String及其子类
        System.out.println("GPS: " + p.getX() + "，" + p.getY());
    }

    public static void printStrPoint2(Point<? super String, ? super String> p) {
        System.out.println("GPS: " + p.getX() + "，" + p.getY());
    }
}








