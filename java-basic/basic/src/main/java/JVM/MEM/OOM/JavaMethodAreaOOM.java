package JVM.MEM.OOM;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * Created by Defias on 2020/09.
 * Description: 演示方法区内存溢出

 借助CGLib直接操作字节码运行时生成了大量的动态类

 VM Args:-XX:PermSize=10M-XX:MaxPermSize=10M
 */
public class JavaMethodAreaOOM {
    public static void main(String[]args){
        while(true){
            Enhancer enhancer=new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[]args, MethodProxy proxy)throws Throwable{
                    return proxy.invokeSuper(obj,args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {}
}