package JVM.classload.classloader;
import java.lang.reflect.Method;
/**
 * Created by Defias on 2017/9/13.
 *
 * 判定两个Java类是否相同
 *
 * Java虚拟机判定两个Java类是否相同
 * 不仅要看类的全名是否相同，还要看加载此类的类加载器是否一样
 * 只有两者都相同的情况，才认为两个类是相同的；即便是同样的字节代码，被不同的类加载器加载之后所得到的类也是不同的
 *
 */


public class My1FileClassLoaderTest {
    public static void main(String[] args) {
        new My1FileClassLoaderTest().testClassIdentity();
    }

    public void testClassIdentity() {
        String classDataRootPath = "/Users/yzh/Code/JavaDemo/java-basic/base/target/classes/JVM/classload/element";
        My1FileClassLoader fscl1 = new My1FileClassLoader(classDataRootPath);
        My1FileClassLoader fscl2 = new My1FileClassLoader(classDataRootPath);
        String className = "JVM.classload.element.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();

            System.out.println(obj1==obj2);

            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            Method setSampleMethod2 = class2.getMethod("setSample", java.lang.Object.class);

            System.out.println(setSampleMethod==setSampleMethod2);
            System.out.println("end");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
