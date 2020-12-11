package Generics;

import Generics.element.Amphibian;
import Generics.element.Vehicle;
import Generics.element_generics.Point;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: 泛型 - 类型擦除
 */

public class Test3_GenericsTypeErased {

    public static void main(String[] args) {
        //test1();
        test2();
        //test3();
        //testArrayMaker();
        //testFilledListMaker();
    }


    public static void test1() {
        ArrayList<String> a1 = new ArrayList<String>();
        ArrayList<Integer> a2 = new ArrayList<Integer>();
        Class c1 = a1.getClass();
        Class c2 = a2.getClass();
        System.out.println(c1 == c2);  //true  ArrayList的泛型类型虽然不一样，但他们是相同的类型

    }

    public static void test2() {
        List<Vehicle> list = new ArrayList<Vehicle>();
        Map<Vehicle,Amphibian> map = new HashMap<Vehicle,Amphibian>();

        //Class.getTypeParameters: 返回一个TypeVariable对象数组，泛型声明的类型参数
        //以下语句只是输出了用作参数占位符的标识符，并非有用的信息
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));


        //看起来好像泛型类Quark知道是工作在Amphibian类之上，但是并非如此，对于泛型类Quark来说，Amphibian只是个Object
        Quark<Amphibian> quark = new Quark<Amphibian>();
        Particle<Long,Double> p = new Particle<Long,Double>();

        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
    }

    public static void test3() {
        //像使用普通类一样使用泛型类（不提供泛型类型参数） --迁移兼容器
        Point p = new Point();
        p.setX(10);
        p.setY(20.8);
        int xx = (Integer)p.getX();  //向下转型 p.getX()返回的是Object类型，所以需要强转
        double yy = (Double)p.getY();
        System.out.println("This point is：" + xx + ", " + yy);
    }

    public static void testArrayMaker() {
        ArrayMaker<String> stringMaker = new ArrayMaker<String>(String.class);
        String[] stringArray = stringMaker.create(9);
        System.out.println(Arrays.toString(stringArray));
    }

    public static void testFilledListMaker() {
        FilledListMaker<String> stringMaker = new FilledListMaker<String>();
        List<String> list = stringMaker.create("Hello", 4);
        System.out.println(list);
    }
}

class Quark<Q> {}

class Particle<POSITION,MOMENTUM> {}


//并非预期的那样（这样做是无意义的）
//由于类型擦除，Array.newInstance并未拥有kind所蕴含的类型信息
class ArrayMaker<T> {
    private Class<T> kind;
    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }


    @SuppressWarnings("unchecked")
    T[] create(int size) {
        return (T[])Array.newInstance(kind, size); //反射
    }
}


//与预期的一样（这样却是有意义的）
//即使擦除在方法或类内部移除了有关实际类型的信息，编译器仍旧可以确保在方法或类中使用的类型的内部一致性
class FilledListMaker<T> {
    List<T> create(T t, int n) {
        List<T> result = new ArrayList<T>();
        for(int i = 0; i < n; i++)
            result.add(t);
        return result;
    }
}


