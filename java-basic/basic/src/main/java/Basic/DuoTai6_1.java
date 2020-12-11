package Basic;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: 构造器与多态 -  构造器的调用顺序
 *
 * 1、基类构造器（反复递归）
 * 2、按声明顺序调用成员的初始化方法
 * 3、调用导出类构造器的主体
 */


public class DuoTai6_1 extends PortableLunch {
    private Bread b = new Bread();
    private Cheese c = new Cheese();
    private Lettuce l = new Lettuce();

    public DuoTai6_1() {
        print("Sandwich()");
    }

    public static void main(String[] args) {
        new DuoTai6_1();
    }
}


class Meal {
    Meal() { print("Meal()"); }
}

class Bread {
    Bread() { print("Bread()"); }
}

class Cheese {
    Cheese() { print("Cheese()"); }
}

class Lettuce {
    Lettuce() { print("Lettuce()"); }
}

class Lunch extends Meal {
    Lunch() { print("Lunch()"); }
}

class PortableLunch extends Lunch {
    PortableLunch() { print("PortableLunch()");}
}