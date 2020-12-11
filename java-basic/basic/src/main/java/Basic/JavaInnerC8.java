package Basic;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: 内部类与继承 - 可以明确的继承内部类
 */

public class JavaInnerC8 {
    public static void main(String[] args) {
        Egg2 e2 = new BigEgg2();
        System.out.println("\n------------\n");
        e2.g();
    }
}


class Egg2 {
    protected class Yolk {
        public Yolk() {
            print("Egg2.Yolk()");
        }

        public void f() {
            print("Egg2.Yolk.f()");
        }
    }

    private Yolk y = new Yolk();

    public Egg2() {
        print("New Egg2()");
    }

    public void insertYolk(Yolk yy) {
        y = yy;
    }

    public void g() {
        y.f();
    }
}

class BigEgg2 extends Egg2 {
    public class Yolk extends Egg2.Yolk {
        public Yolk() {
            print("BigEgg2.Yolk()");
        }

        public void f() {
            print("BigEgg2.Yolk.f()");
        }
    }

    public BigEgg2() {
        insertYolk(new Yolk());
    }
}