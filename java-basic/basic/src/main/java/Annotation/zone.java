package Annotation;
public @interface zone {}
/**
 * Created by Defias on 2020/07.
 * Description: Annotation - 注解

 注解在一定程度上是把元数据和源代码文件结合在一起的趋势所激发的，而不是保存在外部文档
 与其他任何java接口一样，注解也会被编译成class文件

 注解可被用于packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和
 本地变量（如循环变量、catch参数）

 元注解
 负责注解其他注解。Java5.0定义了4个标准的meta-annotation类型，它们被用来提供对其它annotation类型作说明

 @Target 表示该注解可以用于修饰哪些程序元素，用于描述注解的使用范围
 （即：被描述的注解可以用在什么地方）
 取值(ElementType)有：
 1）CONSTRUCTOR: 用于描述构造器  @Target(ElementType.CONSTRUCTOR)  //构造函数
 2）FIELD: 用于描述域  @Target(ElementType.FIELD) //字段、枚举的常量
 3）LOCAL_VARIABLE: 用于描述局部变量  @Target(ElementType.LOCAL_VARIABLE) //局部变量
 4）METHOD: 用于描述方法  @Target(ElementType.METHOD) //方法
 5）PACKAGE: 用于描述包  @Target(ElementType.PACKAGE) ///包
 6）PARAMETER: 用于描述参数  @Target(ElementType.PARAMETER) //方法参数
 7）TYPE: 用于描述类、接口(包括注解类型) 或enum声明  @Target(ElementType.TYPE)   //接口、类、枚举、注解
 8）ANNOTATION_TYPE：用于描述注解    @Target(ElementType.ANNOTATION_TYPE) //注解

 @Retention 表示该注解可以保留多长时间，表示需要在什么级别保存该注解信息，用于描述注解的生命周期
 （即：被描述的注解在什么范围内有效）
 取值（RetentionPoicy）有：
 1）SOURCE: 在源文件中有效（注解仅存在于源码中，在class字节码文件中不包含）
 2）CLASS: 在class文件中有效（默认的保留策略，注解会在class字节码文件中存在，但不会将注解加载到JVM中，即运行时无法获得）
 3）RUNTIME: 在运行时有效（注解信息在目标类加载到JVM后依然保留，在运行期可以通过反射机制读取类找那个的注解信息）

 @Documented 说明该注解将被包含在javadoc中，该注解将被javadoc工具提取成文档
 如果定义注解类时使用了@Documented修饰，则所有使用该注解修饰的程序元素API文档中将会包含该Annotation说明

 @Inherited 允许子类继承父类的注解。如果某个注解类使用了该注解，那么其子类将自动具有该注解类

 @Repeatable  是可重复的意思。是Java 1.8才加进来的


 Java中常见的注解：
 @Override
 表示当前的方法定义将覆盖超类中的方法。如果你不小心拼写错误，或者方法签名对不上被覆盖的方法，编译器就会发出错误
 @Override注解只能用于作用于方法，不能用于作用于其它程序元素

 @Deprecated
 用于表示某个程序元素（类，方法等）已经过时，编译器将不鼓励使用这个被标注的程序元素
 当其他程序使用过时的是程序元素时，编译器会发出警告信息

 @SuppressWarnings
 关闭编译器的警告信息

 @SafeVarargs
 在Java 7中加入用于禁止对具有泛型varargs参数的方法或构造函数的调用方发出警告

 @FunctionalInterface
 Java 8 中加入用于表示类型声明为函数式接口


 */

