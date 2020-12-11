package JVM.bytecoderun;

/**
 * Created by Defias on 2020/09.
 * Description: 方法的重写
 *
 * 动态分派  根据变量的动态类型/实际类型分派   运行期由Java虚拟机执行
 *
 *
 *
 *
 *
 */

public class JavaOverrideAssign {

    public static void main(String[] args) {

        //根据变量（man）的动态类型（Man）确定调用Man中的重写方法sayHello()
        Human man = new Man();
        man.sayHello();

        Human woman = new Woman();
        woman.sayHello();

        //根据变量（man）的动态类型（Woman）确定调用Woman中的重写方法sayHello()
        man = new Woman();
        man.sayHello();
    }
}


class Man extends Human {

    @Override
    public void sayHello() {
        System.out.println("man say hello");

    }
}

class Woman extends Human {
    @Override
    public void sayHello() {
        System.out.println("woman say hello");

    }
}

class Human {
    public void sayHello() {
        System.out.println("Human say hello");
    }
}



