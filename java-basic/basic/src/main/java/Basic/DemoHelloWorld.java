/**
 * hello world
 * */
package Basic;
import java.util.Date;

public class DemoHelloWorld {   //Java中主类名应该和要保存的Java文件名相同

	public static int reverseInteger(int n) {
		// Write your code here
		String strn = n + "";
		String reltstr = "";
		int result;
		int i;
		for(i=strn.length()-1; i>=0; i--) {
			System.out.println("strn: " + strn);
			System.out.println("i: " + i);
			reltstr = reltstr + strn.charAt(i);

		}
		result = Integer.parseInt(reltstr);
		return result;

	}

	public static void main(String[] args) { //Java中的主运行方法，要执行Java程序，必须有一个包括主运行方法的类
		System.out.println("hello world!");
//		int n = 0;
//		int result = reverseInteger(n);
//		System.out.println(result);

		int one = 2011456;
		double two = 15645643.015145;
		String s = String.format("test %,d out of %,.2f", one, two);   //格式化
		System.out.println(s);

		Date today = new Date();
		String d = String.format("%tc", today);
		String d1 = String.format("%tA, %<tB %<td", today);
		System.out.println(d);
		System.out.println(d1);

	}
}