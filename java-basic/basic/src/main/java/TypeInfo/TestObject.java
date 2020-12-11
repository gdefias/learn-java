package TypeInfo;

/**
 * Created by Defias on 2016/3/25.
 *
 * Object类
 */

public class TestObject {
	public static void main(String[] args) {
		Integer d = 123;
        int dh = d.hashCode();
        System.out.println(dh);
        System.out.println("------");

        System.out.println(new Object());  //调用对象的toString方法
		System.out.println("------");

		Object Ob = new Object();
		int h = Ob.hashCode();  //哈希码hashcode
		System.out.println(Ob);
		System.out.println(h);
		System.out.println(Ob.toString());
		System.out.println("------");

		System.out.println(new Integer(123));
		System.out.println(new String("123"));
		System.out.println("------");

		Ob = new Object();  //新的对象
		h = Ob.hashCode();  //哈希码hashcode
		System.out.println(Ob);
		System.out.println(h);
		System.out.println(Ob.toString());
        System.out.println("------");

        Object[] objects = new Object[] {1, "2", 3.3, true, '5', "666"};
        for(Object o: objects) {
            System.out.println(o);
        }
	}
}
