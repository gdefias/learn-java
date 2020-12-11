package Basic;
/**
 * Created by Defias on 2016/2/27.
 *
 * 重载
 *
 * 方法重载(method overloading)：
 * 1. 在Java中，同一个类中的多个方法可以有相同的名字，只要它们的参数列表不同就可以
 * 2. 参数列表又叫参数签名，包括参数的类型、参数的个数和参数的顺序，只要有一个不同就叫做参数列表不同
 * 3. 仅仅参数变量名称不同是不可以的，仅仅返回类型不同不足以成为方法的重载
 * 4. 构造方法也可以重载
 * 5. Java父类和子类中的方法都会参与重载，例如，父类中有一个方法是func(){ … }，子类中有一个方法是func(int i){ … }，就构成了方法的重载
 */


public class JavaOverload {

    public static void main(String args[]){
        JavaOverload obj= new JavaOverload();
        obj.test();
        obj.test(2);
        obj.test(2,3);
        obj.test(2.0);
    }

    void test() {
        System.out.println("No parameters");
    }

    // 重载上面的方法，并且带了一个整型参数
    void test(int a){
        System.out.println("a: " + a);
    }

    // 重载上面的方法，并且带了两个参数
    void test(int a,int b) {
        System.out.println("a and b: " + a + " " + b);
    }

    // 重载上面的方法，并且带了一个双精度参数
    double test(double a) {
        System.out.println("double a: " + a);
        return a*a;
    }
}
