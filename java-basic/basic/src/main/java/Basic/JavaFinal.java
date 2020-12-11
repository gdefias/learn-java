/**
 * Created by Defias on 2016/2/28.
 *
 * final修饰符
 *
 * 对于基本类型，final使数值恒定不变，而对于对象引用，final使引用恒定不变（恒定的指向某个东西，不能再改变为指向别的东西，但东西本身是可以变的）
 *
 * 声明类、变量和方法时，可使用关键字final来修饰。final所修饰的数据具有“终态”的特征，表示“最终的”意思
 * final修饰的类不能被继承（一旦将一个类声明为final，那么该类包含的方法也将被隐式地声明为final，但是变量不是）
 * final修饰的方法不能被子类重写和覆盖
 * final修饰的变量（成员变量或局部变量）即成为常量，只能赋值一次
 * final修饰的成员变量必须在声明的同时赋值，如果在声明的时候没有赋值，那么只有一次赋值的机会，而且只能在构造方法中显式赋值，然后才能使用
 * final修饰的局部变量可以只声明不赋值，然后再进行一次性的赋值
 * final修饰的方法为静态绑定，不会产生多态（动态绑定），程序在运行时不需要再检索方法表，能够提高代码的执行效率。
 * 在Java中，被static或private修饰的方法会被隐式的声明为final，因为动态绑定没有意义
 */
package Basic;

public class JavaFinal {
    public static final int TOTAL_NUMBER = 5;
    public int id;
    public final char na;  //空白的final 既能根据对象有所不同，又可以保持对于同一对象恒定不变的特性

    public JavaFinal(char na) {
        this.na = na;
        //id = ++TOTAL_NUMBER;  // 非法，对final变量TOTAL_NUMBER进行二次赋值了
    }

    public void play() {
        System.out.println(na);
    }

    public void f(final Animalll ani) {  //final参数
        //ani = new Animalll();       //非法
    }

    public int g(final int i) {  //final参数
        return i+1;
    }


    public static void main(String[] args) {
        final JavaFinal t = new JavaFinal('Q');
        t.play();
        //t.na = 'W';  //非法
        final int i = 10;
        final int j;
        j = 20;
        //j = 30;  // 非法，对final变量进行二次赋值


        JavaFinal t2 = new JavaFinal('P');
        t2.play();
        //t2.na = 'W';  //非法

    }
}
