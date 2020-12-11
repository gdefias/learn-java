/**
 * Created by Defias on 2016/2/27.
 *
 * this
 *
 * 关于构造方法：
 * 1、构造方法的名称必须与类的名称相同，并且没有返回值
 * 2、每个类都有构造方法。如果没有显式地为类定义构造方法，Java编译器将会为该类提供一个默认的构造方法
 * 3、构造方法不能被显示调用
 * 4、可以通过重载构造方法来表达对象的多种初始化行为
 */
package Basic;
public class JavaThis {
    public String name;
    public int age;

    public JavaThis(){
        this("微学苑", 3);  //调用本类的其它构造方法（下面带参的Demo方法），它必须作为构造方法的第一句
    }

    public JavaThis(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void say(){
        System.out.println("网站的名字是" + name + "，已经成立了" + age + "年");
    }

    public static void main(String[] args) {
        JavaThis obj = new JavaThis();
        obj.say();
    }
}
