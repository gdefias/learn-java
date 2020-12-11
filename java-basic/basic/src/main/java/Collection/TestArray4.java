package Collection; /**
 * Created by Defias on 2016/4/25.
 *
 * 用Arrays类操纵数组
 */

import Collection.generator.Generated;
import Generics.element_generics.Generator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class TestArray4 {

	public static void main(String args[]) {
		//test1();
		//test2();
		test3();

	}

	//数组比较
	//Arrays.equals 比较整个数组，针对所有基本类型和Object都做了重载  比较的两个数组元素类型需要相同
	public static void test1() {
		int[] a1 = new int[10];
		int[] a2 = new int[10];
		Integer[] a3 = new Integer[10];
		Arrays.fill(a1, 47);
		Arrays.fill(a2, 47);
		Arrays.fill(a3, 47);
		System.out.println(Arrays.equals(a1, a2));
		//! System.out.println(Arrays.equals(a1, a3));

		a2[3] = 11;
		System.out.println(Arrays.equals(a1, a2));

		String[] s1 = new String[4];
		Arrays.fill(s1, "Hi");
		String[] s2 = { new String("Hi"), new String("Hi"),
				new String("Hi"), new String("Hi") };
		System.out.println(Arrays.equals(s1, s2));  //比较的是值而非地址
	}

	//数组排序
	public static void test2() {
		int[] a = {4,5,6,3,9,4};
		Arrays.sort(a);  //排序
		System.out.println("a:"+Arrays.toString(a));
		System.out.println("5在a数组中的位置: "+Arrays.binarySearch(a,5));

		String[] s1 = {"Tom1","J","Mi","Ma","Tom2"};
		String[] s2 = {"Tom1","Jack","Mike","Mary","Tom2"};
		System.out.println("s1是否和s2相等：" + Arrays.equals(s1,s2));

		Arrays.sort(s1);  //为数组s1排序（默认字典序）
		System.out.println(Arrays.toString(s1));

		Arrays.sort(s1, new MyComparator());  //为数组s1排序（自定义）
		//打印s1
		System.out.println(Arrays.toString(s1));

		System.out.println("s1排序后，s1是否和s2相等："+Arrays.equals(s1,s2));

		System.out.println("Jack在s1数组中的位置: "+Arrays.binarySearch(s1,"Jack"));  //二分查找
		System.out.println("Jack在s2数组中的位置: "+Arrays.binarySearch(s2,"Jack"));
		System.out.println("s1:"+Arrays.toString(s1));
		System.out.println("s2:"+Arrays.toString(s2));
	}

	//数组元素的比较
	public static void test3() {
		CompType[] a = Generated.array(new CompType[12], CompType.generator());
		System.out.println("before sorting:");
		System.out.println(Arrays.toString(a));
		Arrays.sort(a);
		System.out.println("after sorting:");
		System.out.println(Arrays.toString(a));
	}


	public static void test4() {
		CompType[] a = Generated.array(new CompType[12], CompType.generator());
		System.out.println("before sorting:");
		System.out.println(Arrays.toString(a));
		Arrays.sort(a, Collections.reverseOrder());  //Collections提供的比较器
		System.out.println("after sorting:");
		System.out.println(Arrays.toString(a));
	}

}


//单独的数组元素比较器
class MyComparator implements Comparator<String> {
	@Override
	public int compare(String s1, String s2) {
		return (s2 + s1).compareTo(s1 + s2); //<=0相对位置不变，>0交换位置
		//return 0;
	}
}


//数组元素类型兼比较器
class CompType implements Comparable<CompType> {
	int i;
	int j;
	private static int count = 1;

	public CompType(int n1, int n2) {
		i = n1;
		j = n2;
	}

	public String toString() {
		String result = "[i = " + i + ", j = " + j + "]";
		if(count++ % 3 == 0)
			result += "\n";
		return result;
	}

	public int compareTo(CompType rv) {
		return (i < rv.i ? -1 : (i == rv.i ? 0 : 1));
	}
	private static Random r = new Random(47);

	public static Generator<CompType> generator() {
		return new Generator<CompType>() {
			public CompType next() {
				return new CompType(r.nextInt(100),r.nextInt(100));
			}
		};
	}
}