package TypeInfo.Reflect;
import TypeInfo.Reflect.Bean.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
/**
 * Created by Defias on 2017/8/23.
 *
 * 反射
 */


public class TestReflection {

    public static void main(String[] args) throws Exception {

        Class<?> person1 = Class.forName("TypeInfo.Reflect.Bean.Person"); //根据类的完整路径名获取Class对象
        Class<?> person2 = (new Person()).getClass();  //通过对象本身获取其Class对象
        Class<?> person3 = Person.class;  //通过类名获取Class对象

        System.out.println("类名称   " + person1.getName());
        System.out.println("类名称   " + person2.getName());
        System.out.println("类名称   " + person3.getName());

        //newInstance:会调用类的默认无参构造函数
        //（当把Person中的默认的无参构造函数取消，比如自己定义只定义一个有参数的构造函数之后，会出现错误）
        Person per = (Person)person1.newInstance();

        per.setName("Rollen");
        per.setAge(20);
        System.out.println(per);
        System.out.println("-------------");

        Person per0 = null;
        Person per1 = null;
        Person per2 = null;
        Person per3 = null;
        Person per4 = null;

        //通过反射获取类的构造方法（获取的构造方法的形参类型为String和int）
        Constructor con = person1.getConstructor(String.class, int.class);
        System.out.println("构造方法：  " + con);

        //调用方法之前先将此对象的accessible标志设置为true，以取消Java语言访问检查，可以提升反射速度，可以访问私有方法
        con.setAccessible(true);
        per0 = (Person)con.newInstance("小明", 20);  //通过调用newInstance方法来创建一个对象

        System.out.println("解析构造方法：  ");
        //getModifiers方法作为整数返回由此Member所表示的成员或构造方法的Java语言修饰符
        int mo = con.getModifiers();
        System.out.print(Modifier.toString(mo)+" ");  //Modifier类提供了static方法和常量对类和成员访问修饰符进行解码
        System.out.print(con.getName());   //仅构造方法名
        System.out.print("(");
        Class<?> p[] = con.getParameterTypes();  //构造方法的参数类型列表
        for(int j=0; j<p.length; ++j){
            System.out.print(p[j].getName()+" arg"+(j+1));
            if(j<p.length-1) {
                System.out.print(", ");
            }
        }
        System.out.println("){}");
        System.out.println("-------------");

        //通过反射获取取得全部的构造方法
        Constructor[] cons = person1.getConstructors();
        for(int i=0; i<cons.length; i++) {
            System.out.println(cons[i]);
        }

        per1 = (Person)cons[3].newInstance();
        per2 = (Person)cons[2].newInstance("Rollen");
        per3 = (Person)cons[1].newInstance(20);
        per4 = (Person)cons[0].newInstance("Rollen",20);

        System.out.println(per0);
        System.out.println(per1);
        System.out.println(per2);
        System.out.println(per3);
        System.out.println(per4);
    }
}


