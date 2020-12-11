/**
 * Created by Defias on 2016/2/27.
 *
 * switch
 */
package Basic;
import java.util.*;

public class TestSwitch {
    public static void main(String[] args) {

        //求某一年的某一月有多少天
        int days = 0;

        //获取用户数输入
        Scanner sc = new Scanner(System.in);
        System.out.println("输入年份：");
        int year = sc.nextInt();
        System.out.println("输入月份：");
        int month = sc.nextInt();

        switch (month) {  //选择因子必须是int或char或枚举
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                //判断润年
                if(year%4==0 && year%100!=0 || year%400==0)
                    days = 29;
                else
                    days = 28;
                break;
            default:
                System.out.println("月份输入错误！");
                System.exit(-1);  //强制结束程序
        }
        System.out.printf("天数：%d\n", days);
    }

}
