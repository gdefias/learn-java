package FileIO;

/**
 * Created by Defias on 2017/3/16.
 *
 * properties类

 关于properties文件:
 1、properties文件是一个文本文件
 2、properties文件的语法有两种，一种是注释，一种属性配置
     注    释：前面加上#号
     属性配置：以"键=值"的方式书写一个属性的配置信息
 3、properties文件的一个属性配置信息值可以换行，但键不可以换行。值换行用“\”表示
 4、properties的属性配置键值前后的空格在解析时候会被忽略
 5、properties文件可以只有键而没有值。也可以仅有键和等号而没有值，但无论如何一个属性配置不能没有键

 properties
 public class Properties extends Hashtable<Object,Object>

 构造方法：
 load(Reader reader)
 load(InputStream inStream)
 loadFromXML(InputStream in)

 方法:
 prop.clear(); // 清空
 prop.containsKey("key"); // 是否包含key
 prop.containsValue("value"); // 是否包含value
 prop.entrySet(); // prop的Map.Entry集合
 prop.getProperty("key"); // 通过key获取value
 prop.put("key", "value"); // 添加属性
 prop.list(new PrintStream(new File(""))); // 将prop保存到文件
 prop.store(new FileOutputStream(new File("")), "注释"); // 和上面类似

 */

import java.io.*;
import java.util.Properties;
import java.util.prefs.Preferences;

public class TestIOProperties {
    public static void main(String args[]) throws IOException {
        test1();
    }


    public static void test1() throws IOException {
        //读properties文件
        InputStream is = new FileInputStream("base/src/test1.properties");
        Properties prop = new Properties();
        prop.load(is);
        is.close();

        for (Object key : prop.keySet()) {
            System.out.println(key + "=" + prop.get(key));
        }
        System.out.println("---------------------------");

        //将Properties写入其他文件
        OutputStream os1 = new FileOutputStream("base/src/test1x.xml");
        OutputStream os2 = new FileOutputStream("base/src/test1p.properties");

        prop.storeToXML(os1, "我从properties导出的XML配置文件");
        prop.store(os2, "我从properties导出的properties配置文件");
        os1.close();
        os2.close();

        //通过Properties读取XML文件
        prop.loadFromXML(new FileInputStream("base/src/test1x.xml"));
        System.out.println("我从导出的xml加载配置文件信息！");
        for (Object key : prop.keySet()) {
            System.out.println(key + "=" + prop.get(key));
        }

        //向Properties对象中添加信息，并持久化到另一个XML文件中
        prop.put("呵呵呵", "嘎嘎嘎");
        OutputStream os3 = new FileOutputStream("base/src/test1xx.xml");
        prop.storeToXML(os3, "我从properties导出的XML配置文件");
        os3.close();
    }
}
