package TypeInfo.jdkdyproxy;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;

/**
 * Created by Defias on 2017/8/23.

 JDK动态代理

 缺点：需要接口（要求委托类必须实现一个接口，只能对该类接口中定义的方法实现代理，有一定的局限性）

 JDK实现动态代理的大致步骤如下：
    1、定义公共接口和委托/真实实现类
    2、定义一个调用处理器类（java的调用处理器接口【InvocationHandler】的实现类），这个类的目的是指定运行时将生成的代理类需要完成的具体任务
    3、生成代理对象（当然也会生成代理类），需要为他指定:委托类的类加载器、一系列公共接口、调用处理器类的实例。因此可以看出一个代理对象对应一个
    委托对象，对应一个调用处理器实例

 Proxy类主要方法：
 static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)   创建代理对象
 参数：
 ClassLoader：一个ClassLoader对象（定义了由哪个ClassLoader对象来对生成的代理对象进行加载）

 Class<?>[]：一个Interface对象的数组（表示的是将要给需要代理的对象提供一组什么接口，如果提供了一组接口给它，那么这个代理对象就宣称实现了
 这些接口(多态)，这样就能调用这组接口中的方法了）

 InvocationHandler：一个InvocationHandler对象（表示的是当动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上）

 这个函数是JDK为了程序员方便创建代理对象而封装的一个函数，因此调用newProxyInstance()时直接创建了代理对象（略去了创建代理类的代码）
 主要完成了以下几个工作：
 static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler handler) {
     //1. 根据类加载器和接口创建代理类
     Class clazz = Proxy.getProxyClass(loader, interfaces);

     //2. 获得代理类的带参数的构造函数
     Constructor constructor = clazz.getConstructor(new Class[] { InvocationHandler.class });

     //3. 创建代理对象，并指定调用处理器实例为参数传入
     Interface Proxy = (Interface)constructor.newInstance(new Object[] {handler});
 }
 */

public class Test0Proxy  {
    public static void main(String[] args) {
        //1.创建委托对象
        RealSubject realSubject = new RealSubject();

        //2.创建调用处理器对象
        MyInvocationHandler myhandler = new MyInvocationHandler(realSubject);

        //Proxy是生成代理类的主类，通过Proxy类生成的代理类都继承了Proxy类，即DynamicProxyClass extends Proxy

        //3.动态生成代理对象
        //Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
        //        RealSubject.class.getInterfaces(), handler);
        //或：
        Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                new Class[] {Subject.class}, myhandler);

        /*
        为啥可以强制转换为Subject对象？
        原因就是在newProxyInstance这个方法的第二个参数上，我们给这个代理对象提供了一组接口，那么代理对象就会实现了这组接口，
        这个时候当然可以将这个代理对象强制类型转化为这组接口中的任意一个了
        */

        System.out.println(proxySubject.getClass().getName());  //$Proxy0
        // 通过Proxy.newProxyInstance创建的代理对象是在jvm运行时动态生成的一个对象，它并不是InvocationHandler类型，也不是我们定义的那组
        // 接口的类型，而是在运行时动态生成的一个对象，并且命名方式都是这样的形式，以$开头，proxy为中，最后一个数字表示对象的标号

        //4.通过代理对象调用方法
        proxySubject.request();
    }
}



//代理类的调用处理器： 实现InvocationHandler接口的类
//InvocationHandler称为"调用处理器"接口，定义动态生成的代理类需要完成的具体内容
//对于所有的实现了Subject接口的子类来说，这个类是公共的，这也是动态代理相比静态代理最大的优点
class MyInvocationHandler implements InvocationHandler {
    private Subject subject;

    public MyInvocationHandler(Subject subject){  //调用时构造方法中传入接口的实现类对象
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //proxy参数: 所代理的那个真实对象
        //method参数: 所要调用真实对象的某个方法的Method对象
        //args参数: 调用真实对象某个方法时接受的参数
        System.out.println("====before===="); //定义预处理的工作
        Object result = method.invoke(subject, args);
        //result = method.invoke(subject, args);
        System.out.println("====after====");
        return result;
        //return null;
    }
}




