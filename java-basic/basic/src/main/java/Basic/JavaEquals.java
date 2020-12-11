/**
 * Created by Defias on 2016/3/4.
 *
 * 相等
 *
 * 基本数据类型（也称原始数据类型）：
 * byte,short,char,int,long,float,double,boolean。他们之间的比较，应用双等号（==）,比较的是他们的值
 *
 * 引用数据类型
 * 当他们用==进行比较的时候，比较的是他们在内存中的存放地址（确切的说，是堆内存地址）
 *
 * 对于equals方法，不能用于基本数据类型， 当用于引用数据类型时，如果没有对equals方法进行重写，则比较的是引用类型变量所指向对象的地址
 * 诸如String、Date等类对equals方法进行了重写，比较的是所指向的对象的内容
 *
 * 5条性质：
 * 自反性(reflexive) 对于任意不为null的引用值x，x.equals(x)一定是true
 * 对称性(symmetric) 对于任意不为null的引用值x和y，当且仅当x.equals(y)是true时，y.equals(x)也是true
 * 传递性(transitive) 对于任意不为null的引用值x、y和z，如果x.equals(y)是true，同时y.equals(z)是true，那么x.equals(z)一定是true
 * 一致性(consistent) 对于任意不为null的引用值x和y，如果用equals比较的对象信息没有被修改的话，多次调用时x.equals(y)，
 * 要么一致地返回true，要么一致地返回false
 * 对于任意不为null的引用值x，x.equals(null)返回false
 */
package Basic;


public class JavaEquals {
	public static void main(String[] args) {
		int n=3;
		int m=3;
		System.out.println(n==m);  //true

		String str = new String("hello");
		String str1 = new String("hello");
		String str2 = new String("hello");
		System.out.println(str1==str2);  //false String为引用类型，str1和str2是不同的对象，不同的引用，虽然引用的内容相同
		System.out.println(str1.equals(str2));

		str1 = str;
		str2 = str;
		System.out.println(str1==str2);  //true 同一个引用
		System.out.println(str1.equals(str2));

		String str3 = new String("hello");
		String str4 = new String("hello");
		System.out.println(str3==str4);
		System.out.println(str3.equals(str4));  //true  String对equals进行了重写，比较的不再是引用，而是引用所指向的内容

		HE he1 = new HE("111");
		HE he2 = new HE("111");
		System.out.println(he1==he2);
		System.out.println(he1.equals(he2));

	}
}

class HE {
	String name;

	public HE() {
		this.name = "";
	}

	public HE(String name) {
		this.name = name;
	}

//	public boolean equals(HE he) {
//		return (this.name.equals(he.name));
//	}
}