/**
 * Created by Defias on 2016/2/27.
 *
 * 访问修饰符Modifier
 *
 * Java的修饰符分为访问修饰符和非访问修饰符，访问修饰符也叫访问控制符，是指能够控制类、成员变量、方法的使用权限的关键字
 *
 * 修饰符说明：
 * public
 * 1）公有的，对所有类可见
 * 2）被声明为public的类、方法、构造方法和接口能够被任何其他类访问
 * 3）由于类的继承性，类所有的公有方法和变量都能被其子类继承
 * 4）接口里的变量都隐式声明为public static final，而接口里的方法默认情况下访问权限为public
 *
 * protected
 * 1）受保护的，对同一包内的类和所有子类可见
 * 2）被声明为protected的变量、方法和构造方法能被同一个包中的任何其他类访问，也能够被不同包中的子类访问（不同包中的非子类不能访问）
 * 3）protected修饰符不能修饰类和接口
 * 4）方法和成员变量能够声明为protected，但是接口的成员变量和成员方法不能声明为protected
 * 5）如果只想让该方法对其所在类的子类可见，则将该方法声明为protected
 *
 * 默认不使用任何修饰符
 * 1）在同一包内可见
 * 2）不使用任何修饰符声明的属性和方法，对同一个包内的类是可见的
 *
 * private
 * 1）私有的，在同一类内可见
 * 2）被声明为private的方法、变量和构造方法只能被所属类访问，并且类和接口不能声明为private
 * 3）声明为私有访问类型的变量只能通过类中公共的Getter/Setter方法被外部类访问
 * 4）private修饰符的使用主要用来隐藏类的实现细节和保护类的数据
 *
 */

package Basic;
public class JavaModifier {
    private String name;
    private int age;
    private int pd;

    public String getName() {  //Getter方法
        return name;
    }

    public void setName(String name) {   //Setter方法
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    protected int setpd(int pd) {
        return this.pd = pd;
    }

    protected int getpd() {
        return pd;
    }

    public static void main(String[] args) {
        JavaModifier O = new JavaModifier();
        O.setpd(123);
        System.out.println(O.getpd());
    }
}


