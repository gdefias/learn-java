package I18n;

/**
 * Created by Defias on 2017/3/16.
 *
 * 加载资源文件
 *
 * java.util.ResourceBundle
 * 读取资源属性文件（properties），然后根据.properties文件的名称信息（本地化信息），匹配当前系统的国别语言信息（也可以程序指定），
 * 然后获取相应的properties文件的内容
 *
 * ResourceBundle类：
 * getBundle方法第一个参数baseName需要输入的是资源文件的package路径 + 文件前缀
 *
 * 在加载资源时按下面的顺序加载：
 * baseName + “_”+  当前locale的language + “_” + 当前locale的country + “_” + 当前locale的variant
 * baseName + “_” + 当前locale的language + “_” + 当前locale的country
 * baseName + “_” + 当前locale的language
 * baseName + “_” + 默认locale的language + “_” + 默认locale的country + “_” + 默认locale的variant
 * baseName + “_” + 默认locale的language + “_” + 默认locale的country
 * baseName + “_” + 默认locale的language
 * baseName
 *
 */

import java.util.Locale;
import java.util.ResourceBundle;

public class TestBaseResourceBundle {
    public static void main(String[] args) {
        //新建Locale对象
        Locale localeZH = new Locale("zh", "CN");
        Locale localeEN = new Locale("en", "US");
        Locale localeDefault = Locale.getDefault();

        ResourceBundle resb1 = ResourceBundle.getBundle("myres", localeZH);
        System.out.println("zh-CN:" + resb1.getString("aaa"));

        ResourceBundle resb2 = ResourceBundle.getBundle("myres", localeDefault);
        System.out.println(resb1.getString("aaa"));


        ResourceBundle resb3 = ResourceBundle.getBundle("myres", localeEN);
        System.out.println("en-US:" + resb3.getString("aaa"));
    }
}
