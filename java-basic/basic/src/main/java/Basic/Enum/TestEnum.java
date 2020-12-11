package Basic.Enum;
import static Basic.Print.*;
/**
 * Created by Defias on 2017/3/8.

 枚举（本质是类）

 枚举（enum）类型是Java 5新增的特性，它是一种新的类型，允许用常量来表示特定的数据片断，而且全部都以类型安全的形式来表示，所有的枚举都
 继承自java.lang.Enum类

 public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable

 私有构造器
 protected Enum(String name, int ordinal)

 API
 protected  Object	clone() 抛出 CloneNotSupportedException
 int compareTo(E o)  比较此枚举与指定对象的顺序
 boolean  equals(Object other)  当指定对象等于此枚举常量时，返回 true
 protected  void	finalize()  枚举类不能有 finalize 方法
 Class<E>	getDeclaringClass()  返回与此枚举常量的枚举类型相对应的 Class 对象
 int	hashCode()  返回枚举常量的哈希码
 String	name()  返回此枚举常量的名称，在其枚举声明中对其进行声明
 int	ordinal()  返回枚举常量的序数（它在枚举声明中的位置，其中初始常量序数为零）
 String	toString()   返回枚举常量的名称，它包含在声明中
 static <T extends Enum<T>> T valueOf(Class<T> enumType, String name)  返回带指定名称的指定枚举类型的枚举常量


 1. 在某些情况下，一个类的对象是有限且固定的，如季节类，它只有春夏秋冬4个对象，这种实例有限且固定的类，在Java中被称为枚举类
 2. 在Java中使用enum关键字来定义枚举类，其地位与class、interface相同
 3. 枚举类是一种特殊的类，它和普通的类一样，有自己的成员变量、成员方法、构造器 (只能使用private访问修饰符，所以无法从外部调用构造器，
    构造器只在构造枚举值时被调用)
 4. 一个Java源文件中最多只能有一个public类型的枚举类，且该Java源文件的名字也必须和该枚举类的类名相同，这点和类是相同的
 5. 使用enum定义的枚举类默认继承了java.lang.Enum类，并实现了java.lang.Seriablizable和java.lang.Comparable两个接口
 6. 所有的枚举值都是public static final的，且非抽象的，枚举类不能再派生子类
 7. 枚举类的所有实例(枚举值)必须在枚举类的第一行显式地列出，否则这个枚举类将永远不能产生实例。列出这些实例(枚举值)时，系统会自动添加
    public static final修饰，无需程序员显式添加
 8. 与普通类一样，枚举类也可以实现一个或多个接口。枚举类实现接口时，同样要实现该接口的所有方法
 9. 枚举类中也可以定义一个抽象方法，枚举类中所有的枚举值都必须实现这个方法

 枚举类常用方法：
    int compareTo(E o) 该方法用于与指定枚举对象比较顺序，同一个枚举实例只能与相同类型的枚举实例比较。如果该枚举对象位于指定枚举对象之
                       后，则返回正整数；反之返回负整数；否则返回零
    String name() 返回此枚举实例的名称，即枚举值
    static values() 返回一个包含全部枚举值的数组，可以用来遍历所有枚举值，按照枚举声明的顺序
    String toString() 返回枚举值的名称，与name方法类似，更常用，可被重写
    int ordinal() 返回枚举值在枚举类中的索引值(从0开始)，即枚举值在枚举声明中的顺序，这个顺序根据枚举值声明的顺序而定
    static valueOf() 返回带指定名称的指定枚举类型的枚举常量，名称必须与在此类型中声明枚举常量所用的标识符完全匹配(不允许使用额外的
                     空白字符)。这个方法与toString相对应，因此重写toString()方法，一定要重写valueOf()方法，可以重写toString()方
                     法，但不能自己重写valueOf()方法，当重写toString()方法时，valueOf()方法会自动重写，不用理会
    boolean equals()方法 比较两个枚举类对象的引用


 避免错误使用Enum：
    1. enum类型不支持public和protected修饰符的构造方法，因此构造函数一定要是private或friendly的。也正因为如此，所以枚举对象是无法在
       程序中通过直接调用其构造方法来初始化的
    2. 定义enum类型时候，如果是简单类型，那么最后一个枚举值后不用跟任何一个符号；但如果有定制方法，那么最后一个枚举值与后面代码要用
       分号';‘隔开
    3. 由于enum类型的值实际上是通过运行期构造出对象来表示的，所以在cluster环境下，每个虚拟机都会构造出一个同义的枚举对象。因而在做比较
       操作时候就需要注意，如果直接通过使用等号 (==) 操作符，这些看似一样的枚举值一定不相等，因为这不是同一个对象实例
 */

enum Shrubbery { GROUND, CRAWLING, HANGING }


public class TestEnum {
    public enum Color {
        RED, GREEN, BLANK, YELLOW,
    }

    public static void main(String[] args) {
        test1();
//        test2();
//        test3();
    }

    public static void test1() {
        System.out.println(Color.RED.equals(Color.BLANK));
        System.out.println(Color.RED.equals(Color.RED));
        System.out.println(Color.values().length);
        for (Color c: Color.values()) {
            System.out.println(c);
        }

        System.out.println("----------------");

        Color color = Color.RED;
        switch(color) {
            case RED :
                System.out.println(Color.RED);
                break;
            case YELLOW :
                System.out.println(Color.YELLOW);
                break;
            case GREEN:
                System.out.println(Color.GREEN);
                break;
        }

        System.out.println("----------------");
    }

    public static void test2() {
        for(Shrubbery s : Shrubbery.values()) {
            print(s + " ordinal: " + s.ordinal());  //次序
            printnb(s.compareTo(Shrubbery.CRAWLING) + " ");
            printnb(s.equals(Shrubbery.CRAWLING) + " ");
            print(s == Shrubbery.CRAWLING);
            print(s.getDeclaringClass().toString());
            print(s.name());
            print(s.toString());
            print("----------------------");
        }

        for(String s : "HANGING CRAWLING GROUND".split(" ")) {
            Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
            print(shrub.toString());
        }
    }

    //使用枚举随机选取工具
    public static void test3() {
        for(int i = 0; i < 20; i++)
            System.out.print(TestUtilEnums.random(Shrubbery.class) + " ");

        print("\n----------------------");
        for(int i = 0; i < 20; i++)
            System.out.print(TestUtilEnums.random(Shrubbery.values()) + " ");
    }
}



