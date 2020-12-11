package I18n;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * DateFormat
 * 日期、时间格式化类的抽象类。它支持基于语言习惯的日期、时间格式
 *
 * 常用风格：
 * SHORT 完全为数字，如 12.13.52 或 3:30pm
 * MEDIUM 较长，如 Jan 12, 1952
 * LONG 更长，如 January 12, 1952 或 3:30:32pm
 * FULL 是完全指定，如 Tuesday、April 12、1952 AD 或 3:30:42pm PST
 *
 */

public class TestDateFormat {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);

        //getDateInstance获取日期格式器，该格式器具有给定语言环境的给定格式化风格
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("zh", "CN"));
        DateFormat df2 = DateFormat.getDateInstance(DateFormat.FULL, new Locale("en", "US"));

        //格式化
        System.out.println(df.format(date));
        System.out.println(df2.format(date));

        //反格式化
        try {
            df2.setLenient(false);  //指定日期/时间解析是否不严格
            Date resultd = df2.parse("Sunday, May 27, 2018");
            System.out.println(resultd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
