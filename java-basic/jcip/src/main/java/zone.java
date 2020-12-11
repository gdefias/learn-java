public @interface zone {}
/**
 * Created by Defias on 2020/09.
 * Description: Java并发编程实战

 线程的优势：
 1、发挥多处理器的强大能力
 2、建模的简单性
 3、异步事件的简化处理
 4、响应更灵敏的用户界面

 线程带来的风险：
 1、安全性问题
 2、活跃性问题：死锁、饥饿、活锁
 3、性能问题




 并发性标注/注解说明

 类的标注
 这三个类级别的标注可以描述类的线程安全保证性,属于类公开文档的一部分.它只是标注了该类是否是线程安全的,但实际上没法保证线程安全

 @Immutable
 表示类是不可变得既是final修饰的,它是线程安全的

 @ThreadSafe
 类是线程安全的

 @NotThreadSafe
 类不是线程安全的，如果类未加任何注解，则不能确定是否线程安全，认为是非线程安全的


 域和方法的标注
 
 GuardedBy(lock) 表示只有在持有了某个特定的锁时才能访问这个域或方法。参数lock表示在访问被标注的域或方法时需要线程持有与之对应的锁
 
 lock的可能取值:
 @GuardedBy("this") 
 表示在包含对象上的内置锁(被标注的方法或域是该对象的成员)
 
 @GuardedBy("fieldName")
 表示与filedName引用的对象相关联的锁,可以是一个隐式锁(对于不引用一个Lock的域),也可以是一个显式锁(对于引用了一个Lock的域)

 @GuardedBy("ClassName.fieldName")
 类似于@GuardedBy("fieldName")，不过所引用的锁对象是存储在另一个类（或本类）中的静态域

 @GuardedBy("methodName()")
 指通过调用命名方法返回的锁对象

 @GuardedBy("ClassName.class")
 指命名类的类字面量对象

 */

