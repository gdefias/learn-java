/**
 * Created by Defias on 2016/2/27.
 *
 *
 * 变量的作用域
 *
 * 在Java中，变量的作用域分为四个级别：类级、对象实例级、方法级、块级
 *
 * 类级：类级变量又称全局级变量或静态变量，需要使用static关键字修饰，类级变量在类定义后就已经存在，占用内存空间，可以通过类名来访问，
 * 不需要实例化
 *
 * 对象实例级：对象实例级变量就是成员变量，实例化后才会分配内存空间，才能访问
 *
 * 方法级：方法级变量就是在方法内部定义的变量，就是局部变量
 *
 * 块级：定义在一个块内部的变量，变量的生存周期就是这个块，出了这个块就消失了
 */
package Basic;

public class DemoVarScope {
    public static String name = "微学苑";  // 类级变量（类变量）
    public int i = 23; // 对象实例级变量

    // 属性块，在类初始化属性时候运行
    {
        int j = 2;// 块级变量
        System.out.println("hehe");
    }

    public void test1() {
        int j = 3;  // 方法级变量
        if(j == 3) {
            int k = 5;  // 块级变量
        }
        // 这里不能访问块级变量，块级变量只能在块内部访问
        System.out.println("name=" + name + ", i=" + i + ", j=" + j);
    }

    public static void main(String[] args) {
        // 不创建对象，直接通过类名访问类级变量
        System.out.println(DemoVarScope.name);

        // 创建对象并访问它的方法
        DemoVarScope t = new DemoVarScope();   //运行到此时(创建了对象)，上面的属性块被执行，输出hehe
        t.test1();
        System.out.println(t.i);
        System.out.println(t.name);
    }
}
