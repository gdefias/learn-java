package JVM.classload.classloader;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by Defias on 2020/09.
 * Description: 类加载器


 系统类加载器的父加载器是扩展类加载器。但输出中扩展类加载器的父加载器是null，因为父加载器不是java实现的，是C++实现的，所以获取不到
 但扩展类加载器的父加载器是根加载器
 */
public class ClassloaderPropTest {
    public static void main(String[] args) throws IOException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("应用/系统类加载器：" + systemClassLoader);

        Enumeration<URL> eml = systemClassLoader.getResources("");
        while (eml.hasMoreElements()){
            System.out.println(eml.nextElement());
        }

        ClassLoader extensionLoader = systemClassLoader.getParent();
        System.out.println("应用/系统类的父加载器是扩展类加载器：" + extensionLoader);
        System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载器的父类加载器：" + extensionLoader.getParent());
    }
}
