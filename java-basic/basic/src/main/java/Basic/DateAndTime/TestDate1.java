package Basic.DateAndTime;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Locale;

/**
 * Created by Defias on 2017/3/16.
 *
 * DateFormat
 * SimpleDateFormat

 DateFormat是一个日期的格式化类，用来格式化日期； 是一个抽象类，DateFormat类本身的内部提供了可以直接为其实例化的操作
 SimpleDateFormat是一个以国别敏感的方式格式化和分析数据的具体类， 它允许格式化 (date -> text)、语法分析 (text -> date)和标准化

 DateFormat和SimpleDateFormat的区别
 1.DateFormat可以直接使用，但其本身是一个抽象类，可以根据Locate指定的区域得到对应的日期时间格式
 2.SimpleDateFormat类是DateFormat类的子类，一般情况下来讲DateFormat类很少会直接使用。而都使用SimpleDateFormat类完成

 注意：SimpleDateFormat和DateFormat类都不是线程安全的，在多线程环境下调用format()和parse()方法应该使用同步代码来避免问题
 http://www.cnblogs.com/peida/archive/2013/05/31/3070790.html

 继承关系：
 Java.lang.Object
 |
 +----java.text.Format
 |
 +----java.text.DateFormat
 |
 +----java.text.SimpleDateFormat
 * SimpleDateFormat语法：
 G 年代标志符
 y 年
 M 月
 d 日
 h 时 在上午或下午 (1~12)
 H 时 在一天中 (0~23)
 m 分
 s 秒
 S 毫秒
 E 星期
 D 一年中的第几天
 F 一月中第几个星期几
 w 一年中第几个星期
 W 一月中第几个星期
 a 上午 / 下午 标记符
 k 时 在一天中 (1~24)
 K 时 在上午或下午 (0~11)
 z 时区
 */

public class TestDate1 {


    public static void main(String[] args) {

        testDateFormat();
        System.out.println("================");

        testSimpleDateFormat();
        System.out.println("================");

        Date now = new Date();
        System.out.println(TestDate1.toLongDateString(now));
        System.out.println(TestDate1.toShortDateString(now));
        System.out.println(TestDate1.toLongTimeString(now));
        System.out.println(TestDate1.toShortTimeString(now));
    }

    public static void testDateFormat() {

        //美式日期格式
        DateFormat df1 = null ;     // 声明一个DateFormat
        DateFormat df2 = null ;     // 声明一个DateFormat
        df1 = DateFormat.getDateInstance() ;    // 得到日期的DateFormat对象
        df2 = DateFormat.getDateTimeInstance() ;    // 得到日期时间的DateFormat对象
        System.out.println("DATE：" + df1.format(new Date())) ; // 按照日期格式化
        System.out.println("DATETIME：" + df2.format(new Date())) ;   // 按照日期时间格式化

        //中式日期格式（通过Locale对象指定要显示的区域，指定的区域是中国）
        df1 = DateFormat.getDateInstance(DateFormat.YEAR_FIELD,new Locale("zh","CN")) ; // 得到日期的DateFormat对象
        df2 = DateFormat.getDateTimeInstance(DateFormat.YEAR_FIELD,DateFormat.ERA_FIELD,new Locale("zh","CN")) ;    // 得到日期时间的DateFormat对象
        System.out.println("DATE：" + df1.format(new Date())) ; // 按照日期格式化
        System.out.println("DATETIME：" + df2.format(new Date())) ;   // 按照日期时间格式化

    }

    public static void testSimpleDateFormat() {

        //把Date转化成指定的日期格式
        Date da = new Date();
        SimpleDateFormat ma1 = new SimpleDateFormat("yyyy 年 MM 月 dd 日 E 北京时间");
        System.out.println(ma1.format(da));
        SimpleDateFormat ma2 = new SimpleDateFormat("北京时间：yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒");
        System.out.println(ma2.format(-1000));
        System.out.println("================");

        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yy/MM/dd HH:mm");
        SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//等价于now.toLocaleString()
        SimpleDateFormat myFmt3 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        SimpleDateFormat myFmt4 = new SimpleDateFormat("一年中的第 D 天 一年中第w个星期 一月中第W个星期 在一天中k时 z时区");
        Date now = new Date();
        System.out.println(myFmt.format(now));
        System.out.println(myFmt1.format(now));
        System.out.println(myFmt2.format(now));
        System.out.println(myFmt3.format(now));
        System.out.println(myFmt4.format(now));
        System.out.println(now.toGMTString());
        System.out.println(now.toLocaleString());
        System.out.println(now.toString());
        System.out.println("================");

        //把给定的字符串中的日期提取为Date
        String strDate = "2008-10-19 10:11:30.345";
        //准备第一个模板，从字符串中提取出日期数字
        String pat1 = "yyyy-MM-dd HH:mm:ss.SSS";
        //准备第二个模板，将提取后的日期数字变为指定的格式
        String pat2 = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";

        SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);   //实例化模板对象
        SimpleDateFormat sdf2 = new SimpleDateFormat(pat2);
        Date d = null ;
        try {
            d = sdf1.parse(strDate) ;   // 将给定的字符串中的日期提取出来
        } catch(Exception e) {            // 如果提供的字符串格式有错误，则进行异常处理
            e.printStackTrace() ;       // 打印异常信息
        }
        System.out.println(sdf2.format(d)) ;    // 将日期变为新的格式
    }



    public static String toLongDateString(Date dt){
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        return myFmt.format(dt);
    }

    public static String toShortDateString(Date dt){
        SimpleDateFormat myFmt=new SimpleDateFormat("yy年MM月dd日 HH时mm分");
        return myFmt.format(dt);
    }

    public static String toLongTimeString(Date dt){
        SimpleDateFormat myFmt=new SimpleDateFormat("HH mm ss SSSS");
        return myFmt.format(dt);
    }

    public static String toShortTimeString(Date dt){
        SimpleDateFormat myFmt=new SimpleDateFormat("yy/MM/dd HH:mm");
        return myFmt.format(dt);
    }


}
