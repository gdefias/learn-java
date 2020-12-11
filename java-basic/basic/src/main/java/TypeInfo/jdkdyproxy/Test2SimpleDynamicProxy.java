package TypeInfo.jdkdyproxy;
import java.lang.reflect.*;

/**
 * Created by Defias on 2020/07.
 * Description: JDK动态代理
 */

public class Test2SimpleDynamicProxy {

    public static void main(String[] args) {
        RealInterface real = new RealInterface();
        consumer(real);

        Interface proxy = (Interface)Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{ Interface.class },
                new DynamicProxyHandler(real));
        consumer(proxy);
    }

    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

}


class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;
    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }
    public Object
    invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean debug = false;
        if(debug) {
            System.out.println("**** proxy: " + proxy.getClass() + ", method: " + method + ", args: " + args);
            if(args != null)
                for(Object arg : args)
                    System.out.println("  " + arg);
        }

        return method.invoke(proxied, args);
    }
}