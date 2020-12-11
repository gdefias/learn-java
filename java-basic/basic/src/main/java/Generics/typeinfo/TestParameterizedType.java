package Generics.typeinfo;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:  ParameterizedType接口
 * User: Defias
 * Date: 2018-05
 */

public class TestParameterizedType {
    Map<String, String> map;

    public static void main(String[] args) throws Exception {
        Field f = TestParameterizedType.class.getDeclaredField("map");
        System.out.println(f.getGenericType());
        System.out.println(f.getGenericType() instanceof ParameterizedType);  //true

        ParameterizedType pType = (ParameterizedType) f.getGenericType();
        System.out.println(pType.getRawType());
        for (Type type : pType.getActualTypeArguments()) {
            System.out.println(type);
        }
        System.out.println(pType.getOwnerType());  //null
    }
}
