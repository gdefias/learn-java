package Basic;

/**
 * Created by Defias on 2020/07.
 * Description: 使用内部类实现"多重继承"
 *
 */

public class JavaInnerC5 {
    public static void main(String[] args) {
        Z z = new Z();
        MultiImplementation.takesD(z);
        MultiImplementation.takesE(z.makeF());
    }
}


class D {}  //普通类
abstract class F {}  //抽象类

class Z extends D {  //相当于多重继承了D和F
    F makeF() { return new F() {}; }
}

class MultiImplementation {
    static void takesD(D d) {}
    static void takesE(F e) {}
}