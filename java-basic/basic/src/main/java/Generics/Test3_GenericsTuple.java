package Generics;
import Generics.element.*;
/**
 * Created by Defias on 2020/07.
 * Description: 泛型元组 - 将一组对象打包存储于一个单一对象
 */

public class Test3_GenericsTuple {

    public static void main(String[] args) {
        TwoTuple<String,Integer> ttsi = f();
        System.out.println(ttsi);
        // ttsi.first = "there"; // Compile error: final
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }



    static TwoTuple<String,Integer> f() {
        // Autoboxing converts the int to Integer:
        return new TwoTuple<String,Integer>("hi", 47);
    }

    static ThreeTuple<Amphibian,String,Integer> g() {
        return new ThreeTuple<Amphibian, String, Integer>(
                new Amphibian(), "hi", 47);
    }

    static FourTuple<Vehicle,Amphibian,String,Integer> h() {
        return new FourTuple<Vehicle,Amphibian,String,Integer>(
                new Vehicle(), new Amphibian(), "hi", 47);
    }

    static FiveTuple<Vehicle,Amphibian,String,Integer,Double> k() {
        return new FiveTuple<Vehicle,Amphibian,String,Integer,Double>(
                new Vehicle(), new Amphibian(), "hi", 47, 11.1);
    }



    //五元组
    public static class FiveTuple<A,B,C,D,E> extends FourTuple<A,B,C,D> {
        public final E fifth;
        public FiveTuple(A a, B b, C c, D d, E e) {
            super(a, b, c, d);
            fifth = e;
        }
        public String toString() {
            return "(" + first + ", " + second + ", " +
                    third + ", " + fourth + ", " + fifth + ")";
        }
    }

    //四元组
    public static class FourTuple<A,B,C,D> extends ThreeTuple<A,B,C> {
        public final D fourth;
        public FourTuple(A a, B b, C c, D d) {
            super(a, b, c);
            fourth = d;
        }
        public String toString() {
            return "(" + first + ", " + second + ", " +
                    third + ", " + fourth + ")";
        }
    }

    //三元组
    public static class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
        public final C third;
        public ThreeTuple(A a, B b, C c) {
            super(a, b);
            third = c;
        }
        public String toString() {
            return "(" + first + ", " + second + ", " + third +")";
        }
    }

    //二元组
    public static class TwoTuple<A,B> {
        public final A first;
        public final B second;
        public TwoTuple(A a, B b) { first = a; second = b; }
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

}
