package Basic.Enum;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

import static Basic.Print.print;
import static Basic.Print.printnb;
/**
 * Created by Defias on 2020/07.
 * Description: 枚举 - values()的神秘之处
 *
 * Enum类并没有实现values()方法，具体的枚举类中的values()方法是由编译器添加的static方法
 * 编译器还为其添加了只需一个参数的valueOf()方法， 原Enum类中的valueOf()方法是需要两个参数的
 */

enum Explore { HERE, THERE }

public class TestEnumReflection {
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1() {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        print("Explore.containsAll(Enum)? " + exploreMethods.containsAll(enumMethods));
        printnb("Explore.removeAll(Enum): ");
        exploreMethods.removeAll(enumMethods);
        print(exploreMethods.toString());
        System.out.println("----------------");
    }

    public static void test2() {
        Explore[] vals = Explore.values();
        Enum e = Explore.HERE; // Upcast 将枚举向上转型为Enum类，那么values()方法就无法访问了
        // e.values(); // No values() in Enum

        //getEnumConstants: 以声明顺序返回一个数组，该数组包含构成此Class对象所表示的枚举类的值，或者在此Class对象不表示枚举
        //类型时返回null
        for(Enum en : e.getClass().getEnumConstants())
            System.out.println(en);
    }


    public static Set<String> analyze(Class<?> enumClass) {
        print("----- Analyzing " + enumClass + " -----");
        print("Interfaces:");
        for(Type t : enumClass.getGenericInterfaces())
            print(t.toString());

        print("Base: " + enumClass.getSuperclass());

        print("Methods: ");
        Set<String> methods = new TreeSet<String>();
        for(Method m : enumClass.getMethods())
            methods.add(m.getName());
        print(methods.toString());
        return methods;
    }

}
