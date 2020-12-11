package TypeInfo.cglibdyproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


import java.lang.reflect.Method;

/**
 * Created by Defias on 2017/9/5.
 *
 * 需求变更： 这些方法不能开放给用户，只有“张三”才有权使用
 *
 * CGLib动态代理
 *
 * CGlib是一个强大的,高性能,高质量的Code生成类库。它可以在运行期扩展Java类与实现Java接口。实际的功能是asm（Java字节码操控框架）所提供的
 * CGlib封装了asm，简化了asm的操作，实现了在运行期动态生成新的class
 * CGlib为spring aop提供了底层的一种实现，为hibernate使用cglib动态生成VO/PO (接口层对象)
 *
 * 优点：无需接口  无入侵的类代理
 * 缺点：由于CGLib采用动态创建子类的方式生成代理对象，所以不能对目标类中的final和private方法进行代理
 *
 */

public class Test1Proxy2 {
    public static void main(String[] args) {
        haveAuth();  //张三有权访问
        haveNoAuth();  //李四无权访问


    }

    public static void haveAuth(){
        TableDAO tDao = TabledaoFactory2.getAuthInstance(new AuthProxy("张三"));
        doMethod(tDao);
    }

    public static void haveNoAuth(){
        TableDAO tDao = TabledaoFactory2.getAuthInstance(new AuthProxy("李四"));
        doMethod(tDao);
    }

    public static void doMethod(TableDAO dao) {
        dao.create();
        dao.query();
        dao.update();
        dao.delete();
    }
}


//DAO工厂
class TabledaoFactory2 {
    public static TableDAO getAuthInstance(AuthProxy authProxy){
        //CGLib的字节码增强器
        Enhancer en = new Enhancer();

        //继承被代理类
        en.setSuperclass(TableDAO.class);

        //设置回调
        //原有类的每个方法调用都会转成调用实现了MethodInterceptor接口的proxy类（authProxy）的intercept()方法
        en.setCallback(authProxy);

        //生成代理对象（通过字节码技术动态创建子类实例）
        //代理对象的生成过程由Enhancer类实现，大概步骤如下：
        //1、生成代理类Class的二进制字节码；
        //2、通过Class.forName加载二进制字节码，生成Class对象；
        //3、通过反射机制获取实例构造，并初始化代理类对象。
        return (TableDAO)en.create();

    }
}


//代理类：方法拦截器
class AuthProxy implements MethodInterceptor {
    private String name ;

    public AuthProxy(String name){
        this.name = name;
    }

    //拦截父类所有方法的调用
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        //对用户进行过滤
        if(!"张三".equals(name)){
            System.out.println("你没有权限！");
            return null;
        }
        System.out.println("====before====");
        Object object = proxy.invokeSuper(o, args);  //执行原有函数 通过代理类调用父类中的方法
        System.out.println("====after====");
        return object;
    }
}

