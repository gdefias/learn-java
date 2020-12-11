package JVM.MEM.GC;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by Defias on 2016/4/27.
 *
 * Java GC Log
 */

public class TestGCLog {
	private static final Map<String, TestGCLog> ghosts = new HashMap<String, TestGCLog>();
	private final String name;

	public static void main(String args[]) throws Exception {
		TestGCLog ghost = TestGCLog.getInstance("IAmBack"); //①
		System.out.println(ghost.ghosts); //②
		String name = ghost.getName(); //③

		ghost = null; //执行完这条语句后，对象仍然被ghosts属性间接引用着

		TestGCLog.removeInstance(name); //对象不再被程序引用变为无用对象

		System.gc();  //提高垃圾回收机进行垃圾回收操作的可能性，不能保证无用对象的finalize()方法一定会被调用

		//把CPU让给垃圾回收线程
		Thread.sleep(3000);  //⑦
		ghost = TestGCLog.getInstance("IAmBack");  //⑧
		System.out.println(ghost.ghosts);  //⑨

	}

	public TestGCLog(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public static TestGCLog getInstance(String name){
		TestGCLog ghost = ghosts.get(name);
		if (ghost == null) {
			ghost = new TestGCLog(name);
			ghosts.put(name,ghost);
		}
		return ghost;
	}

	public static void removeInstance(String name) {
		ghosts.remove(name);
	}

	protected void finalize() throws Throwable {
		ghosts.put(name,this);  //把对象的引用又加入到HashMap对象中
		System.out.println("execute finalize");
		throw new Exception("Just Test");
	}


}