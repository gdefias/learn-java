/**
 * Created by Defias on 2016/2/28.
 *
 * 抽象类
 *
 * 1、只给出方法定义而不具体实现的方法被称为抽象方法，抽象方法是没有方法体的，在代码的表达上就是没有“{}”
 * 2、包含一个或多个抽象方法的类也必须被声明为抽象类
 * 3、抽象类除了包含抽象方法外，还可以包含具体的变量和具体的方法
 * 4、类即使不包含抽象方法，也可以被声明为抽象类，防止被实例化
 * 5、不能有抽象构造方法或抽象静态方法
 * 6、在继承中可以赋值子类对象给父类的引用，即使父类是抽象的
 */
package Basic;

import static java.lang.System.*;


public final class JavaAbstractClass {
    public static void main(String[] args) {
        Teacherr t = new Teacherr();
        t.setName("王明");
        t.work();

        Driverr d = new Driverr();
        d.setName("小陈");
        d.work();
    }
}


// 定义一个抽象类
abstract class Peoplee {
    private String name;  // 实例变量

    // 共有的setter和getter方法
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    // 抽象方法
    public abstract void work();
}

class Teacherr extends Peoplee {
    // 必须实现该方法
    public void work() {
        out.println("我的名字叫" + this.getName() + "，我正在讲课，请大家不要东张西望...");
    }
}

class Driverr extends Peoplee {
    // 必须实现该方法
    public void work() {
        out.println("我的名字叫" + this.getName() + "，我正在开车，不能接听电话...");
    }
}