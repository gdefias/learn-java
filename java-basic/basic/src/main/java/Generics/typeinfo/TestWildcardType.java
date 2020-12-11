package Generics.typeinfo;

import sun.jvm.hotspot.utilities.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:  WildcardType接口
 * User: Defias
 * Date: 2018-05
 *
 */
public class TestWildcardType {
    private List<? extends Number> a;  //a没有下界, 取下界会抛出ArrayIndexOutOfBoundsException
    private List<? super String> b;

    public static void main(String[] args) throws Exception {
        Field fieldA = TestWildcardType.class.getDeclaredField("a");
        Field fieldB = TestWildcardType.class.getDeclaredField("b");

        // 先拿到泛型类型
        Assert.that(fieldA.getGenericType() instanceof ParameterizedType, "");
        Assert.that(fieldB.getGenericType() instanceof ParameterizedType, "");
        ParameterizedType pTypeA = (ParameterizedType) fieldA.getGenericType();
        ParameterizedType pTypeB = (ParameterizedType) fieldB.getGenericType();

        // 再从泛型里拿到通配符类型
        Assert.that(pTypeA.getActualTypeArguments()[0] instanceof WildcardType, "");
        Assert.that(pTypeB.getActualTypeArguments()[0] instanceof WildcardType, "");
        WildcardType wTypeA = (WildcardType) pTypeA.getActualTypeArguments()[0];
        WildcardType wTypeB = (WildcardType) pTypeB.getActualTypeArguments()[0];

        // 方法测试
        System.out.println(wTypeA.getUpperBounds()[0]);   // class java.lang.Number
        System.out.println(wTypeB.getLowerBounds()[0]);   // class java.lang.String

        // 看看通配符类型到底是什么, 打印结果为: ? extends java.lang.Number
        System.out.println(wTypeA);
        System.out.println(wTypeB);
    }
}
