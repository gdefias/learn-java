package FileIO;

import java.util.prefs.Preferences;

/**
 * Created by Defias on 2020/07.
 * Description: java首选项 -- Preferences -- 持久化偏好设置

 只能用于小的，受限的数据集合 --只能存储基本类型和字符串，并且每个字符串的长度不能超过8K

 Preferences提供一个存储配置信息的中心知识库，与平台无关。在Windows系统中，它存储在注册表中，在Linux中存储在本地文件系统中。它的实现
 是透明的，程序员无需深究它的底层是如何实现的
 Preferences的中心知识库是树状结构，因此可以避免文件名冲突。每个用户都有一棵树，存放与本用户有关的配置；还有一个系统树，存放全体用户
 的公共信息。内部的配置信息仍然以key-value的结构进行存储

 Preferences类允许应用程序存储和获取用户和系统首选项以及配置数据。此数据持久存储在依赖于实现的内部存储中。典型实现包括纯文本文件、
 特定于操作系统的注册表、目录服务器和 SQL 数据库。此类的用户无需关注内部存储的细节


 Preferences的中文意思即偏好或喜好的意思，也就是说同一个程序在每次运行完后，可以通过Preferences来记录用户的偏好，下次启动时，
 程序会利用这些信息来了解用户的喜好


 Preferences
 是一个可以为任意名字的键/值对. 值可以为布尔型,字符型,其他简单的数据类型，如int等

 API：
 static Preferences	userNodeForPackage(Class<?> c)
 从调用用户首选项树（根据约定，它与指定类的包相关联）返回首选项节点

 static Preferences	systemNodeForPackage(Class<?> c)
 从系统首选项树（根据约定，它与指定类的包相关联）返回首选项节点

 abstract  String	get(String key, String def)
 返回与此首选项节点中指定键相关联的值

 abstract  int	getInt(String key, int def)
 返回与此首选项节点中与指定键相关联的、由字符串表示的int值

 abstract  void	clear()
 移除此首选项节点中的所有首选项（键-值关联）


 */
public class TestIOPreferences {

    public static void main(String args[]) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(TestIOProperties.class);
//        prefs.put("Location", "Oz");
//        prefs.put("Footwear", "Ruby Slippers");
//        prefs.putInt("Companions", 4);
//        prefs.putBoolean("Are there witches?", true);
//
//
//        int usageCount = prefs.getInt("UsageCount", 0);
//        System.out.println("Frist usageCount: " + usageCount);
//        usageCount++;
//        prefs.putInt("UsageCount", usageCount);

        //prefs.clear();

        for(String key : prefs.keys()) {
            System.out.println(key + ": " + prefs.get(key, null));
        }
        // You must always provide a default value:
        System.out.println("How many companions does Dorothy have? " + prefs.getInt("Companions", 0));
    }
}
