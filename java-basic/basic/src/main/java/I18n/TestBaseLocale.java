package I18n;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * 本地化类
 *
 * java.util.Locale
 * 表示语言和国家/地区信息的本地化类，它是创建国际化应用的基础
 *
 * 构造方法：
 * Locale(String language)
 * Locale(String language, String country)
 * Locale(String language, String country, String variant)
 *
 */
public class TestBaseLocale {
    public static void main(String[] args) {
        Locale localeZH = new Locale("zh", "CN");
        System.out.println(localeZH.getCountry());  //获取ISO国家代码
        System.out.println(localeZH.getLanguage());  //获取ISO语言代码
        System.out.println(localeZH.getDisplayCountry());  //获取适合向用户显示的国家名
        System.out.println(localeZH.getDisplayLanguage());  //获取适合向用户显示的语言名
        System.out.println(localeZH.getDisplayName());
        System.out.println();

        Locale localeEN = new Locale("en", "US");
        System.out.println(localeEN.getCountry());
        System.out.println(localeEN.getLanguage());
        System.out.println(localeEN.getDisplayCountry());
        System.out.println(localeEN.getDisplayLanguage());
        System.out.println(localeEN.getDisplayName());
        System.out.println();

        Locale localeDefault = Locale.getDefault();
        System.out.println(localeDefault.getCountry());
        System.out.println(localeDefault.getLanguage());
        System.out.println(localeDefault.getDisplayCountry());
        System.out.println(localeDefault.getDisplayLanguage());
        System.out.println(localeDefault.getDisplayName());

    }
}
