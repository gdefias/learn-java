package Basic;

/**
 * Created by Defias on 2017/9/1.

 floatToIntBits: 根据IEEE 754浮点“单一格式”位布局，返回指定浮点值的表示
    If the argument is positive infinity, the result is 0x7f800000.    正无穷大 eg：正整数除以0
    If the argument is negative infinity, the result is 0xff800000.    负无穷大  eg: 0/0、负数的平方根
    If the argument is NaN, the result is 0x7fc00000.

 */
public class DemoFloat {
    public static void main(String[] args) {
        Float f = new Float("10.07");
        System.out.println(f.doubleValue());

        System.out.println("Value = " + Float.floatToIntBits(5.5f));

        int intBits = Float.floatToIntBits(5.5f);
        System.out.println("Value = " + intBits);
        String hexStr = Long.toHexString(intBits);
        System.out.println("hex str : " + hexStr);   //十六进制字符串

        System.out.println(Long.toHexString(Float.floatToIntBits(Float.NaN)));
        System.out.println(Long.toHexString(Float.floatToIntBits(Float.POSITIVE_INFINITY)));
        System.out.println(Long.toHexString(Float.floatToIntBits(Float.NEGATIVE_INFINITY)));

        System.out.println(Double.toHexString(Double.doubleToLongBits(Double.NaN)));
        System.out.println(Double.toHexString(Double.doubleToLongBits(Double.POSITIVE_INFINITY)));
        System.out.println(Double.toHexString(Double.doubleToLongBits(Double.NEGATIVE_INFINITY)));

    }
}
