/**
 * Created by Defias on 2016/2/28.
 *
 * 内部类： 定义在一个类中的类
 *
 * 在Java中，允许在一个类（或方法、语句块）的内部定义另一个类，称为内部类（Inner Class）或嵌套类（Nested Class），编译会生成
 * 两个.class文件：JavaOuterC.class 和 JavaOuterC$Inner.class。也就是说，内部类会被编译成独立的字节码文件
 *
 * Java内部类（两个类或以上的关联关系），对外部类数据有更好的封装性：
 * - 成员内部类：内部类作为外部类的一个普通成员属性存在，必须先new外部类对象，再new内部类对象
 * - 静态内部类：内部类作为外部类的一个类属性存在，而且对外部类数据有更好的封装
 * - 局部内部类：类的方法中定义的类，很少使用，有效范围很窄，仅限于方法栈而已
 * - 匿名内部类：匿名了，没有类名，适用于创建一次性使用的类，局部内部类的一种特殊形式。
 *
 * 非静态内部类中：不能使用static成员变量和方法，也不能包含嵌套类
 *
 * 理解内部类：
 * - 内部类对象拥有一个对外部类对象的引用
 * - 编译器修改了所有内部类的构造器，添加一个外部类引用的参数
 * - 内部类是一种编译器现象，与虚拟机无关，编译器会将内部类翻译成$分隔外部类名与内部类名的常规类文件，而虚拟机对此一无所知
 *
 * 内部类的特殊语法规则：先有外部类的对象，才能通过外部类的对象生成内部类的对象 （调用外部类对象的new方法）
 *
 * 匿名内部类语法：
 * new 接口() | 抽象类构造器 (实参列表) {
 *     //匿名内部类的类体部分
 * }
 *
 * 一个内部类被嵌套多少层并不重要，它能透明的访问所有它所嵌入的外部类的所有成员
 */
package Basic;

public class JavaInnerC {
    private int size;
    public static final int TOTAL_NUMBER = 5;
    public int id = 123;
    private int[] items;

    //成员内部类（如同外部类的一个普通成员）：成员内部类可以使用各种修饰符，包括public、protected、pte、static、final和abstract
    private class Inner {
        private int counter = 10;
        private int size;

        public int curr0() {
            return items[0];   //内部类拥有其外围类的所有元素的访问权
        }

        public JavaInnerC outer() {
            return JavaInnerC.this;   //在内部类中获得外部类对象的引用
        }

        //特殊情况：外部类成员变量名 == 内部类成员变量名 == 内部类中局部变量名
        public void doStuff(int size) {
            size++;  //局部变量size
            System.out.println(size);
            this.size++;  //内部类的size
            JavaInnerC.this.size++;  //外部类的size
        }

        public void printt() {
            System.out.println("printt");
        }
    }

    public JavaInnerC(int[] items) {
        this.items = items;
    }


    //通过一个方法返回内部类的引用
    public Inner Inner() {
        return new Inner();
    }

    public void func1() {
        final int age = 15;
        String str = "http://www.weixueyuan.net";

        class Innerr {  //局部内部类(Local class)：定义在方法代码块中的类。它只在定义它们的代码块中是可见的（可以调用它），
                        //局部内部类可以使用定义它的代码块中的final局部变量（非final变量不能访问），当然也可以使用外围类的变量
                        //不可以是static的，里边也不能定义static成员；不可以用public、private、protected修饰，只能使用缺省的；可以是abstract的
            public void innerTest() {
                System.out.println(TOTAL_NUMBER);
                System.out.println(id);
                //System.out.println(str);  //不合法，只能访问本地方法的final变量（非final变量不能访问）
                System.out.println(age);
            }
        }
        new Innerr().innerTest();  //调用局部内部类的方法
    }

    public void func2(boolean b) {
        if(b) {    //可以在任意作用域内创建一个局部内部类
            class TrackingSlip {
                private String id;

                TrackingSlip(String s) {
                    id = s;
                }

                String getSlip() {
                    return id;
                }
            }
            TrackingSlip ts = new TrackingSlip("slip");
            String s = ts.getSlip();
        }
        // Can't use it here! Out of scope:
        //! TrackingSlip ts = new TrackingSlip("x");
    }

    public void func() {
        Inner inner0 = Inner();
        inner0.printt();

        Inner inner1 = new Inner();
        inner1.printt();
        inner1.doStuff(1111);

        Inner inner2 = new Inner();
        inner2.printt();
        inner1.doStuff(2222);

    }

    public static void main(String args[]) {
        int[] items = new int[] {123,231,222};
        JavaInnerC outer = new JavaInnerC(items);
        Inner inner = outer.new Inner();  //通过外部类对象创建内部类对象
        inner.doStuff(123);
        System.out.println(outer.size);
        //System.out.println(inner.counter);
        //System.out.println(counter); // 编译错误，外部类不能访问内部类的变量

        outer.func1();

        System.out.println(outer.size);

        //实现接口或抽象类的匿名内部类
        new Person("fan") {
            public void eat() {
                System.out.println("eat something");
            }
        }.eat();
    }
}


abstract class Person {
    String food;

    public Person(String food) {
        this.food = food;
    }

    public abstract void eat();
}

