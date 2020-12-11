/**
 * Created by Defias on 2016/2/26.
 *
 * Demo
 *
 * 虽然Java语言是典型的面向对象编程语言，但其中的八种基本数据类型并不支持面向对象编程，基本类型的数据不具备“对象”的特性——不携带属性、
 * 没有方法可调用。 沿用它们只是为了迎合人类根深蒂固的习惯，并的确能简单、有效地进行常规数据处理
 *
 *
 * 自动转换： byte/char/short -> int ->  long -> float -> double（boolean不参与转换）
 * int转换到float或long转换到double，很有可能会造成精度丢失
 * byte、short、int都是有符号的整数
 *
 * 如操作数之一为double，则另一个操作数先被转化为double，再参与算术运算
 * 如两操作数均不为double，当操作数之一为float，则另一操作数先被转换为float，再参与运算
 * 如两操作数均不为double或float，当操作数之一为long，则另一操作数先被转换为long，再参与算术运算
 * 如两操作数均不为double、float或long，则两操作数先被转换为int，再参与运算
 *
 * 在Java中的小数默认为double类型，不能对boolean类型进行类型转换，整数的默认类型是int
 * 在把容量大的类型转换为容量小的类型（窄转换）时必须使用强制类型转换（转换过程中可能导致溢出或损失精度，例如：当int窄转换到byte时，会丢
 * 掉int的高3个字节（24位），将最低的一个字节（8位）放入byte中）
 * 浮点数到整数的转换是通过舍弃小数得到，而不是四舍五入
 *
 * byte转换到char: 其转换过程为：先将byte宽转换到int，再将int窄转换到char
 * char转换到short: 直接将char的两个字节放入short的两个字节，虽然两个都是2个字节，但是由于一个有符号、一个没符号，所以在转换过程中可能
 * 由一个正数变成了一个负数
 *
 */
package Basic;

public class DemoBaseType {
	public static void main(String[] args) {
		test1();
		System.out.println("\n-------------");

		//类型转换
		short s1 = 1;
		s1 = (short) (s1 + 1);   //必须强制类型转换
		s1 += 1;  //无需强制类型转换，复合赋值+=是自带了隐式的强制类型转换的

		short s2 = 2;
		short s3 = (short) (s1 + s2); //两个short做运算后再进行赋值的话，也需要强转

		byte b1 = (byte) s1;
		int i1 = s1;   //short可以直接赋值给int类型，不用强转

		long l1 = 333;
		float f1 = l1;

		int xx = (int)34.96 + (int)11.6;  //丢失精度
		int xx1 = (int)(34.96 + 11.6);  //丢失精度
		double yy = (double)xx + (double)10 + 1; //提高精度
		System.out.println("xx = " + xx + ", xx1 = " + xx1 + ", yy = " + yy);

		double zz = (double)(xx>yy?xx:yy);   //三目运算符
		System.out.printf("zz = %f ", zz);  //格式输出

		System.out.println("\n-------------");
		System.out.println((int)'\0');

		double k = 1/6d;
		System.out.printf("k = %.5f ", k);


	}

	public static void test1() {
		//字符型
		char cname1 = '您';
		char cname2 = '好';
		System.out.println("我的名字是：" + cname1 + cname2);

		//整型
		short x = 22;
		int y = 023;  //八进制
		long z = 0x22L;  //十六进制
		System.out.println("转换为十进制：x = " + x + "，y = " + y + "，z = " + z);


		//浮点类型
		float m = 22.45f;
		double n = 10;
		System.out.println("计算乘积：" + m + " * " + n + " = " + m*n);

		//布尔类型
		boolean a = 100>10;
		boolean b = 100<10;
		System.out.println("a is ：" + a + "，b is ：" + b);
		if(a) {
			System.out.println("100<10是对的");
		} else {
			System.out.println("100<10是错的");
		}
	}
}

