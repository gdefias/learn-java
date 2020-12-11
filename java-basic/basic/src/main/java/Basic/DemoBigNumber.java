package Basic;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 BigDecimal 主要用来操作（大）浮点数/实数，BigInteger 主要用来操作大整数（超过 long 类型）
 BigDecimal 的实现利用到了 BigInteger, 所不同的是 BigDecimal 加入了小数位的概念
 * */

public class DemoBigNumber {
    public static void main(String[] args) {

        //精度丢失
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999964
        System.out.println(a == b);// false


        BigDecimal a1 = new BigDecimal("1.0");
        BigDecimal b1 = new BigDecimal("0.9");
        BigDecimal c1 = new BigDecimal("0.8");

        BigDecimal x1 = a1.subtract(b1);    //减法
        BigDecimal y1 = b1.subtract(c1);    //减法

        System.out.println(x1); /* 0.1 */
        System.out.println(y1); /* 0.1 */
        System.out.println(Objects.equals(x1, y1)); /* true */

        System.out.println(a1.add(b1));  //和
        System.out.println(a1.multiply(b1));     //积
        System.out.println(a1.divide(new BigDecimal(10.0)));  //商
        System.out.println(a1.divide((new BigDecimal(0.01)), RoundingMode.HALF_DOWN));    //四舍五入


        //BigDecimal(Double)存在精度损失的风险
        BigDecimal g = new BigDecimal(0.1f);
        System.out.println("g: " + g);

        //BigDecimal.valueOf(Double)也存在精度损失的风险
        BigDecimal gg = BigDecimal.valueOf(0.1f);
        System.out.println("gg: " + gg);

        //推荐使用BigDecimal(String)
        BigDecimal sg = new BigDecimal("0.1");
        System.out.println("sg: " + sg);


        //大小比较 返回-1表示a小于b，0表示a等于b ， 1表示a大于b
        System.out.println(a1.compareTo(b1));// 1

        //setScale方法设置保留几位小数以及保留规则
        BigDecimal m = new BigDecimal("1.255433");
        BigDecimal n = m.setScale(3, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(n); // 1.255

        //valueOf返回x或x/10^scale的大实数
        long xx = 523502321;
        System.out.println(BigDecimal.valueOf(xx));
        System.out.println(BigDecimal.valueOf(xx, 3));

        System.out.println("--------------");
        //BigInteger
        long x2 = 523502321;
        System.out.println(BigInteger.valueOf(x2));

        BigInteger a2 = new BigInteger("2");
        BigInteger b2 = new BigInteger("14");
        BigInteger c2 = new BigInteger("88");

        System.out.println(a2.add(b2));  //和
        System.out.println(a2.subtract(b2));  //差
        System.out.println(a2.multiply(b2));     //积
        System.out.println(a2.divide(new BigInteger("10")));  //商
        System.out.println(a2.mod(new BigInteger("10")));  //余数
    }
}
