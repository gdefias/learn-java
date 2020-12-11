package Basic.DateAndTime;
import java.util.Date;
/**
 * Created by Defias on 2016/2/29.
 *
 * 日期时间Date类
 *
 * 常用的Date类方法：
 * 方法	             功能
 * boolean after(Date date)	若调用Date对象所包含的日期比date指定的对象所包含的日期晚，返回true，否则返回false
 * boolean before(Date date)	若调用Date对象所包含的日期比date指定的对象所包含的日期早，返回true，否则返回false
 * Object clone()	复制调用Date对象
 * int compareTo(Date date)	比较调用对象所包含的日期和指定的对象包含的日期，若相等返回0；若前者比后者早，返回负值；否则返回正值
 * long getTime()	以毫秒数返回从1970年01月01日00时到目前的时间
 * int hashCode()	返回调用对象的散列值
 * void setTime(long time)	根据time的值，设置时间和日期。time值从1970年01月01日00时开始计算
 * String toString()	把调用的Date对象转换成字符串并返回结果
 * public Static String valueOf(type variable)	把variable转换为字符串
 */
public class TestDate {
    public static void main(String args[]) {
        int curtime = (int)(System.currentTimeMillis() / 1000);
        System.out.println(curtime);

        Date da = new Date();  //使用当前的日期和时间初始化时间对象
        System.out.println(da); //显示时间和日期
        long msec = da.getTime();  //当前时间戳
        System.out.println("从1970年1月1日0时到现在共有：" + msec + "毫秒");

    }
}