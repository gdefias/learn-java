package JVM.MEM.OOM;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-03
 *
 * java内存泄露的理解
 */

public class TestOOM {
    Object object1;

    public void method1() {
        object1 = new Object();
        //这里的object实例，其实期望它只作用于method1()方法中，且其他地方不会再用到它
        //但当method1()方法执行完成后，object对象所分配的内存不会马上被认为是可以被释放的对象
        //只有在TestOutofMem类创建的对象被释放后才会被释放，严格的说，这就是一种内存泄露

        object1 = null;  //解决方法
    }
}
