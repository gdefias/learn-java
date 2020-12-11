package Generics;

import Generics.element_generics.Holder;

/**
 * Created by Defias on 2020/07.
 * Description: 泛型 - 捕获转换
 */
public class Test1_GenericsWildcard4 {

    //@SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Holder raw = new Holder<Integer>(1);
        f1(raw); // Produces warnings  测试正常
        f2(raw); // No warnings

        Holder rawBasic = new Holder();
        rawBasic.set(new Object()); // Warning
        f2(rawBasic); // No warnings

        // Upcast to Holder<?>, still figures it out:
        Holder<?> wildcarded = new Holder<Double>(1.0);
        f2(wildcarded);
    }


    static <T> void f1(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getSimpleName());
    }

    static void f2(Holder<?> holder) {
        f1(holder); // Call with captured type
    }
}
