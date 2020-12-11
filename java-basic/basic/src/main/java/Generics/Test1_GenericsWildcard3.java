package Generics;

import Generics.element_generics.Holder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Defias on 2020/07.
 * Description:
 *
 */

public class Test1_GenericsWildcard3 {
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    public static void test1() {
        UnboundedWildcards1.assign1(new ArrayList());
        UnboundedWildcards1.assign2(new ArrayList());
        // assign3(new ArrayList()); // Warning:
        // Unchecked conversion. Found: ArrayList
        // Required: List<? extends Object>

        UnboundedWildcards1.assign1(new ArrayList<String>());
        UnboundedWildcards1.assign2(new ArrayList<String>());
        UnboundedWildcards1.assign3(new ArrayList<String>());

        // Both forms are acceptable as List<?>:
        List<?> wildList = new ArrayList();
        wildList = new ArrayList<String>();
        UnboundedWildcards1.assign1(wildList);
        UnboundedWildcards1.assign2(wildList);
        UnboundedWildcards1.assign3(wildList);
    }

    public static void test2() {
        UnboundedWildcards2.assign1(new HashMap());
        UnboundedWildcards2.assign2(new HashMap());
        // assign3(new HashMap()); // Warning:
        // Unchecked conversion. Found: HashMap
        // Required: Map<String,?>
        UnboundedWildcards2.assign1(new HashMap<String,Integer>());
        UnboundedWildcards2.assign2(new HashMap<String,Integer>());
        UnboundedWildcards2.assign3(new HashMap<String,Integer>());
    }


    public static void test3() {
        Holder raw = new Holder<Long>();
        // Or:
        raw = new Holder();
        Holder<Long> qualified = new Holder<Long>();
        Holder<?> unbounded = new Holder<Long>();
        Holder<? extends Long> bounded = new Holder<Long>();
        Long lng = 1L;

        Wildcards.rawArgs(raw, lng);
        Wildcards.rawArgs(qualified, lng);
        Wildcards.rawArgs(unbounded, lng);
        Wildcards.rawArgs(bounded, lng);

        Wildcards.unboundedArg(raw, lng);
        Wildcards.unboundedArg(qualified, lng);
        Wildcards.unboundedArg(unbounded, lng);
        Wildcards.unboundedArg(bounded, lng);

        // Object r1 = exact1(raw); // Warnings:
        //   Unchecked conversion from Holder to Holder<T>
        //   Unchecked method invocation: exact1(Holder<T>)
        //   is applied to (Holder)
        Long r2 = Wildcards.exact1(qualified);
        Object r3 = Wildcards.exact1(unbounded); // Must return Object
        Long r4 = Wildcards.exact1(bounded);

        // Long r5 = exact2(raw, lng); // Warnings:
        //   Unchecked conversion from Holder to Holder<Long>
        //   Unchecked method invocation: exact2(Holder<T>,T)
        //   is applied to (Holder,Long)
        Long r6 = Wildcards.exact2(qualified, lng);
        // Long r7 = exact2(unbounded, lng); // Error:
        //   exact2(Holder<T>,T) cannot be applied to
        //   (Holder<capture of ?>,Long)
        // Long r8 = exact2(bounded, lng); // Error:
        //   exact2(Holder<T>,T) cannot be applied
        //   to (Holder<capture of ? extends Long>,Long)

        // Long r9 = wildSubtype(raw, lng); // Warnings:
        //   Unchecked conversion from Holder
        //   to Holder<? extends Long>
        //   Unchecked method invocation:
        //   wildSubtype(Holder<? extends T>,T) is
        //   applied to (Holder,Long)
        Long r10 = Wildcards.wildSubtype(qualified, lng);
        // OK, but can only return Object:
        Object r11 = Wildcards.wildSubtype(unbounded, lng);
        Long r12 = Wildcards.wildSubtype(bounded, lng);

        // wildSupertype(raw, lng); // Warnings:
        //   Unchecked conversion from Holder
        //   to Holder<? super Long>
        //   Unchecked method invocation:
        //   wildSupertype(Holder<? super T>,T)
        //   is applied to (Holder,Long)
        Wildcards.wildSupertype(qualified, lng);
        // wildSupertype(unbounded, lng); // Error:
        //   wildSupertype(Holder<? super T>,T) cannot be
        //   applied to (Holder<capture of ?>,Long)
        // wildSupertype(bounded, lng); // Error:
        //   wildSupertype(Holder<? super T>,T) cannot be
        //  applied to (Holder<capture of ? extends Long>,Long)
    }
}



class UnboundedWildcards1 {
    static List list1;
    static List<?> list2;
    static List<? extends Object> list3;

    static void assign1(List list) {
        list1 = list;
        list2 = list;
        // list3 = list; // Warning: unchecked conversion
        // Found: List, Required: List<? extends Object>
    }

    static void assign2(List<?> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }

    static void assign3(List<? extends Object> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }
}


class UnboundedWildcards2 {
    static Map map1;
    static Map<?,?> map2;
    static Map<String,?> map3;

    static void assign1(Map map) {
        map1 = map;
    }

    static void assign2(Map<?,?> map) {
        map2 = map;
    }

    static void assign3(Map<String,?> map) {
        map3 = map;
    }
}



class Wildcards {
    // Raw argument:
    static void rawArgs(Holder holder, Object arg) {
        // holder.set(arg); // Warning:
        //   Unchecked call to set(T) as a
        //   member of the raw type Holder
        // holder.set(new Wildcards()); // Same warning

        // Can't do this; don't have any 'T':
        // T t = holder.get();

        // OK, but type information has been lost:
        Object obj = holder.get();
    }

    // Similar to rawArgs(), but errors instead of warnings:
    static void unboundedArg(Holder<?> holder, Object arg) {
        // holder.set(arg); // Error:
        //   set(capture of ?) in Holder<capture of ?>
        //   cannot be applied to (Object)
        // holder.set(new Wildcards()); // Same error

        // Can't do this; don't have any 'T':
        // T t = holder.get();

        // OK, but type information has been lost:
        Object obj = holder.get();
    }

    static <T> T exact1(Holder<T> holder) {
        T t = holder.get();
        return t;
    }

    static <T> T exact2(Holder<T> holder, T arg) {
        holder.set(arg);
        T t = holder.get();
        return t;
    }

    static <T> T wildSubtype(Holder<? extends T> holder, T arg) {
        // holder.set(arg); // Error:
        //   set(capture of ? extends T) in
        //   Holder<capture of ? extends T>
        //   cannot be applied to (T)
        T t = holder.get();
        return t;
    }

    static <T> void wildSupertype(Holder<? super T> holder, T arg) {
        holder.set(arg);
        // T t = holder.get();  // Error:
        //   Incompatible types: found Object, required T

        // OK, but type information has been lost:
        Object obj = holder.get();
    }
}