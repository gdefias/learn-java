package Basic.Enum;
import static Basic.Print.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.EnumSet;

/**
 * Created by Defias on 2017/8/16.
 *
 * 枚举 - 常量相关方法
 *
 * 允许为enum实例编写方法，从而为每个enum实例赋予各自不同的行为（包含抽象方法的枚举类）
 * 也允许为enum实例覆盖非抽象方法
 */

public class TestEnum6 {
    public static void main(String[] args) {
//        test0();
//        test1();
        test2();
//        test3();
    }

    public static void test0() {
        System.out.println("6 + 2 = " + Operation.PLUS.calculate(6, 3));
        System.out.println("6 - 2 = " + Operation.MINUS.calculate(6, 2));
        System.out.println("6 * 2 = " + Operation.TIMES.calculate(6, 2));
        System.out.println("6 / 2 = " + Operation.DIVIDE.calculate(6, 2));
        System.out.println();
    }

    public static void test1() {
        for(ConstantSpecificMethod csm : ConstantSpecificMethod.values())
            System.out.println(csm.getInfo());
    }


    public static void test2() {
        CarWash wash = new CarWash();
        print(wash.toString());
        wash.washCar();
        // Order of addition is unimportant:
        wash.add(CarWash.Cycle.BLOWDRY);
        wash.add(CarWash.Cycle.BLOWDRY); // Duplicates ignored
        wash.add(CarWash.Cycle.RINSE);
        wash.add(CarWash.Cycle.HOTWAX);
        print(wash.toString());
        wash.washCar();
    }

    public static void test3() {
        for(OverrideConstantSpecific ocs : OverrideConstantSpecific.values()) {
            printnb(ocs + ": ");
            ocs.f();
        }
    }
}


//计算器（加减乘除）
enum Operation {
    PLUS { //花括号部分其实是一个匿名内部子类
        @Override
        public double calculate(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        @Override
        public double calculate(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        @Override
        public double calculate(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        public double calculate(double x, double y) {
            return x / y;
        }
    };

    //为该枚举类定义一个抽象方法，枚举类中所有的枚举值都必须实现这个方法
    public abstract double calculate(double x, double y);
}



enum ConstantSpecificMethod {
    DATE_TIME {
        String getInfo() {
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH {
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },
    VERSION {
        String getInfo() {
            return System.getProperty("java.version");
        }
    };

    abstract String getInfo();
}



class CarWash {
    public enum Cycle {
        UNDERBODY {
            void action() { print("Spraying the underbody"); }
        },
        WHEELWASH {
            void action() { print("Washing the wheels"); }
        },
        PREWASH {
            void action() { print("Loosening the dirt"); }
        },
        BASIC {
            void action() { print("The basic wash"); }
        },
        HOTWAX {
            void action() { print("Applying hot wax"); }
        },
        RINSE {
            void action() { print("Rinsing"); }
        },
        BLOWDRY {
            void action() { print("Blowing dry"); }
        };
        abstract void action();
    }

    EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);

    public void add(Cycle cycle) {
        cycles.add(cycle);
    }

    public void washCar() {
        for(Cycle c : cycles)
            c.action();
    }

    public String toString() {
        return cycles.toString();
    }

}




enum OverrideConstantSpecific {
    NUT, BOLT,
    WASHER {
        void f() { print("Overridden method"); }
    };

    void f() {
        print("default behavior");
    }
}