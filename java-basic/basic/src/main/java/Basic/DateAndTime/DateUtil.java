package Basic.DateAndTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Defias on 2020/08.
 * Description: SimpleDateFormat的线程安全问题
 */

public class DateUtil {


    //线程不安全
    public static class DateUnTrueUtil {
        private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public static  String formatDate(Date date) throws ParseException {
            return sdf.format(date);
        }

        public static Date parse(String strDate) throws ParseException {
            return sdf.parse(strDate);
        }
    }

    //解决办法1：需要的时候创建新实例
    public static class DateUtil1 {
        public static  String formatDate(Date date)throws ParseException{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }

        public static Date parse(String strDate) throws ParseException{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(strDate);
        }
    }

    //解决办法2： 使用同步 同步SimpleDateFormat对象
    public static class DateSyncUtil {
        private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public static String formatDate(Date date)throws ParseException{
            synchronized(sdf){
                return sdf.format(date);
            }
        }

        public static Date parse(String strDate) throws ParseException{
            synchronized(sdf){
                return sdf.parse(strDate);
            }
        }
    }

    //解决办法3：使用ThreadLocal
    public static class ConcurrentDateUtil {

        private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        };

        public static Date parse(String dateStr) throws ParseException {
            return threadLocal.get().parse(dateStr);
        }

        public static String format(Date date) {
            return threadLocal.get().format(date);
        }
    }

    //解决办法4: 抛弃JDK，使用其他类库中的时间格式化类：
    //1.Apache commons里的FastDateFormat，宣称是既快又线程安全的SimpleDateFormat, 但它只能对日期进行format, 不能对日期串进行解析
    //2.使用Joda-Time类库来处理时间相关问题

}
