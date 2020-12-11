/**
 * Created by Defias on 2017/2/23.
 *
 * 随机数    大数
 *
 */
package Basic;
import java.util.Random;
import java.math.*;

public class DemoRandom {
    public static void main(String[] args) {
        System.out.println(Math.random());  //0~1之间的随机数(不含1)

        System.out.println(Math.random()*100);  //0~100之间的随机数

        System.out.println(10+Math.random()*(20-10));  //10~20之间的随机数

        Random random1 = new Random();  //默认以当前时间作为种子
        System.out.println(random1.nextInt());  //返回一个随机的int值
        System.out.println(random1.nextInt(50));  //返回一个0~50之间的随机的int值
        System.out.println(random1.nextBoolean());
        System.out.println(random1.nextDouble());  //返回一个0.0~1.0之间的随机的Double类型的值

        System.out.println("-----------------------------------");
        //使用相同的种子产生相同的随机数列
        Random random2 = new Random(20);
        for(int i=0; i<10; i++) {
            System.out.print(random2.nextInt(1000) + " ");
        }
        System.out.println("");
        Random random3 = new Random(20);
        for(int i=0; i<10; i++) {
            System.out.print(random3.nextInt(1000) + " ");
        }
        System.out.println("");
        System.out.println("-----------------------------------");

        //阶乘
        int n = 50;
        System.out.println(facto(n));
    }

    public static BigInteger facto(int n) {
        BigInteger result = BigInteger.ONE;
        for(int i=1; i<=n; i++) {
            result = result.multiply(new BigInteger(i+""));  //new BigInteger(String)创建BigInteger实例
        }

        return result;
    }


}
