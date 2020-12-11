package I18n;
public @interface zone {}
/**
 * Created by Defias on 2020/07.
 * Description:  国际化(i18n)

 【背景】
 现代软件开发，往往做出的应用程序不止给一个国家的人去使用。不同国家的人往往存在语言文字不通的问题。由此产生了国际化(internationalization)、多语言
 (multi-language)、本地化(locale)这些词，它们其实都是一个意思，支持多种语言，提供给不同国家的用户使用

 【标准】
 ISO-639标准使用编码定义了国际上常见的语言，每一种语言由两个小写字母表示
 ISO-3166标准使用编码定义了国家/地区，每个国家/地区由两个大写字母表示

 为什么要用语言+国家/地区来标示语言呢？
 道理很简单：拿咱们中国来说，同样是中文，全国各地的方言就多如牛毛，更不要说世界上有那么多种语言，得有多少方言。

 国家、地区的语言编码如：
 ----------------------------------------------------
 国家/地区                        语言编码
 简体中文(中国)                      zh-cn
 繁体中文(台湾地区)                  zh-tw
 繁体中文(香港)                      zh-hk
 英语(香港)                          en-hk
 英语(美国)                          en-us
 英语(英国)                          en-gb
 英语(全球)                          en-ww
 韩文(韩国)                          ko-kr
 日语(日本)                          ja-jp


 【Java中实现国际化的方法】
 实现国际化，归根结底就是根据语言类型去定义好字符串模板而已，Java中的多语言字符串模板一般保存在properties资源文件中

 它必须遵照以下的命名规范：
 <资源名>_<语言代码>_<国家/地区代码>.properties   其中语言代码和国家/地区代码都是可选的
 <资源名>.properties   默认的资源文件，即某个本地化类型在系统中找不到对应的资源文件，就采用这个默认的资源文件

 例：
 定义中英文两种多语言资源文件，将其置于com.notes.locale.resources路径下：
 content_en_US.properties：
 helloWorld = HelloWorld!
 time = Thecurrenttimeis%s.

 content_zh_CN.properties：
 helloWorld = \u4e16\u754c\uff0c\u4f60\u597d\uff01
 time = \u5f53\u524d\u65f6\u95f4\u662f\u0025\u0073\u3002

 两种语言的Key完全一致，只是Value是对应语言的字符串
 本地化不同的同一资源文件，虽然属性值各不相同，但属性名却是相同的，这样应用程序就可以通过Locale对象和属性名精确调用到某个具体的属性值了
 为了达到跨编码也正常显示的目的，有必要将非ASCII字符转为Unicode编码。上面的中文资源文件就是中文转为Unicode的结果


 【Unicode转换工具】
 JDK在bin目录下为我们提供了一个Unicode转换工具：native2ascii
 它可以将中文字符的资源文件转换为Unicode代码格式的文件，命令格式如下：
 native2ascii [-reverse] [-encoding 编码] [输入文件 [输出文件]]

 例：
 假设content_zh_CN.properties 在d:\ 目录。执行以下命令可以新建content_zh_CN_new.properties，其中的内容就所有中文字符转为UTF-8编码格式的结果
 native2ascii -encoding utf-8 d:\content_zh_CN.properties d:\content_zh_CN_new.properties


 【加载资源文件】
 定义了多语言资源文件，下一步就是加载资源文件了，Java为我们提供了用于加载本地化资源文件的工具类：java.util.ResourceBoundle


 【支持国际化的国际化工具类】
 Java中也提供了几个支持国际化的格式化工具类。例如：NumberFormat、DateFormat、MessageFormat
 */

