package JVM.classload.classloader;
/**
 * Created by Defias on 2016/4/27.
 *
 */

public class ClassLoaderTest2 {
	public static void main(String[] args) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        while (cl != null) {
            System.out.println(cl.toString());
			cl = cl.getParent();
		}

		try {
            //Class c = Class.forName("java.lang.Object");
            Class c = ClassLoaderTest2.class;
			cl = c.getClassLoader();  //通过class实例获得类的加载器
			System.out.println("loader is " + cl);

			c = Class.forName("JVM.classload.element.Sample");  //获得代表Sample类的Class实例
			cl = c.getClassLoader();   //获得加载Sample类的加载器
			System.out.println("Sample's loader is " + cl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
