/**
 * Created by Defias on 2016/3/5.
 *
 * 继承
 *
 * 1、继承可以理解为一个类从另一个类获取方法和属性的过程
 * 2、子类可以继承父类除private以外的所有的成员，也可以理解为：子类拥有父类对象所有的属性和方法（包括私有属性和私有方法），但是父类中的
 * 私有属性和方法子类是无法访问，只是拥有
 *
 * 单继承性: Java允许一个类仅能继承一个其它类，即一个类只能有一个父类，这个限制被称做单继承性（接口允许多继承）
 *
 * 继承与super
 * 1. 如果父类的构造器都是带有参数的，则必须在子类的构造器中显示地通过super关键字调用父类的构造器并配以适当的参数列表
 * 2. 如果父类有无参构造器，则在子类的构造器中用super关键字调用父类构造器不是必须的。如果没有使用super关键字，系统会自动调用父类的无参构造器
 *
 *
 * 如非必须（需要向上转型等），不应该优先考虑使用继承，而是组合
 * */
package Basic;

public class JavaExtendsSuper {
    public static void main(String[] args) {
        Teacher t = new Teacher("小布", 70, 180);
        t.school = "清华大学";
        t.subject = "Java";
        t.seniority = 12;
        t.say();
        t.lecturing();

        t.f();  //private方法不会被继承并覆盖
    }
}


class People {
    String name;
    int age;
    int height;

    public People(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    void say() {
        System.out.println("我的名字是 " + name + "，年龄是 " + age + "，身高是 " + height);
    }


    private void f() {
        System.out.println("private f()");
    }
}


class Teacher extends People {  //如果在People类中进行修改，那么Teacher类就会自动修改，而不需要做任何工作，除了对它进行编译
    String school;  // 所在学校
    String subject;  // 学科
    int seniority;  // 教龄

    public Teacher(String name, int age, int height) {   //必须的
        super(name, age, height);
    }

    // 覆盖People类中的say()方法
    void say() {
        System.out.println("我叫" + name + "，在" + school + "教" + subject + "，有" + seniority + "年教龄");
    }

    void lecturing(){
        System.out.println("我已经" + age + "岁了，依然站在讲台上讲课");
    }

    public void f() {
        System.out.println("public f()");
    }
}



