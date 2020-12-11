package JVM.classload.classloader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import JVM.classload.element.*;
/**
 * Created by Defias on 2017/9/13.
 *
 * 网络类加载器：
 * Java字节代码（.class）文件存放在服务器上，客户端通过网络的方式获取字节代码并执行
 * 好处：当有版本更新的时候，只需要替换掉服务器上保存的文件即可
 *
 */
public class NetworkClassLoader extends ClassLoader {
    private String rootUrl;

    public static void main(String[] args) {
        String url = "http://localhost:8080/ClassloaderTest/classes";
        NetworkClassLoader ncl = new NetworkClassLoader(url);
        String basicClassName = "com.example.CalculatorBasic";
        String advancedClassName = "com.example.CalculatorAdvanced";
        try {
            Class<?> clazz = ncl.loadClass(basicClassName);
            ICalculator calculator = (ICalculator) clazz.newInstance();
            System.out.println(calculator.getVersion());

            clazz = ncl.loadClass(advancedClassName);
            calculator = (ICalculator) clazz.newInstance();
            System.out.println(calculator.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NetworkClassLoader(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        try {
            URL url = new URL(path);
            InputStream ins = url.openStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        return rootUrl + "/"
                + className.replace('.', '/') + ".class";
    }
}
