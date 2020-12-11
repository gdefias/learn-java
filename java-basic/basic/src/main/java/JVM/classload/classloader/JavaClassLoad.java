package JVM.classload.classloader;
/**
 * Created by Defias on 2016/4/27.
 *
 * JVM类加载
 */

public class JavaClassLoad {
	//static {
	//	System.out.println("init InitTester");
	//}

	public static void main(String args[]) {
		Base base;

		//base = new Base();  //new创建类的实例，初始化Base类

		//System.out.println("a=" + Base.a);

		//System.out.println("b=" + Sub.b);

		//System.out.println("a=" + Sub.a);

		//Base[] bases = new  Base[10];   //不会触发Base类初始化

		System.out.println("a=" + Base.a);
	}



	public static class Base {
		static int  a = 1;
		//static final int a=1;
		static {
			System.out.println("init Base");
		}
	}


	public static class Sub extends Base {
		static int b = 1;
		static {
			System.out.println("init Sub");
		}
	}
}











