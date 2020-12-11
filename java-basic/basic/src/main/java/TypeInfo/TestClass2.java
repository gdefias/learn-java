package TypeInfo;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: Class对象的方法
 */

public class TestClass2 {
    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("TypeInfo.FancyToy");
        } catch(ClassNotFoundException e) {
            print("Can't find FancyToy");
            System.exit(1);
        }
        printInfo(c);

        System.out.println("--------------");
        for(Class face : c.getInterfaces())
            printInfo(face);
        System.out.println("--------------");

        Class up = c.getSuperclass();
        Object obj = null;
        try {
            obj = up.newInstance();  //使用newInstance创建对象对应的类，必须带有默认构造器,否则无法成功创建出实例
        } catch(InstantiationException e) {
            print("Cannot instantiate");
            System.exit(1);
        } catch(IllegalAccessException e) {
            print("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }


    static void printInfo(Class cc) {
        print("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
        print("Simple name: " + cc.getSimpleName());  //简名
        print("Canonical name : " + cc.getCanonicalName());  //全名
    }
}



interface HasBatteries {}
interface Waterproof {}
interface Shoots {}

class Toy {
    Toy() {}
    Toy(int i) {}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
    FancyToy() {
        super(1);
    }
}