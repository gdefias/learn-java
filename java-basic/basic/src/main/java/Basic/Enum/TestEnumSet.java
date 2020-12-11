package Basic.Enum;
import java.util.*;

/**
 * Created by Defias on 2017/3/8.

 枚举集合 - EnumSet

 EnumSet的基础是long，一个long值有64位，而一个enum实例只需要一位bit表示其是否存在
 EnumSet也可以应用于超过64个元素的enum

 java.util.EnumSet
 保证集合中的元素不重复

 public abstract class EnumSet<E extends Enum<E>> extends AbstractSet<E> implements Cloneable, java.io.Serializable

 API:
 static <E extends Enum<E>> EnumSet<E>	allOf(Class<E> elementType)
 创建一个包含指定元素类型的所有元素的枚举 set

 EnumSet<E>	clone() 返回 set 的副本

 static <E extends Enum<E>> EnumSet<E>	complementOf(EnumSet<E> s)
 创建一个其元素类型与指定枚举 set 相同的枚举 set，最初包含指定 set 中所不包含的此类型的所有元素
 s - 用其补码初始化此枚举 set 的枚举 set

 static <E extends Enum<E>> EnumSet<E>	copyOf(Collection<E> c)
 创建一个从指定 collection 初始化的枚举 set

 static <E extends Enum<E>> EnumSet<E>	copyOf(EnumSet<E> s)
 创建一个其元素类型与指定枚举 set 相同的枚举 set，最初包含相同的元素（如果有的话）

 static <E extends Enum<E>> EnumSet<E>	noneOf(Class<E> elementType)
 创建一个具有指定元素类型的空枚举 set

 static <E extends Enum<E>> EnumSet<E>	range(E from, E to)
 创建一个最初包含由两个指定端点所定义范围内的所有元素的枚举set

 static <E extends Enum<E>> EnumSet<E>	of(E e)
 创建一个最初包含指定元素的枚举set

 static <E extends Enum<E>> EnumSet<E>	of(E first, E... rest) //调用参数少于或等于5个时进行了性能优化，多于5个时使用可变参数的方法
 创建一个最初包含指定元素的枚举set

 static <E extends Enum<E>> EnumSet<E>	of(E e1, E e2)
 创建一个最初包含指定元素的枚举 set

 static <E extends Enum<E>> EnumSet<E>	of(E e1, E e2, E e3)
 创建一个最初包含指定元素的枚举 set

 static <E extends Enum<E>> EnumSet<E>	of(E e1, E e2, E e3, E e4)
 创建一个最初包含指定元素的枚举 set

 static <E extends Enum<E>> EnumSet<E>	of(E e1, E e2, E e3, E e4, E e5)
 创建一个最初包含指定元素的枚举 set
 */


public class TestEnumSet {
    enum WeekDayEnum {
        Mon, Tue, Wed, Thu, Fri, Sat, Sun;
    }

    public static void main(String[] args) {
        //test0();
        //test1();
        test2();

    }

    public static void test0() {
        for(WeekDayEnum day : EnumSet.range(WeekDayEnum.Mon, WeekDayEnum.Fri)) {
            System.out.println(day);
        }
        System.out.println("----------------");

        EnumSet<WeekDayEnum> subset = EnumSet.of(WeekDayEnum.Mon, WeekDayEnum.Wed);
        for (WeekDayEnum day : subset) {
            System.out.println(day);
        }
        System.out.println();
    }


    public static void test1() {
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class); // Empty set
        points.add(AlarmPoints.BATHROOM);
        System.out.println(points);
        System.out.println("----------------");

        points.addAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
        System.out.println(points);
        System.out.println("----------------");

        points = EnumSet.allOf(AlarmPoints.class);
        points.removeAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
        System.out.println(points);
        System.out.println("----------------");

        points.removeAll(EnumSet.range(AlarmPoints.OFFICE1, AlarmPoints.OFFICE4));
        System.out.println(points);
        System.out.println("----------------");

        points = EnumSet.complementOf(points);
        System.out.println(points);
    }

    public static void test2() {
        EnumSet<Big> bigEnumSet = EnumSet.allOf(Big.class);
        System.out.println(bigEnumSet);
    }


}


enum AlarmPoints {
    STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2, OFFICE3,
    OFFICE4, BATHROOM, UTILITY, KITCHEN
}

enum Big { A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10,
    A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21,
    A22, A23, A24, A25, A26, A27, A28, A29, A30, A31, A32,
    A33, A34, A35, A36, A37, A38, A39, A40, A41, A42, A43,
    A44, A45, A46, A47, A48, A49, A50, A51, A52, A53, A54,
    A55, A56, A57, A58, A59, A60, A61, A62, A63, A64, A65,
    A66, A67, A68, A69, A70, A71, A72, A73, A74, A75 }