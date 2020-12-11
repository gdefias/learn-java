package Generics;
import Generics.element.Watercolors;
import static Generics.element.Watercolors.*; //导入枚举项
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: 泛型方法

 集合操作 --并集、交集、差集
 */

public class Test2_GenericsMethodSet {

    public static void main(String[] args) {

        //EnumSet.range： 传入两个边界，返回一个范围集合
        Set<Watercolors> set1 = EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
        print("set1: " + set1);

        Set<Watercolors> set2 = EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);
        print("set2: " + set2);

        print("union(set1, set2): " + union(set1, set2));  //传入枚举类型的Set

        Set<Watercolors> subset = intersection(set1, set2);
        print("intersection(set1, set2): " + subset);

        print("difference(set1, subset): " + difference(set1, subset));
        print("difference(set2, subset): " + difference(set2, subset));
        print("complement(set1, set2): " + complement(set1, set2));
    }


    //并集
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<T>(a);
        result.addAll(b);
        return result;
    }

    //交集
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<T>(a);
        result.retainAll(b);
        return result;
    }

    //差集
    // Subtract subset from superset:
    public static <T> Set<T> difference(Set<T> superset, Set<T> subset) {
        Set<T> result = new HashSet<T>(superset);
        result.removeAll(subset);
        return result;
    }

    //并集与交集的差集
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }
}
