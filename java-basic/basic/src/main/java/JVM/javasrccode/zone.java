package JVM.javasrccode;
public @interface zone {}
/**
 * Created by Defias on 2020/08.
 * Description: java源码生成

 javapoet
 一个用来生成 .java源文件的Java API
 poet指诗人，java poet指的是能够自动写java源代码的人（java诗人）

 javapoet里面常用的几个类：
 MethodSpec 代表一个构造函数或方法声明
 TypeSpec 代表一个类，接口，或者枚举声明
 FieldSpec 代表一个成员变量，一个字段声明
 JavaFile 包含一个顶级类的Java文件

 javapoet还提供了占位符，比如：
 $L for int
 $S for Strings
 $T for Types
 $N for Names(我们自己生成的方法名或者变量名等等)
 */

