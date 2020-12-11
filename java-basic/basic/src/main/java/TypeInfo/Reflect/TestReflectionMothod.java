package TypeInfo.Reflect;
import java.lang.reflect.*;
import TypeInfo.Reflect.Bean.CPerson;
/**
 * Created by Defias on 2017/8/23.
 *
 * 方法
 */

public class TestReflectionMothod {
    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("TypeInfo.Reflect.Bean.CPerson");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("===============本类的方法========================");
        Method[] methods = demo.getDeclaredMethods();  //获取到当前类中定义的所有方法（不包括从父类继承来的、包括private、public、static等各种方法）
        for (Method method : methods) {
            System.out.println("declared method name : " + method.getName());
            System.out.println("getDeclaringClass method name : " + method.getDeclaringClass());  //定义该方法的类
        }

        try {
            Method sayHelloMethod = demo.getDeclaredMethod("sayHello", String.class, int.class);  //获得指定的public方法？，调用getDeclaredMethod(String name, Class...<T> parameterTypes)

            Class<?>[] paramClasses = sayHelloMethod.getParameterTypes();  //获取方法的参数列表
            for (Class<?> classi: paramClasses) {
                System.out.println("sayHello方法的参数: " + classi.getName());
            }

            int mo = sayHelloMethod.getModifiers();
            System.out.print(Modifier.toString(mo)+" ");
            System.out.println(sayHelloMethod.getName() + " is private " + Modifier.isPrivate(mo));  //判断方法是否为private

            //方法的调用
            CPerson person = (CPerson)demo.newInstance();
            sayHelloMethod.invoke(person, "XiaoHong", 23);  //实例, 参数, 参数...
            sayHelloMethod.invoke(demo.newInstance(), "Qiushui", 25);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("===============当前类或父类的public方法========================");

        Method[] methodss = demo.getMethods();  //获取所有public方法(包括demo本身的和从父类继承来的）
        for (Method method : methodss) {
            System.out.println("method name : " + method.getName());
        }
        try {

            Method getFatherNameMethod = demo.getMethod("getFatherName");   //获得指定的public方法(包括demo本身的和从父类继承来的） 通过getMethod只能获取public方法?
            System.out.println(getFatherNameMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //调用set和get方法
        try {
            CPerson person1 = (CPerson)demo.newInstance();
            setter(person1, "FatherName", "上帝", String.class);
            getter(person1, "FatherName");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getter(Object obj, String att) {
        try {
            Method method = obj.getClass().getMethod("get" + att);
            System.out.println(method.invoke(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setter(Object obj, String att, Object value, Class<?> type) {
        try {
            Method method = obj.getClass().getMethod("set" + att, type);
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
