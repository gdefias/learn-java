package Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Defias on 2017/9/6.
 *
 * 自动调用框架的基类
 */

public class BaseClass {
    Map<String, Method> methodCache = new HashMap<String, Method>();

    public BaseClass() {
        Class<? extends BaseClass> myclass = this.getClass();
        System.out.println(myclass.getName());
        Method methods[] = myclass.getMethods();
        for (Method m: methods) {
            AutoCall autocall = m.getDeclaredAnnotation(AutoCall.class);
            if (autocall != null) {
                methodCache.put(autocall.name(), m);
            }
        }
    }

    public Method findMethod(String name) {
        if (name == null)
            return null;
        return methodCache.get(name);
    }

    void CallFunc(String name, Object... objects) {
        Method m = findMethod(name);
        try {
            m.invoke(this, objects);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    void CallFunc(Method m, Object... objects) {
        try {
            m.invoke(this, objects);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
