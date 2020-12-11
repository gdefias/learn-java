package JVM.classload.classloader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Defias on 2020/09.
 * Description: 类加载器与instanceof关键字演示
 *
 * 即使这两个类来源于同一个Class文件，被同一个虚拟机加载，只要加载它们的类加载器不同，那这两个类就必定不相等
 *
 * 本例虚拟机中存在了两个ClassLoaderTest类，一个是由系统应用程序类加载器加载的，另外一个是由自定义的类加载器myLoader加载的
 */
public class ClassLoaderTest {
    public static void main(String[]args)throws Exception{
        ClassLoader myLoader = new ClassLoader(){
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException{
                try{
                    String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is==null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b, 0, b.length);
                } catch(IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("JVM.classload.classloader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof JVM.classload.classloader.ClassLoaderTest);
    }
}


