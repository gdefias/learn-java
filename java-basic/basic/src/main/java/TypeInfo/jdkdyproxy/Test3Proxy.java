package TypeInfo.jdkdyproxy;
import java.lang.reflect.*;
import java.util.*;
/**
 * Created with IntelliJ IDEA.
 * Description: 动态代理
 * User: Defias
 * Date: 2018-05
 *
 * 接口：java.lang.Comparable
 * 方法：compareTo
 * 实现类：Integer
 */


public class Test3Proxy {

    public static void main(String[] args) {
        //boolean debug = false;
        boolean debug = true;

        Comparable[] elements = new Comparable[1000];
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;

            //处理器对象
            InvocationHandler handler = new TraceHandler(value, debug);

            //代理对象
            Comparable proxy = (Comparable) Proxy.newProxyInstance(
                    Integer.class.getClassLoader(),
                    new Class[] { Comparable.class } ,
                    handler);

            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length) + 1;
        System.out.println("key: " + key);

        //二分查找会调用elements数组中Integer元素对象的比较方法compareTo，实际被代理到调用TraceHandler对象的invoke方法
        int result = Arrays.binarySearch(elements, key);
        System.out.println("result: " + result);
    }
}



class TraceHandler implements InvocationHandler {
    private Comparable target;
    private boolean debug = false;

    public TraceHandler(Comparable t) {
        target = t;
    }

    public TraceHandler(Comparable t, boolean debug) {
        this.target = t;
        this.debug = debug;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
       if(debug) {
           System.out.print(target);
           System.out.print("." + m.getName() + "(");
           if (args != null) {
               for (int i = 0; i < args.length; i++) {
                   System.out.print(args[i]);
                   if (i < args.length - 1)
                       System.out.print(", ");
               }
           }
           System.out.println(")");
       }
        return m.invoke(target, args);
    }
}