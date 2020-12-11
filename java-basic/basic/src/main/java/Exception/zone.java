package Exception;
public @interface zone {}
/**
 * Created by Defias on 2020/07.
 * Description:  异常  -  Java常见内置异常
 *
 * 在标准包java.lang中，Java定义了若干个异常类
 * java.lang中定义的免检异常子类
 * 异常	                                 说明
 * ArithmeticException	            算术错误，如被0除  算术异常类
 * ArrayIndexOutOfBoundsException	数组下标出界  数组下标越界异常
 * ArrayStoreException	            数组元素赋值类型不兼容
 * ClassCastException	            非法强制转换类型  类型强制转换异常
 * IllegalArgumentException	        调用方法的参数非法
 * IllegalMonitorStateException	    非法监控操作，如等待一个未锁定线程
 * IllegalStateException	            环境或应用状态不正确
 * IllegalThreadStateException	    请求操作与当前线程状态不兼容
 * IndexOutOfBoundsException	        某些类型索引越界
 * NullPointerException	            非法使用空引用  空指针异常类
 * NumberFormatException	            字符串到数字格式非法转换
 * SecurityException	                试图违反安全性 违背安全原则异常
 * StringIndexOutOfBounds	        试图在字符串边界之外索引
 * UnsupportedOperationException	    遇到不支持的操作
 *
 *
 *java.lang中定义的必检异常
 * 异常	                         说明
 * ClassNotFoundException	    找不到类
 * CloneNotSupportedException	试图克隆一个不能实现Cloneable接口的对象
 * IllegalAccessException	    对一个类的访问被拒绝
 * InstantiationException	    试图创建一个抽象类或者抽象接口的对象
 * InterruptedException	        一个线程被另一个线程中断
 * NoSuchFieldException	        请求的字段不存在
 * NoSuchMethodException	        请求的方法不存在
 *

 数组负下标异常：NegativeArrayException

 文件已结束异常：EOFException

 文件未找到异常：FileNotFoundException

 字符串转换为数字异常：NumberFormatException

 操作数据库异常：SQLException

 输入输出异常：IOException

 方法未找到异常：NoSuchMethodException


 java.lang.AbstractMethodError

 抽象方法错误。当应用试图调用抽象方法时抛出。

 java.lang.AssertionError

 断言错。用来指示一个断言失败的情况。

 java.lang.ClassCircularityError

 类循环依赖错误。在初始化一个类时，若检测到类之间循环依赖则抛出该异常。

 java.lang.ClassFormatError

 类格式错误。当Java虚拟机试图从一个文件中读取Java类，而检测到该文件的内容不符合类的有效格式时抛出。

 java.lang.Error

 错误。是所有错误的基类，用于标识严重的程序运行问题。这些问题通常描述一些不应被应用程序捕获的反常情况。

 java.lang.ExceptionInInitializerError

 初始化程序错误。当执行一个类的静态初始化程序的过程中，发生了异常时抛出。静态初始化程序是指直接包含于类中的static语句段。

 java.lang.IllegalAccessError

 违法访问错误。当一个应用试图访问、修改某个类的域（Field）或者调用其方法，但是又违反域或方法的可见性声明，则抛出该异常。

 java.lang.IncompatibleClassChangeError

 不兼容的类变化错误。当正在执行的方法所依赖的类定义发生了不兼容的改变时，抛出该异常。一般在修改了应用中的某些类的声明定义而没有对整个应用重新编译而直接运行的情况下，容易引发该错误。

 java.lang.InstantiationError

 实例化错误。当一个应用试图通过Java的new操作符构造一个抽象类或者接口时抛出该异常.

 java.lang.InternalError

 内部错误。用于指示Java虚拟机发生了内部错误。

 java.lang.LinkageError

 链接错误。该错误及其所有子类指示某个类依赖于另外一些类，在该类编译之后，被依赖的类改变了其类定义而没有重新编译所有的类，进而引发错误的情况。

 java.lang.NoClassDefFoundError

 未找到类定义错误。当Java虚拟机或者类装载器试图实例化某个类，而找不到该类的定义时抛出该错误。

 java.lang.NoSuchFieldError

 域不存在错误。当应用试图访问或者修改某类的某个域，而该类的定义中没有该域的定义时抛出该错误。

 java.lang.NoSuchMethodError

 方法不存在错误。当应用试图调用某类的某个方法，而该类的定义中没有该方法的定义时抛出该错误。

 java.lang.OutOfMemoryError

 内存不足错误。当可用内存不足以让Java虚拟机分配给一个对象时抛出该错误。

 java.lang.StackOverflowError

 堆栈溢出错误。当一个应用递归调用的层次太深而导致堆栈溢出时抛出该错误。

 java.lang.ThreadDeath

 线程结束。当调用Thread类的stop方法时抛出该错误，用于指示线程结束。

 java.lang.UnknownError

 未知错误。用于指示Java虚拟机发生了未知严重错误的情况。

 java.lang.UnsatisfiedLinkError

 未满足的链接错误。当Java虚拟机未找到某个类的声明为native方法的本机语言定义时抛出。

 java.lang.UnsupportedClassVersionError

 不支持的类版本错误。当Java虚拟机试图从读取某个类文件，但是发现该文件的主、次版本号不被当前Java虚拟机支持的时候，抛出该错误。

 java.lang.VerifyError

 验证错误。当验证器检测到某个类文件中存在内部不兼容或者安全问题时抛出该错误。

 java.lang.VirtualMachineError

 虚拟机错误。用于指示虚拟机被破坏或者继续执行操作所需的资源不足的情况。

 java.lang.ArithmeticException

 算术条件异常。譬如：整数除零等。

 java.lang.ArrayIndexOutOfBoundsException

 数组索引越界异常。当对数组的索引值为负数或大于等于数组大小时抛出。

 java.lang.ArrayStoreException

 数组存储异常。当向数组中存放非数组声明类型对象时抛出。

 java.lang.ClassCastException

 类造型异常。假设有类A和B（A不是B的父类或子类），O是A的实例，那么当强制将O构造为类B的实例时抛出该异常。该异常经常被称为强制类型转换异常。

 java.lang.ClassNotFoundException

 找不到类异常。当应用试图根据字符串形式的类名构造类，而在遍历CLASSPAH之后找不到对应名称的class文件时，抛出该异常。

 java.lang.CloneNotSupportedException

 不支持克隆异常。当没有实现Cloneable接口或者不支持克隆方法时,调用其clone()方法则抛出该异常。

 java.lang.EnumConstantNotPresentException

 枚举常量不存在异常。当应用试图通过名称和枚举类型访问一个枚举对象，但该枚举对象并不包含常量时，抛出该异常。

 java.lang.Exception

 根异常。用以描述应用程序希望捕获的情况。

 java.lang.IllegalAccessException

 违法的访问异常。当应用试图通过反射方式创建某个类的实例、访问该类属性、调用该类方法，而当时又无法访问类的、属性的、方法的或构造方法的定义时抛出该异常。

 java.lang.IllegalMonitorStateException

 违法的监控状态异常。当某个线程试图等待一个自己并不拥有的对象（O）的监控器或者通知其他线程等待该对象（O）的监控器时，抛出该异常。

 java.lang.IllegalStateException

 违法的状态异常。当在Java环境和应用尚未处于某个方法的合法调用状态，而调用了该方法时，抛出该异常。

 java.lang.IllegalThreadStateException

 违法的线程状态异常。当县城尚未处于某个方法的合法调用状态，而调用了该方法时，抛出异常。

 java.lang.IndexOutOfBoundsException

 索引越界异常。当访问某个序列的索引值小于0或大于等于序列大小时，抛出该异常。

 java.lang.InstantiationException

 实例化异常。当试图通过newInstance()方法创建某个类的实例，而该类是一个抽象类或接口时，抛出该异常。

 java.lang.InterruptedException

 被中止异常。当某个线程处于长时间的等待、休眠或其他暂停状态，而此时其他的线程通过Thread的interrupt方法终止该线程时抛出该异常。

 java.lang.NegativeArraySizeException

 数组大小为负值异常。当使用负数大小值创建数组时抛出该异常。

 java.lang.NoSuchFieldException

 属性不存在异常。当访问某个类的不存在的属性时抛出该异常。

 java.lang.NoSuchMethodException

 方法不存在异常。当访问某个类的不存在的方法时抛出该异常。

 java.lang.NullPointerException

 空指针异常。当应用试图在要求使用对象的地方使用了null时，抛出该异常。譬如：调用null对象的实例方法、访问null对象的属性、计算null对象的长度、使用throw语句抛出null等等。

 java.lang.NumberFormatException

 数字格式异常。当试图将一个String转换为指定的数字类型，而该字符串确不满足数字类型要求的格式时，抛出该异常。

 java.lang.RuntimeException

 运行时异常。是所有Java虚拟机正常操作期间可以被抛出的异常的父类。

 java.lang.SecurityException

 安全异常。由安全管理器抛出，用于指示违反安全情况的异常。

 java.lang.StringIndexOutOfBoundsException

 字符串索引越界异常。当使用索引值访问某个字符串中的字符，而该索引值小于0或大于等于序列大小时，抛出该异常。

 java.lang.TypeNotPresentException

 类型不存在异常。当应用试图以某个类型名称的字符串表达方式访问该类型，但是根据给定的名称又找不到该类型是抛出该异常。该异常与ClassNotFoundException的区别在于该异常是unchecked（不被检查）异常，而ClassNotFoundException是checked（被检查）异常。

 java.lang.UnsupportedOperationException

 不支持的方法异常。指明请求的方法不被支持情况的异常。
 */



