package Basic;

/**
 * Created by Defias on 2017/8/29.
 */
public class DemoInteger {

    public static void main(String[] args) {
        int a = 65535;

        //十进制的整数转换成二、八、十六进制数的字符串
        System.out.println(Integer.toBinaryString(a));

        System.out.println(Integer.toOctalString(a));

        System.out.println(Integer.toHexString(a));

        //二、八、十六进制数的字符串转十进制字符串
        System.out.println(Integer.valueOf("fffff",16).toString());

        System.out.println(Integer.valueOf("76",8).toString());

        System.out.println(Integer.valueOf("0101",2).toString());

        //byteValue： 转为字节 只取低八位的值，高位不要
        Integer obj = new Integer(129);
        // returns the value of Integer as a byte
        byte b = obj.byteValue();
        System.out.println("Value of b = " + b);

    }
}
