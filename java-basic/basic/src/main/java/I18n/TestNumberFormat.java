package I18n;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * NumberFormat
 * 所有数字格式类的基类。它提供格式化和解析数字的接口。它也提供了决定数字所属语言类型的方法
 *
 */

public class TestNumberFormat {
    public static void main(String[] args) {
        Locale locale = new Locale("en", "US");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale); //返回指定语言环境的货币格式

        //格式化
        double num = 123456.78;
        System.out.println(String.format("%s : %s", locale.toString(), format.format(num)));

        //反格式化
        String s = "$123,456.78";
        double n;
        try {
            n = (double)format.parse(s);
            System.out.println(n);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Locale[] locales;
        //getAvailableLocales返回一个数组，它包含所有此类(NumberFormat)的get*Instance方法可以为其返回本地化实例的语言环境
        locales = (Locale[]) NumberFormat.getAvailableLocales().clone();

        //排序
        Arrays.sort(locales, new Comparator<Locale>() {
            public int compare(Locale l1, Locale l2) {
                return l1.getDisplayName().compareTo(l2.getDisplayName());
            }
        });

        //迭代输出
        for (Locale loc : locales)
            System.out.println(loc.getDisplayName());

    }
}
