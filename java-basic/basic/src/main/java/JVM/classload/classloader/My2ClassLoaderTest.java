package JVM.classload.classloader;
/**
 * Created by Defias on 2017/10/9.
 */
public class My2ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        My2ClassLoader loadera = new My2ClassLoader("loadera");
        loadera.setPath("/Users/yzh/Code/JavaDemo/java-basic/src/main/temp/JVM/Classload/example/");

        My2ClassLoader loaderb = new My2ClassLoader(loadera, "loaderb");
        loaderb.setPath("/Users/yzh/Code/JavaDemo/java-basic/src/main/temp/JVM/Classload/example/");

        test(loadera);
        test(loaderb);
    }

    public static void test(ClassLoader loader) throws Exception {
        Class objClass = loader.loadClass("JVM.classload.element.Programmer");  //类名一定要带上包
        Object obj = objClass.newInstance();
        try {
            //调用方法
            objClass.getMethod("code", null).invoke(obj, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}