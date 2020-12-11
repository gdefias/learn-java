/**
 * Created by Defias on 2016/2/28.
 *
 * static静态
 *
 * 静态static
 *
 * static静态变量和static静态方法都能够通过类名来直接访问，它们属于类，不属于任何独立的对象，不需要创建一个类的对象（可以但不推荐）
 * 来访问该类的静态成员，所以static修饰的成员又称作类变量和类方法
 *
 * 理解static：
 * 编译器（在类加载的时候）只为整个类创建了一个静态变量的副本，也就是只分配一个内存空间，虽然有多个实例，但这些实例共享该内存。
 * 实例变量则不同，每创建一个对象，都会分配一次内存空间，不同变量的内存相互独立，互不影响，改变a对象的实例变量不会影响b对象
 *
 * 静态变量和静态方法：
 * 1. 一个类的静态方法只能访问静态变量
 * 2. 一个类的静态方法不能够直接调用非静态方法
 * 3. 如访问控制权限允许，静态变量和静态方法也可以通过对象来访问，但是不被推荐
 * 4. 静态方法中不存在当前对象，因而不能使用this，当然也不能使用super
 * 5. 静态方法不能被非静态方法覆盖
 * 6. 构造方法不允许声明为static的
 * 7. 局部变量不能使用static修饰
 *
 * 静态导入import：
 * import static packageName.className.methonName; //导入某个特定的静态方法
 * import static packageName.className; //导入类中的所有静态成员
 *
 * 静态初始器(Static Initializer)：
 * 一个存在于类中、方法外面的静态块。静态初始器仅仅在类装载的时候（第一次使用类的时候）执行一次，往往用来初始化静态变量
 */
package Basic;
import static java.lang.System.*;

public class JavaStatic {
    static int i = 10;  //静态变量、类变量
    int j;

    static {  //静态初始器
        i = 100;
        out.println("Now in static block.");
    }


    JavaStatic() {
        this.j = 20;
    }

    static int sum(int x, int y) {  //静态方法
        return x + y;
    }

    public static void main(String[] args) {
        out.println("类变量 i=" + JavaStatic.i);
        JavaStatic obj = new JavaStatic();
        out.println("实例变量 j=" + obj.j);

        int sum = JavaStatic.sum(15, 10);
        out.println("15+10=" + sum);
    }
}
