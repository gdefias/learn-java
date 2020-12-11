/**
 * Created by Defias on 2017/3/1.
 *
 * 数据类型转换
 *
 */
package Basic;

public class DemoTypeToType {
    public static void main(String[] args) {

        test1();
    }

    public static void test1() {
        //字符串转换为字符数组
        String str1 = "sdfdsg";
        char[] strr = str1.toCharArray();
        System.out.println(strr);
        System.out.println();

        //字符型数组转换到字符串
        //方法1：直接在构造String时转换
        char[] data = {'a', 'b', 'c'};
        String str2 = new String(data);
        System.out.println(str2);

        //方法2
        String str3 = String.valueOf(data);
        System.out.println(str3);
        System.out.println();


        //将字串String转换成整数int
        //方法1
        String str4 = "123";
        int i = Integer.parseInt(str4);
        System.out.println(i);

        int ii = Integer.parseInt(str4,8);  //第二个参数表示几进制数
        System.out.println(ii);

        //方法2
        int j = Integer.valueOf(str4).intValue();
        System.out.println(j);
        System.out.println();

        //将整数int转换成字符串String
        //方法1
        String s = String.valueOf(i);
        System.out.println(s);

        //方法2
        s = Integer.toString(ii);
        System.out.println(s);

        //方法3
        String str5 = "" + i + ii;
        System.out.println(str5);
        System.out.println();
    }
}
