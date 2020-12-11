package TypeInfo.jdkdyproxy;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: 静态代理 VS 动态代理
 *
 * 静态代理
 */

public class Test2SimpleProxyDemo {

    public static void main(String[] args) {
        consumer(new RealInterface());
        consumer(new SimpleProxy(new RealInterface()));
    }

    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }
}




class SimpleProxy implements Interface {
    private Interface proxied;
    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    public void doSomething() {
        print("SimpleProxy doSomething");
        proxied.doSomething();
    }

    public void somethingElse(String arg) {
        print("SimpleProxy somethingElse " + arg);
        proxied.somethingElse(arg);
    }
}





