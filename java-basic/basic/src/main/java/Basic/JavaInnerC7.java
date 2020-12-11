package Basic;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: 内部类与继承 - 内部类不会被覆盖
 */

public class JavaInnerC7 {
    public static void main(String[] args) {
        new BigEgg();
    }
}


class Egg {
    private Yolk y;
    protected class Yolk {
        public Yolk() {
            print("Egg.Yolk()");
        }
    }
    public Egg() {
        print("New Egg()");
        y = new Yolk();
    }
}

class BigEgg extends Egg {
    public class Yolk {   //与Egg中的内部类是完全独立的两个实体，各自在自己的命名空间内
        public Yolk() {
            print("BigEgg.Yolk()");
        }
    }
}