package JVM.classload.classloader.urlclassloader;
import java.net.URLClassLoader;
import java.net.URL;
import java.io.*;
import java.lang.reflect.*;

/**
 * Created by Defias on 2016/4/27.
 *
 * URLClassLoader
 * ClassLoader的子类，用于从指向JAR文件和目录的URL的搜索路径加载类和资源
 *
 */

public class URLClassLoaderTest {
    public static void main(String args[]) {
        try {
            File file = new File("base/src/main/java/JVM/classload/classloader/urlclassloader/rtest.txt");
            BufferedReader in = new BufferedReader(new FileReader(file));
            String s = new String();
            while ((s = in.readLine()) != null) {
                //从rtest.txt中读取的url，根据url创建类装载器
                URL url = new URL(s);
                s = null;
                URLClassLoader myClassLoader = new URLClassLoader(
                        new URL[] { url }, Thread.currentThread().getContextClassLoader());
                System.out.println(myClassLoader);

                Class myClass = myClassLoader.loadClass("JVM.classload.classloader.urlclassloader.TestAction");
                ActionInterface action = (ActionInterface) myClass.newInstance();
                String str = action.action();
                System.out.println(str);

                //根据url1创建类装载器
                URL url1 = new URL("file:base/src/main/java/JVM/classload/classloader/urlclassloader/TestAction.jar");
                URLClassLoader myClassLoader1 = new URLClassLoader(
                        new URL[] { url1 }, Thread.currentThread().getContextClassLoader());
                Class myClass1 = myClassLoader1.loadClass("JVM.classload.classloader.urlclassloader.TestAction");
                ActionInterface action1 = (ActionInterface) myClass1.newInstance();
                String str1 = action1.action();
                System.out.println(str1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

