package TypeInfo.Reflect;
import TypeInfo.Reflect.Bean.*;
import java.lang.reflect.*;
/**
 * Created by Defias on 2017/8/23.
 *
 * 属性
 */

public class TestReflectionField {

    public static void main(String[] args) {

        Class<?> demo1 = null;
        try{
            demo1 = Class.forName("TypeInfo.Reflect.Bean.FFather");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Field field1 = demo1.getDeclaredField("father");  //通过反射获取当前类中指定属性（public和private都可以）
            System.out.println("获取的指定属性： " + field1.getName());
            Type type = field1.getGenericType();
            System.out.println(type.toString());

            Class<?> dd = field1.getType();
            Object cc = (Father)dd.newInstance();
            System.out.println(cc);

            Father tt = (Father)cc;
            tt.printt();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("===============================================");
        Class<?> demo=null;
        try{
            demo = Class.forName("TypeInfo.Reflect.Bean.CPerson");
        }catch (Exception e) {
            e.printStackTrace();
        }

        Class<?> intes[] = demo.getInterfaces();  //通过反射获取所有该类实现的接口
        for (int i=0; i<intes.length; i++) {
            System.out.println("实现的接口   " + intes[i].getName());
        }

        Class<?> temp = demo.getSuperclass();  //通过反射取得父类
        System.out.println("继承的父类为：   "+temp.getName());


        System.out.println("===============本类的属性========================");
        try {
            Field field1 = demo.getDeclaredField("address");  //通过反射获取当前类中指定属性（public和private都可以）
            System.out.println("获取的指定属性： " + field1.getName());

            //通过反射操作属性
            CPerson person = (CPerson)demo.newInstance();
            field1.set(person, "北京");
            System.out.println(field1.get(person));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("当前类中定义的所有属性：");
        Field[] field = demo.getDeclaredFields();  //通过反射获取当前类中定义的所有属性（不包括从父类继承来的属性）
        for (int i=0; i<field.length; i++) {
            int mo = field[i].getModifiers();  //权限修饰符
            String priv = Modifier.toString(mo);

            Class<?> type = field[i].getType();  //属性类型
            System.out.println(priv + " " + type.getName() + " " + field[i].getName() + ";");
        }

        System.out.println("===============当前类或父类的public属性========================");
        try {
            Field field2 = demo.getField("age");  //通过反射获取当前类或父类中指定public属性
            System.out.println("获取的指定属性： " + field2.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println("当前类或父类中定义的所有public属性：");
        Field[] fields = demo.getFields();  //通过反射获取当前类和父类的所有public属性
        for (int j=0; j<fields.length; j++) {
            int mo = fields[j].getModifiers();  //权限修饰符
            String priv = Modifier.toString(mo);

            Class<?> type = fields[j].getType();    //属性类型
            System.out.println(priv + " " + type.getName() + " " + fields[j].getName() + ";");
        }
    }
}
