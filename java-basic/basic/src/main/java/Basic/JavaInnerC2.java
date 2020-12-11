package Basic;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description:  内部类 - 向上转型、 匿名内部类
 */
public class JavaInnerC2 {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents c = p.contents();
        Destination d = p.destination("Tasmania");
        // Illegal -- can't access private class:
        //! Parcel4.PContents pc = p.new PContents();
    }

    //匿名内部类
    public Contents contents() {
        return new Contents() { // Insert a class definition
            private int i = 11;
            public int value() {
                return i;
            }

        }; // 分号 required in this case
    }

    //匿名内部类 - 参数i在匿名内部类中没有被使用，不要求一定要是final的
    public static Base getBase(int i) {
        return new Base(i) {
            { print("Inside instance initializer"); }
            public void f() {
                print("In anonymous f()");
            }
        };
    }

    //匿名内部类 - 参数dest和price在匿名内部类中使用，要求一定要是final的
    public Destination destination(final String dest, final float price) {
        return new Destination() {
            private int cost;
            // Instance initialization for each object:
            {
                cost = Math.round(price);
                if(cost > 100)
                    System.out.println("Over budget!");
            }
            private String label = dest;
            public String readLabel() { return label; }
        };
    }
}

interface Contents {
    int value();
}

interface Destination {
    String readLabel();
}

abstract class Base {
    public Base(int i) {
        print("Base constructor, i = " + i);
    }
    public abstract void f();
}

class Parcel4 {
    private class PContents implements Contents {
        private int i = 11;
        public int value() { return i; }
    }

    protected class PDestination implements Destination {
        private String label;
        private PDestination(String whereTo) {
            label = whereTo;
        }
        public String readLabel() { return label; }
    }

    public Destination destination(String s) {
        return new PDestination(s); //向上转型
    }

    public Contents contents() {
        return new PContents(); //向上转型
    }
}

