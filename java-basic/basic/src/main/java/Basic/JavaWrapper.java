/**
 * Created by Defias on 2016/2/27.
 *
 *
 * 包装类  装箱  拆箱
 *
 * Java为每种基本数据类型分别设计了对应的类，称之为包装类(Wrapper Classes)，也有称为外覆类或数据类型类
 * 基本数据类型 对应的包装类
 * ———————————-
 * byte Byte
 * short Short
 * int Integer
 * long Long
 * char Character
 * float Float
 * double Double
 * boolean Boolean
 * 1. 每个包装类的对象可以封装一个相应的基本类型的数据，并提供了其它一些有用的方法
 * 2. 包装类对象一经创建，其内容（所封装的基本类型数据值）不可改变
 * 3. 装箱：由基本类型向对应的包装类转换称为装箱，例如把int包装成Integer类的对象
 * 4. 拆箱：包装类向对应的基本类型转换称为拆箱，例如把Integer类的对象重新简化为int
 * 5. 所有包装类都是final类型，不能创建它们的子类
 */
package Basic;


public class JavaWrapper {
    public static void main(String[] args) {
        int m = 500;
        Integer obj = new Integer(m);  // 手动装箱
        Integer auto_obj = m;  // 自动装箱

        int n = obj.intValue();   // 手动拆箱
        int nn = auto_obj;  // 自动拆箱
        System.out.println("n = " + n);
        System.out.println("nn = " + nn);

        Integer obj1 = new Integer(500);
        System.out.println("obj 等价于 obj1？ " + obj.equals(obj1));

        Integer obj11 = 500;
        System.out.println("auto_obj 等价于 obj11？ " + auto_obj.equals(obj11));


        String[] str = {"123", "123abc", "abc123", "abcxyz", "00123"};
        for(String item: str) {
            try {
                int k = Integer.parseInt(item);  //Integer类有一个静态的paseInt()方法，可以将字符串转换为整数
                System.out.println(item + " 可以转换为整数 " + k);
            } catch (Exception e) {
                System.out.println(item + " 无法转换为整数");
            }
        }

        String s = Integer.toString(m);
        System.out.println("s = " + s);
		System.out.println("----------------");


		Integer objNum = 200;
		System.out.println(objNum.byteValue());  //objNum对象所表示的byte类型的值
		System.out.println(objNum.intValue());  //objNum对象所表示的int类型的值
		System.out.println(objNum.longValue());  //objNum对象所表示的long类型的值
		System.out.println(objNum.shortValue());  //objNum对象所表示的short类型的值
		System.out.println(objNum.doubleValue());  //objNum对象所表示的double类型的值
		System.out.println(objNum.floatValue());  //objNum对象所表示的float类型的值
		System.out.println(objNum.toString());  //objNum对象所表示的字符串形式

		Integer objN = Integer.valueOf("432"); //所有包装类（Character和Boolean除外）都有一个静态方法valueOf： 根据参数（字符串）创建包装类对象
		System.out.println(objN);

		int N = Integer.parseInt("234"); //所有包装类（Character和Boolean除外）都有一个静态方法parseXXX： 把字符串解析为相应的基本类型的数据
		System.out.println(N);


    }
}
