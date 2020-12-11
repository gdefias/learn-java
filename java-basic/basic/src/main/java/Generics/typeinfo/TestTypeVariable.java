package Generics.typeinfo;

import sun.jvm.hotspot.utilities.Assert;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created with IntelliJ IDEA.
 * Description: TypeVariable接口
 * User: Defias
 * Date: 2018-05
 */



public class TestTypeVariable <K extends Comparable & Serializable, V> {
    K key;
    V value;
    public static void main(String[] args) throws Exception {
        // 获取字段的类型
        Field fk = TestTypeVariable.class.getDeclaredField("key");
        Field fv = TestTypeVariable.class.getDeclaredField("value");
        Assert.that(fk.getGenericType() instanceof TypeVariable, "必须为TypeVariable类型");
        Assert.that(fv.getGenericType() instanceof TypeVariable, "必须为TypeVariable类型");
        TypeVariable keyType = (TypeVariable)fk.getGenericType();
        TypeVariable valueType = (TypeVariable)fv.getGenericType();

        // getName 方法
        System.out.println(keyType.getName());
        System.out.println(valueType.getName());

        // getGenericDeclaration 方法
        System.out.println(keyType.getGenericDeclaration());
        System.out.println(valueType.getGenericDeclaration());

        // getBounds 方法
        System.out.println("K 的上界:");
        for (Type type : keyType.getBounds()) {
            System.out.println(type);
        }
        System.out.println("V 的上界:");   //没明确声明上界的,默认上界是Object
        for (Type type : valueType.getBounds()) {
            System.out.println(type);
        }
    }
}
