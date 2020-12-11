/**
 * Created by Defias on 2016/2/27.
 *
 * 字符串
 *
 UNICODE标准定义了UTF-8、UTF-16、UTF-32三种编码格式，共有UTF-8、UTF-16、UTF-16BE、UTF-16LE、UTF-32、UTF-32BE、
 UTF-32LE七种编码方案。JAVA所采用的编码方案是UTF-16BE。
 英文字母：A
 字节数：1;编码：GB2312
 字节数：1;编码：GBK
 字节数：1;编码：GB18030
 字节数：1;编码：ISO-8859-1
 字节数：1;编码：UTF-8
 字节数：4;编码：UTF-16
 字节数：2;编码：UTF-16BE
 字节数：2;编码：UTF-16LE

 中文汉字：人
 字节数：2;编码：GB2312
 字节数：2;编码：GBK
 字节数：2;编码：GB18030
 字节数：1;编码：ISO-8859-1
 字节数：3;编码：UTF-8
 字节数：4;编码：UTF-16
 字节数：2;编码：UTF-16BE
 字节数：2;编码：UTF-16LE


 StringBuffer
 String的值是不可变的，每次对String的操作都会生成新的String对象，不仅效率低，而且耗费大量内存空间
 StringBuffer类和String类一样，也用来表示字符串，但是StringBuffer的内部实现方式和String不同，在进行字符串处理时，不生成新的
 对象，在内存使用上要优于String
 StringBuffer默认分配16字节长度的缓冲区，当字符串超过该大小时，会自动增加缓冲区长度，而不是生成新的对象
 StringBuffer类中的方法主要偏重于对于字符串的操作，例如追加、插入和删除等。如果需要对一个字符串进行频繁的修改，建议使用StringBuffer

 StringBuilder
 StringBuilder类和StringBuffer类功能基本相似，方法也差不多，主要区别在于StringBuffer类的方法是多线程安全的，而StringBuilder
 不是线程安全的。StringBuffer、StringBuilder、String中都实现了CharSequence接口


 StringBuffer：线程安全
 StringBuilder：线程不安全

 适用场景：
 操作少量的数据使用 String
 单线程操作大量数据使用 StringBuilder
 多线程操作大量数据使用 StringBuffer
 */
package Basic.String;

import TEST.test2;

import java.util.Arrays;

import static Basic.Print.print;

public class TestString {
	public static String knights = "Then, when you have found the shrubbery, you must " +
			"cut down the mightiest tree in the forest... " +
			"with... a herring!";

	public static void main(String[] arg) throws Exception {

		test0();
		//test1();
		//test2();
		//test3();
		//test4();
		//test5();
	}


	public static void test0() {
		String str1 = "aa";    //永久代(常量池)
		String str2 = new String("aa"); //堆
		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));


		//字符串不可变性
		String q = "howdy";
		print(q); // howdy
		String qq = upcase(q);
		print(qq); // HOWDY
		print(q); // howdy
	}

	public static String upcase(String s) {
		return s.toUpperCase();
	}


	//字符串构造方法
    public static void test1() {
		String s1 = new String();  //创建一个内容为空的字符串
		System.out.println(s1);

		String s2 = new String("sdfsdfsdfs");  //字符串参数指定字符串的内容
		System.out.println(s2);

		char[] cs = new char[] {'h', 'e', 'l', 'l', 'o'};  //字符数组参数指定字符串的内容
		String s3 = new String(cs);
		System.out.println(s3);

		char[] cs1 = new char[] {'w','e', 'r', 'y'};
		String s31 = String.valueOf(cs1);  //通过valueOf方法由数组构造字符串
		System.out.println(s31);

		String s = "fs123fdsa";
		byte b[] = s.getBytes();
		System.out.println(b);
		String s4 = new String(b); //由字节数组构造字符串(根据本地平台默认的字符编码)
		System.out.println(s4);

		//String s5 = new String(b, "GBK");   //由字节数组构造字符串(根据第二个参数指定的字符编码)
	}

	//字符串操作
	public static void test2() {
		//字符串的长度
		String str1 = new String();
		str1 = "微学苑abc";
		String str2 = "weixueyuan";
		System.out.println("The lenght of str1 is " + str1.length());
		System.out.println("The lenght of str2 is " + str2.length());
		System.out.println("The Bytes lenght of str1 is " + str1.getBytes().length);
		System.out.println("The Bytes lenght of str2 is " + str2.getBytes().length);
		System.out.println("1----------------");

		//按照索引值获得字符串中的指定字符
		String str3 = "123456789";
		System.out.println(str3.charAt(0) + "    " + str3.charAt(5) + "    " + str3.charAt(8));

		//检测字符串是否包含某个子串
		String str4 = "weixianyuan";
		System.out.println(str4.contains("xian"));
		System.out.println("2----------------");

		//字符串的正则匹配
		System.out.println("-1234".matches("-?\\d+"));
		System.out.println("5678".matches("-?\\d+"));
		System.out.println("+911".matches("-?\\d+"));
		System.out.println("+911".matches("(-|\\+)?\\d+"));
		System.out.println("3----------------");


		//替换字符串中所有指定的子串
		String str5 = "The url of weixueyuan is www.weixueyuan.net!";
		String str6_1 = str5.replace("weixueyuan", "微学苑");  //替换所有匹配到的
		String str6_2 = str5.replaceAll("weixueyuan", "微学苑");  //替换所有匹配到的，第一个参数支持正则表达式
		String str6_3 = str5.replaceFirst("weixueyuan", "微学苑");  //替换第一次匹配到的，第一个参数支持正则表达式
		System.out.println(str6_1);
		System.out.println(str6_2);
		System.out.println(str6_3);

		String sName = "Java转义字符(补遗)";
		sName = sName.replaceFirst("\\(补遗\\)","");
		System.out.println(sName);

		String s = knights;
		System.out.println(s);
		System.out.println(s.replaceFirst("f\\w+", "located"));
		System.out.println(s.replaceAll("shrubbery|tree|herring","banana"));
		System.out.println("4----------------");


		//以指定字符串作为分隔符，对当前字符串进行分割，分割的结果是一个数组
		String str7 = "wei_xue_yuan_is_good";
		String[] str7Array = str7.split("_");  //参数支持正则表达式
		System.out.println(Arrays.toString(str7Array));

		String stro2 = "a&b&c";
		String strs[] = stro2.split("&");
		//String strs[] = stro2.split("\\u0024");
		for(String str:strs) {
			System.out.println(str);
		}

		System.out.println(Arrays.toString(knights.split(" "))); //按空格划分字符串
		System.out.println(Arrays.toString(knights.split("\\W+"))); //按非单词字符划分字符串（标点字符都被删了）
		//System.out.println(Arrays.toString(knights.split("n\\\\W+")));  //？
		System.out.println("5----------------");


		//判断字符串的内容是否相同
		String str32 = "hello";
		String str33 = "h" + "ello";  //编译器内部使用StringBuilder的append方法来完成连接
		String str34 = "Hello";

		System.out.println(str32.equals(str33));
		System.out.println(str32.equals(str34));
		System.out.println(str32.equalsIgnoreCase(str34));  //忽略大小写
		System.out.println("6----------------");

		//按字典次序比较两个字符串的大小
		System.out.println("abbb".compareTo("baaa"));  //<0
		System.out.println("a".compareTo("a"));  //=0
		System.out.println("b".compareTo("a"));  //>0
		System.out.println("7----------------");

		//字符串中检索特定字符或子字符串
		String str35 = "HelloHelloHello";
		System.out.println("==========");
		System.out.println(str35.indexOf("ll"));  //字符串第一次在str35中出现的位置
		System.out.println(str35.indexOf('o')); //字符o第一次在str35中出现的位置
		System.out.println(str35.indexOf('o', 5)); //字符o第一次在str35中出现的位置,从位置5开始
		System.out.println(str35.lastIndexOf('o')); //从末尾开始查找，字符o第一次在str35中出现的位置
		System.out.println("8----------------");

		//字符串拼接
		String str36 = "Hello ";
		String str37 = str36.concat("world!");
		System.out.println(str37);
		System.out.println("9----------------");

		//字符串截取
		String str38 = "123456789";
		String str38sub1 = str38.substring(1);
		String str38sub2 = str38.substring(3, 7); //index: 3 ~ 6
		System.out.println(str38sub1);
		System.out.println(str38sub2);
		System.out.println("10----------------");

		//删除字符串首尾的空格
		String str39 = "  123  456  789 ";
		System.out.println(str39.trim());  //中间的空格不会删除
		System.out.println("11----------------");

		//大小写转换
		System.out.println("Hello".toUpperCase());
		System.out.println("Hello".toLowerCase());
		System.out.println("12----------------");

		//把基本类型转换为字符串
		System.out.println(String.valueOf(-123));
		System.out.println(String.valueOf(-12D));
		System.out.println(String.valueOf(-234F));
	}


	public static void testgetChars() {
		//从字符串中拷贝若干字符到字符数组中
		String str31 = "123456789";
		char[] chars = new char[str31.length()];
		//chars[0] = 'A';

		//从索引0开始取字符直到索引为6-1=5（不含)
		//要复制到chars子数组的字符从索引0处开始，并结束于索引：0 + (6-1)-1 = 4
		str31.getChars(1,6, chars,0);
		System.out.println(chars);  //注意：此处直接输出，不能加toString，也不能与其他字符串进行连接操作


		String Str1 = new String("www.w3big.com");
		char[] Str2 = new char[6];

		try {
			Str1.getChars(4, 10, Str2, 0);
			System.out.print("拷贝的字符串为：" );
			System.out.println(Str2);
		} catch( Exception ex) {
			System.out.println("触发异常...");
		}

	}

	//编码
    public static void test3() {
		System.out.println(System.getProperty("sun.jnu.encoding"));  //字符串的编码类型
		String sss = "人";
		System.out.println("不同字符编码输出");
		System.out.println(sss.getBytes());
		System.out.println(sss.getBytes().length);  //按操作系统默认编码来编码
		try {
			System.out.println(sss.getBytes("UTF-8"));
			System.out.println(sss.getBytes("UTF-8").length);
			System.out.println(sss.getBytes("UTF-16"));
			System.out.println(sss.getBytes("UTF-16").length);
			System.out.println(sss.getBytes("UTF-32"));
			System.out.println(sss.getBytes("UTF-32").length);
			System.out.println(sss.getBytes("GBK"));
			System.out.println(sss.getBytes("GBK").length);
			System.out.println(sss.getBytes("GB2312"));
			System.out.println(sss.getBytes("GB2312").length);
			System.out.println(sss.getBytes("GB18030"));
			System.out.println(sss.getBytes("GB18030").length);
			System.out.println(sss.getBytes("Unicode"));
			System.out.println(sss.getBytes("Unicode").length);
			System.out.println(sss.getBytes("ISO-8859-1"));
			System.out.println(sss.getBytes("ISO-8859-1").length);
			System.out.println(sss.getBytes("UTF-16BE"));
			System.out.println(sss.getBytes("UTF-16BE").length);
			System.out.println(sss.getBytes("UTF-16LE"));
			System.out.println(sss.getBytes("UTF-16LE").length);

		} catch (java.io.UnsupportedEncodingException e) {
			System.out.println(e.getMessage().toString());
		}
	}

	//StringBuffer
	public static void test4() {
		StringBuffer str8 = new StringBuffer();   // 分配16个字节长度的缓冲区
		StringBuffer str9 = new StringBuffer(512);  // 分配512个字节长度的缓冲区
		// 在缓冲区中存放了字符串，并在后面预留了16个字节长度的空缓冲区
		StringBuffer str10 = new StringBuffer("www.weixueyuan.net");

		//向当前字符串的末尾追加内容
		str8.append("weixueyuan");
		str9.append("weixueyuan");
		str10.append("weixueyuan");
		System.out.println("str8: " + str8);
		System.out.println("str9: " + str9);
		System.out.println("str10: " + str10);

		//删除指定位置的字符，并将剩余的字符形成新的字符串（有移动）
		str9.deleteCharAt(0);
		str9.deleteCharAt(3);
		str9.deleteCharAt(6);
		System.out.println("str9: " + str9);
		str9.delete(0,4);  //删除索引值为0~4之间的字符，包括索引值0，但不包括4
		System.out.println("str9: " + str9);

		//在指定位置插入字符串，可以认为是append()的升级版
		str9.insert(1,"xxx");
		System.out.println("str9: " + str9);

		//修改指定位置的字符
		str9.setCharAt(4, '4');
		System.out.println("str9: " + str9);
	}

	//String和StringBuffer的效率
	public static void test5() {
		System.out.println("String和StringBuffer的效率对比：");
		String fragment = "abcdefghijklmnopqrstuvwxyz";

		// 通过String对象
		int times = 10000;
		long timeStart1 = System.currentTimeMillis();
		String str11 = "";
		for (int i=0; i<times; i++) {
			str11 += fragment;
		}
		long timeEnd1 = System.currentTimeMillis();
		System.out.println("String: " + (timeEnd1 - timeStart1) + "ms");


		// 通过StringBuffer
		long timeStart2 = System.currentTimeMillis();
		StringBuffer str22 = new StringBuffer();
		for (int i=0; i<times; i++) {
			str22.append(fragment);
		}
		long timeEnd2 = System.currentTimeMillis();
		System.out.println("StringBuffer: " + (timeEnd2 - timeStart2) + "ms");



		StringBuffer str100 = new StringBuffer("www.weixueyuan.net");
		str100.append("weixueyuan");
		System.out.println("str100: " + str100);


		//StringBuilder
		StringBuilder str101 = new StringBuilder("www.weixueyuan.net");
		str101.append("weixueyuan");
		System.out.println("str100: " + str101);

	}
}
