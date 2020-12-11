package Concurrency;
/**
 * Created by Defias on 2020/07.
 * Description: Java并发 - API

 Thread类
 Thread类


 静态API
 静态API
 static int	activeCount()
 static int	activeCount()
 返回当前线程的thread group及其子组中活动线程数的估计
 返回当前线程的thread group及其子组中活动线程数的估计


 static Thread	currentThread()
 static Thread	currentThread()
 返回对当前正在执行的线程对象的引用
 返回对当前正在执行的线程对象的引用


 static void dumpStack()
 static void dumpStack()
 将当前线程的堆栈跟踪打印到标准错误流
 将当前线程的堆栈跟踪打印到标准错误流


 static int	enumerate(Thread[] tarray)
 static int	enumerate(Thread[] tarray)
 将当前线程的线程组及其子组中的每个活动线程复制到指定的数组中
 将当前线程的线程组及其子组中的每个活动线程复制到指定的数组中


 static Map<Thread,StackTraceElement[]>	getAllStackTraces()
 static Map<Thread,StackTraceElement[]>	getAllStackTraces()
 返回所有活动线程的堆栈跟踪图
 返回所有活动线程的堆栈跟踪图


 static Thread.UncaughtExceptionHandler	getDefaultUncaughtExceptionHandler()
 static Thread.UncaughtExceptionHandler	getDefaultUncaughtExceptionHandler()
 返回当线程由于未捕获异常突然终止而调用的默认处理程序
 返回当线程由于未捕获异常突然终止而调用的默认处理程序


 static boolean	holdsLock(Object obj)
 static boolean	holdsLock(Object obj)
 返回 true当且仅当当前线程在指定的对象上保持监视器锁
 返回 true当且仅当当前线程在指定的对象上保持监视器锁


 static boolean	interrupted()
 static boolean	interrupted()
 测试当前线程是否中断
 测试当前线程是否中断


 static void  setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)
 static void  setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)
 设置当线程由于未捕获的异常突然终止而调用的默认处理程序，并且没有为该线程定义其他处理程序
 设置当线程由于未捕获的异常突然终止而调用的默认处理程序，并且没有为该线程定义其他处理程序


 static void  sleep(long millis)
 static void  sleep(long millis)
 使当前正在执行的线程以指定的毫秒数暂停（暂时停止执行），具体取决于系统定时器和调度程序的精度和准确性
 使当前正在执行的线程以指定的毫秒数暂停（暂时停止执行），具体取决于系统定时器和调度程序的精度和准确性


 static void  sleep(long millis, int nanos)
 static void  sleep(long millis, int nanos)
 导致正在执行的线程以指定的毫秒数加上指定的纳秒数来暂停（临时停止执行），这取决于系统定时器和调度器的精度和准确性
 导致正在执行的线程以指定的毫秒数加上指定的纳秒数来暂停（临时停止执行），这取决于系统定时器和调度器的精度和准确性


 static void  yield()
 static void  yield()
 对调度程序的一个暗示，即当前线程愿意让出当前使用的处理器
 对调度程序的一个暗示，即当前线程愿意让出当前使用的处理器




 实例方法
 实例方法
 void	checkAccess()
 void	checkAccess()
 确定当前正在运行的线程是否有权限修改此线程
 确定当前正在运行的线程是否有权限修改此线程


 protected Object	clone()
 protected Object	clone()
 将CloneNotSupportedException作为线程抛出无法有意义地克隆
 将CloneNotSupportedException作为线程抛出无法有意义地克隆


 ClassLoader	getContextClassLoader()
 ClassLoader	getContextClassLoader()
 返回此Thread的上下文ClassLoader。
 返回此Thread的上下文ClassLoader。


 long	getId()
 long	getId()
 返回此线程的标识符
 返回此线程的标识符


 String	getName()
 String	getName()
 返回此线程的名称
 返回此线程的名称


 int	getPriority()
 int	getPriority()
 返回此线程的优先级
 返回此线程的优先级


 StackTraceElement[]	getStackTrace()
 StackTraceElement[]	getStackTrace()
 返回表示此线程的堆栈转储的堆栈跟踪元素数组
 返回表示此线程的堆栈转储的堆栈跟踪元素数组


 Thread.State	getState()
 Thread.State	getState()
 返回此线程的状态
 返回此线程的状态


 ThreadGroup	getThreadGroup()
 ThreadGroup	getThreadGroup()
 返回此线程所属的线程组
 返回此线程所属的线程组


 Thread.UncaughtExceptionHandler	getUncaughtExceptionHandler()
 Thread.UncaughtExceptionHandler	getUncaughtExceptionHandler()
 返回由于未捕获的异常，此线程突然终止时调用的处理程序
 返回由于未捕获的异常，此线程突然终止时调用的处理程序


 void interrupt()
 void interrupt()
 中断这个线程
 中断这个线程


 boolean isAlive()
 boolean isAlive()
 测试这个线程是否活着
 测试这个线程是否活着


 boolean isDaemon()
 boolean isDaemon()
 测试这个线程是否是守护线程
 测试这个线程是否是守护线程


 boolean isInterrupted()
 boolean isInterrupted()
 测试这个线程是否被中断
 测试这个线程是否被中断


 void	join()
 void	join()
 等待这个线程死亡
 等待这个线程死亡


 void	join(long millis)
 void	join(long millis)
 等待这个线程死亡最多 millis毫秒
 等待这个线程死亡最多 millis毫秒


 void	join(long millis, int nanos)
 void	join(long millis, int nanos)
 等待最多 millis毫秒加上 nanos纳秒这个线程死亡
 等待最多 millis毫秒加上 nanos纳秒这个线程死亡


 void	run()
 void	run()
 如果这个线程使用单独的Runnable运行对象构造，则调用该Runnable对象的run方法; 否则，此方法不执行任何操作并返回
 如果这个线程使用单独的Runnable运行对象构造，则调用该Runnable对象的run方法; 否则，此方法不执行任何操作并返回


 void	setContextClassLoader(ClassLoader cl)
 void	setContextClassLoader(ClassLoader cl)
 设置此线程的上下文ClassLoader
 设置此线程的上下文ClassLoader


 void	setDaemon(boolean on)
 void	setDaemon(boolean on)
 将此线程标记为 daemon线程或用户线程
 将此线程标记为 daemon线程或用户线程


 void	setName(String name)
 void	setName(String name)
 将此线程的名称更改为等于参数 name
 将此线程的名称更改为等于参数 name


 void	setPriority(int newPriority)
 void	setPriority(int newPriority)
 更改此线程的优先级
 更改此线程的优先级


 void	setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)
 void	setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)
 设置当该线程由于未捕获的异常而突然终止时调用的处理程序
 设置当该线程由于未捕获的异常而突然终止时调用的处理程序


 void	start()
 void	start()
 导致此线程开始执行; Java虚拟机调用此线程的run方法
 导致此线程开始执行; Java虚拟机调用此线程的run方法


 String	toString()
 String	toString()
 返回此线程的字符串表示，包括线程的名称，优先级和线程组
 返回此线程的字符串表示，包括线程的名称，优先级和线程组




 （Java 1.1或更早版本 已废弃的方法）
 （Java 1.1或更早版本 已废弃的方法）
 suspend和resume：阻塞和唤醒
 suspend和resume：阻塞和唤醒
 stop： 暂停
 stop： 暂停








 Thread的内嵌类
 Thread的内嵌类
 static class Thread.State  线程状态
 static class Thread.State  线程状态


 static Thread.State valueOf(String name)
 static Thread.State valueOf(String name)
 以指定的名称返回此类型的枚举常量
 以指定的名称返回此类型的枚举常量


 static Thread.State[]	values()
 static Thread.State[]	values()
 按照它们声明的顺序返回一个包含此枚举类型常量的数组
 按照它们声明的顺序返回一个包含此枚举类型常量的数组










 ThreadGroup类
 ThreadGroup类


 int	activeCount()
 int	activeCount()
 返回此线程组及其子组中活动线程数的估计
 返回此线程组及其子组中活动线程数的估计


 int	activeGroupCount()
 int	activeGroupCount()
 返回此线程组及其子组中活动组数的估计
 返回此线程组及其子组中活动组数的估计


 void	checkAccess()
 void	checkAccess()
 确定当前运行的线程是否有权限修改此线程组
 确定当前运行的线程是否有权限修改此线程组


 void	destroy()
 void	destroy()
 销毁此线程组及其所有子组
 销毁此线程组及其所有子组


 int	enumerate(Thread[] list)
 int	enumerate(Thread[] list)
 将此线程组及其子组中的每个活动线程复制到指定的数组中
 将此线程组及其子组中的每个活动线程复制到指定的数组中


 int	enumerate(Thread[] list, boolean recurse)
 int	enumerate(Thread[] list, boolean recurse)
 将此线程组中的每个活动线程复制到指定的数组中
 将此线程组中的每个活动线程复制到指定的数组中


 int	enumerate(ThreadGroup[] list)
 int	enumerate(ThreadGroup[] list)
 复制到该线程组及其子组中每个活动子组的指定数组引用
 复制到该线程组及其子组中每个活动子组的指定数组引用


 int	enumerate(ThreadGroup[] list, boolean recurse)
 int	enumerate(ThreadGroup[] list, boolean recurse)
 复制到该线程组中每个活动子组的指定数组引用
 复制到该线程组中每个活动子组的指定数组引用


 int	getMaxPriority()
 int	getMaxPriority()
 返回此线程组的最大优先级
 返回此线程组的最大优先级


 String	getName()
 String	getName()
 返回此线程组的名称
 返回此线程组的名称


 ThreadGroup	getParent()
 ThreadGroup	getParent()
 返回此线程组的父级
 返回此线程组的父级


 void	interrupt()
 void	interrupt()
 中断此线程组中的所有线程
 中断此线程组中的所有线程


 boolean	isDaemon()
 boolean	isDaemon()
 测试此线程组是否是守护线程组
 测试此线程组是否是守护线程组


 boolean	isDestroyed()
 boolean	isDestroyed()
 测试此线程组是否已被破坏
 测试此线程组是否已被破坏


 void	list()
 void	list()
 将有关此线程组的信息打印到标准输出
 将有关此线程组的信息打印到标准输出


 boolean	parentOf(ThreadGroup g)
 boolean	parentOf(ThreadGroup g)
 测试此线程组是线程组参数还是其祖先线程组之一
 测试此线程组是线程组参数还是其祖先线程组之一


 void	setDaemon(boolean daemon)
 void	setDaemon(boolean daemon)
 更改此线程组的守护程序状态
 更改此线程组的守护程序状态


 void	setMaxPriority(int pri)
 void	setMaxPriority(int pri)
 设置组的最大优先级
 设置组的最大优先级


 String	toString()
 String	toString()
 返回此Thread组的字符串表示形式
 返回此Thread组的字符串表示形式


 void	uncaughtException(Thread t, Throwable e)
 void	uncaughtException(Thread t, Throwable e)
 当此线程组中的线程因为一个未捕获的异常由Java Virtual Machine调用，而线程不具有特定Thread.UncaughtExceptionHandler安装
 当此线程组中的线程因为一个未捕获的异常由Java Virtual Machine调用，而线程不具有特定Thread.UncaughtExceptionHandler安装






 ThreadLocal类
 ThreadLocal类


 ThreadLocal() 创建线程局部变量
 ThreadLocal() 创建线程局部变量
 T	get()  返回当前线程的此线程局部变量的副本中的值
 T	get()  返回当前线程的此线程局部变量的副本中的值
 protected T initialValue()  返回此线程局部变量的当前线程的“初始值”
 protected T initialValue()  返回此线程局部变量的当前线程的“初始值”
 void	remove()  删除此线程局部变量的当前线程的值
 void	remove()  删除此线程局部变量的当前线程的值
 void	set(T value) 将当前线程的此线程局部变量的副本设置为指定的值
 void	set(T value) 将当前线程的此线程局部变量的副本设置为指定的值
 static <S> ThreadLocal<S>	withInitial(Supplier<? extends S> supplier) 创建线程局部变量
 static <S> ThreadLocal<S>	withInitial(Supplier<? extends S> supplier) 创建线程局部变量






 ExecutorService接口
 ExecutorService接口


 boolean	awaitTermination(long timeout, TimeUnit unit)
 boolean	awaitTermination(long timeout, TimeUnit unit)
 阻止所有任务在关闭请求完成后执行，或发生超时，或当前线程中断，以先到者为准
 阻止所有任务在关闭请求完成后执行，或发生超时，或当前线程中断，以先到者为准
 
 
 <T> List<Future<T>>	invokeAll(Collection<? extends Callable<T>> tasks)
 <T> List<Future<T>>	invokeAll(Collection<? extends Callable<T>> tasks)
 执行给定的任务，返回持有他们的状态和结果的所有完成的Future列表
 执行给定的任务，返回持有他们的状态和结果的所有完成的Future列表
 
 
 <T> List<Future<T>>	invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
 <T> List<Future<T>>	invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
 执行给定的任务，返回在所有完成或超时到期时持有其状态和结果的Future列表，以先发生者为准
 执行给定的任务，返回在所有完成或超时到期时持有其状态和结果的Future列表，以先发生者为准
 
 
 <T> T	invokeAny(Collection<? extends Callable<T>> tasks)
 <T> T	invokeAny(Collection<? extends Callable<T>> tasks)
 执行给定的任务，返回一个成功完成的结果（即没有抛出异常），如果有的话
 执行给定的任务，返回一个成功完成的结果（即没有抛出异常），如果有的话


 <T> T	invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
 <T> T	invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
 执行给定的任务，返回一个已经成功完成的结果（即，不抛出异常），如果有的话在给定的超时之前过去
 执行给定的任务，返回一个已经成功完成的结果（即，不抛出异常），如果有的话在给定的超时之前过去


 boolean	isShutdown()
 boolean	isShutdown()
 如果此执行者已关闭，则返回 true
 如果此执行者已关闭，则返回 true


 boolean	isTerminated()
 boolean	isTerminated()
 如果所有任务在关闭后完成，则返回 true
 如果所有任务在关闭后完成，则返回 true


 void	shutdown()
 void	shutdown()
 启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务
 启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务


 List<Runnable>	shutdownNow()
 List<Runnable>	shutdownNow()
 尝试停止所有主动执行的任务，停止等待任务的处理，并返回正在等待执行的任务列表
 尝试停止所有主动执行的任务，停止等待任务的处理，并返回正在等待执行的任务列表


 <T> Future<T>	submit(Callable<T> task)
 <T> Future<T>	submit(Callable<T> task)
 提交值返回任务以执行，并返回代表任务待处理结果的Future
 提交值返回任务以执行，并返回代表任务待处理结果的Future


 Future<?>	submit(Runnable task)
 Future<?>	submit(Runnable task)
 提交一个可运行的任务执行，并返回一个表示该任务的未来
 提交一个可运行的任务执行，并返回一个表示该任务的未来


 <T> Future<T>	submit(Runnable task, T result)
 <T> Future<T>	submit(Runnable task, T result)
 提交一个可运行的任务执行，并返回一个表示该任务的未来
 提交一个可运行的任务执行，并返回一个表示该任务的未来










 Executors类
 Executors类


 static Callable<Object> callable(PrivilegedAction<?> action)
 static Callable<Object> callable(PrivilegedAction<?> action)
 返回一个Callable对象，当被调用时，它运行给定的特权动作并返回其结果
 返回一个Callable对象，当被调用时，它运行给定的特权动作并返回其结果


 static Callable<Object>	callable(PrivilegedExceptionAction<?> action)
 static Callable<Object>	callable(PrivilegedExceptionAction<?> action)
 返回一个Callable对象，该对象在被调用时运行给定的特权异常操作并返回其结果
 返回一个Callable对象，该对象在被调用时运行给定的特权异常操作并返回其结果


 static Callable<Object>	callable(Runnable task)
 static Callable<Object>	callable(Runnable task)
 返回一个Callable对象，当被调用时，它运行给定的任务并返回null
 返回一个Callable对象，当被调用时，它运行给定的任务并返回null


 static <T> Callable<T>	callable(Runnable task, T result)
 static <T> Callable<T>	callable(Runnable task, T result)
 返回一个Callable对象，当被调用时，它运行给定的任务并返回给定的结果
 返回一个Callable对象，当被调用时，它运行给定的任务并返回给定的结果


 static ThreadFactory	defaultThreadFactory()
 static ThreadFactory	defaultThreadFactory()
 返回用于创建新线程的默认线程工厂
 返回用于创建新线程的默认线程工厂


 static ExecutorService	newCachedThreadPool()
 static ExecutorService	newCachedThreadPool()
 创建一个根据需要创建新线程的线程池，但在可用时将重新使用以前构造的线程
 创建一个根据需要创建新线程的线程池，但在可用时将重新使用以前构造的线程


 static ExecutorService	newCachedThreadPool(ThreadFactory threadFactory)
 static ExecutorService	newCachedThreadPool(ThreadFactory threadFactory)
 创建一个根据需要创建新线程的线程池，但在可用时将重新使用以前构造的线程，并在需要时使用提供的ThreadFactory创建新线程
 创建一个根据需要创建新线程的线程池，但在可用时将重新使用以前构造的线程，并在需要时使用提供的ThreadFactory创建新线程


 static ExecutorService	newFixedThreadPool(int nThreads)
 static ExecutorService	newFixedThreadPool(int nThreads)
 创建一个线程池，该线程池重用固定数量的从共享无界队列中运行的线程
 创建一个线程池，该线程池重用固定数量的从共享无界队列中运行的线程


 static ExecutorService	newFixedThreadPool(int nThreads, ThreadFactory threadFactory)
 static ExecutorService	newFixedThreadPool(int nThreads, ThreadFactory threadFactory)
 创建一个线程池，重用固定数量的线程，从共享无界队列中运行，使用提供的ThreadFactory在需要时创建新线程
 创建一个线程池，重用固定数量的线程，从共享无界队列中运行，使用提供的ThreadFactory在需要时创建新线程


 static ScheduledExecutorService	newScheduledThreadPool(int corePoolSize)
 static ScheduledExecutorService	newScheduledThreadPool(int corePoolSize)
 创建一个线程池，可以调度命令在给定的延迟之后运行，或定期执行
 创建一个线程池，可以调度命令在给定的延迟之后运行，或定期执行


 static ScheduledExecutorService	newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory)
 static ScheduledExecutorService	newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory)
 创建一个线程池，可以调度命令在给定的延迟之后运行，或定期执行
 创建一个线程池，可以调度命令在给定的延迟之后运行，或定期执行


 static ExecutorService	newSingleThreadExecutor()
 static ExecutorService	newSingleThreadExecutor()
 创建一个使用从无界队列运行的单个工作线程的执行程序
 创建一个使用从无界队列运行的单个工作线程的执行程序


 static ExecutorService	newSingleThreadExecutor(ThreadFactory threadFactory)
 static ExecutorService	newSingleThreadExecutor(ThreadFactory threadFactory)
 创建一个使用单个工作线程运行无界队列的执行程序，并在需要时使用提供的ThreadFactory创建一个新线程
 创建一个使用单个工作线程运行无界队列的执行程序，并在需要时使用提供的ThreadFactory创建一个新线程


 static ScheduledExecutorService	newSingleThreadScheduledExecutor()
 static ScheduledExecutorService	newSingleThreadScheduledExecutor()
 创建一个单线程执行器，可以调度命令在给定的延迟之后运行，或定期执行
 创建一个单线程执行器，可以调度命令在给定的延迟之后运行，或定期执行


 static ScheduledExecutorService	newSingleThreadScheduledExecutor(ThreadFactory threadFactory)
 static ScheduledExecutorService	newSingleThreadScheduledExecutor(ThreadFactory threadFactory)
 创建一个单线程执行器，可以调度命令在给定的延迟之后运行，或定期执行
 创建一个单线程执行器，可以调度命令在给定的延迟之后运行，或定期执行


 static ExecutorService	newWorkStealingPool()
 static ExecutorService	newWorkStealingPool()
 创建使用所有 available processors作为其目标并行级别的工作窃取线程池
 创建使用所有 available processors作为其目标并行级别的工作窃取线程池


 static ExecutorService	newWorkStealingPool(int parallelism)
 static ExecutorService	newWorkStealingPool(int parallelism)
 创建一个维护足够的线程以支持给定的并行级别的线程池，并且可以使用多个队列来减少争用
 创建一个维护足够的线程以支持给定的并行级别的线程池，并且可以使用多个队列来减少争用


 static <T> Callable<T>	privilegedCallable(Callable<T> callable)
 static <T> Callable<T>	privilegedCallable(Callable<T> callable)
 返回一个Callable对象，当被调用时，将在当前访问控制上下文中执行给定的callable
 返回一个Callable对象，当被调用时，将在当前访问控制上下文中执行给定的callable


 static <T> Callable<T>	privilegedCallableUsingCurrentClassLoader(Callable<T> callable)
 static <T> Callable<T>	privilegedCallableUsingCurrentClassLoader(Callable<T> callable)
 返回一个Callable对象，当被调用时，将在当前访问控制上下文中执行给定的callable ，当前上下文类加载器作为上下文类加载器
 返回一个Callable对象，当被调用时，将在当前访问控制上下文中执行给定的callable ，当前上下文类加载器作为上下文类加载器


 static ThreadFactory	privilegedThreadFactory()
 static ThreadFactory	privilegedThreadFactory()
 返回一个用于创建与当前线程具有相同权限的新线程的线程工厂
 返回一个用于创建与当前线程具有相同权限的新线程的线程工厂


 static ExecutorService	unconfigurableExecutorService(ExecutorService executor)
 static ExecutorService	unconfigurableExecutorService(ExecutorService executor)
 返回一个将所有定义的ExecutorService方法委托给给定执行程序的对象，但不能以其他方式使用转换方式访问
 返回一个将所有定义的ExecutorService方法委托给给定执行程序的对象，但不能以其他方式使用转换方式访问


 static ScheduledExecutorService	unconfigurableScheduledExecutorService(ScheduledExecutorService executor)
 static ScheduledExecutorService	unconfigurableScheduledExecutorService(ScheduledExecutorService executor)
 返回一个将所有定义的ScheduledExecutorService方法委托给给定执行程序的对象，但不能以其他方式使用转换方式访问
 返回一个将所有定义的ScheduledExecutorService方法委托给给定执行程序的对象，但不能以其他方式使用转换方式访问






 ReentrantLock类
 ReentrantLock类


 构造方法
 构造方法
 Constructor and Description ReentrantLock()
 Constructor and Description ReentrantLock()
 创建一个 ReentrantLock的实例
 创建一个 ReentrantLock的实例


 ReentrantLock(boolean fair)
 ReentrantLock(boolean fair)
 根据给定的公平政策创建一个 ReentrantLock的实例
 根据给定的公平政策创建一个 ReentrantLock的实例




 boolean isLocked()
 boolean isLocked()
 查询此锁是否由任何线程持有
 查询此锁是否由任何线程持有


 void lock()
 void lock()
 获得锁
 获得锁


 void	lockInterruptibly()
 void	lockInterruptibly()
 获取锁定，除非当前线程是 interrupted
 获取锁定，除非当前线程是 interrupted


 Condition	newCondition()
 Condition	newCondition()
 返回Condition用于这种用途实例Lock实例
 返回Condition用于这种用途实例Lock实例


 String	toString()
 String	toString()
 返回一个标识此锁的字符串以及其锁定状态
 返回一个标识此锁的字符串以及其锁定状态


 boolean tryLock()
 boolean tryLock()
 只有在调用时它不被另一个线程占用才能获取锁
 只有在调用时它不被另一个线程占用才能获取锁


 boolean	tryLock(long timeout, TimeUnit unit)
 boolean	tryLock(long timeout, TimeUnit unit)
 如果在给定的等待时间内没有被另一个线程占用 ，并且当前线程尚未被保留，则获取该锁（interrupted）
 如果在给定的等待时间内没有被另一个线程占用 ，并且当前线程尚未被保留，则获取该锁（interrupted）


 void	unlock()
 void	unlock()
 尝试释放此锁
 尝试释放此锁




 原子性变量类
 原子性变量类
 java.util.concurrent.atomic包
 java.util.concurrent.atomic包


 AtomicBoolean
 AtomicBoolean
 一个 boolean值可以用原子更新
 一个 boolean值可以用原子更新


 AtomicInteger
 AtomicInteger
 可能原子更新的 int值
 可能原子更新的 int值


 AtomicIntegerArray
 AtomicIntegerArray
 一个 int数组，其中元素可以原子更新
 一个 int数组，其中元素可以原子更新


 AtomicIntegerFieldUpdater<T>
 AtomicIntegerFieldUpdater<T>
 基于反射的实用程序，可以对指定类的指定的 volatile int字段进行原子更新
 基于反射的实用程序，可以对指定类的指定的 volatile int字段进行原子更新


 AtomicLong
 AtomicLong
 一个 long值可以用原子更新
 一个 long值可以用原子更新


 AtomicLongArray
 AtomicLongArray
 可以 long地更新元素的 long数组
 可以 long地更新元素的 long数组


 AtomicLongFieldUpdater<T>
 AtomicLongFieldUpdater<T>
 基于反射的实用程序，可以对指定类的指定的 volatile long字段进行原子更新
 基于反射的实用程序，可以对指定类的指定的 volatile long字段进行原子更新


 AtomicMarkableReference<V>
 AtomicMarkableReference<V>
 AtomicMarkableReference维护一个对象引用以及可以原子更新的标记位
 AtomicMarkableReference维护一个对象引用以及可以原子更新的标记位


 AtomicReference<V>
 AtomicReference<V>
 可以原子更新的对象引用
 可以原子更新的对象引用


 AtomicReferenceArray<E>
 AtomicReferenceArray<E>
 可以以原子方式更新元素的对象引用数组
 可以以原子方式更新元素的对象引用数组


 AtomicReferenceFieldUpdater<T,V>
 AtomicReferenceFieldUpdater<T,V>
 一种基于反射的实用程序，可以对指定类的指定的 volatile volatile引用原子更新
 一种基于反射的实用程序，可以对指定类的指定的 volatile volatile引用原子更新


 AtomicStampedReference<V>
 AtomicStampedReference<V>
 AtomicStampedReference维护对象引用以及可以原子更新的整数“印记”
 AtomicStampedReference维护对象引用以及可以原子更新的整数“印记”


 DoubleAccumulator
 DoubleAccumulator
 一个或多个变量一起维护使用提供的功能更新的运行的值 double
 一个或多个变量一起维护使用提供的功能更新的运行的值 double


 DoubleAdder
 DoubleAdder
 一个或多个变量一起保持初始为零 double和
 一个或多个变量一起保持初始为零 double和


 LongAccumulator
 LongAccumulator
 一个或多个变量，它们一起保持运行 long使用所提供的功能更新值
 一个或多个变量，它们一起保持运行 long使用所提供的功能更新值


 LongAdder
 LongAdder
 一个或多个变量一起保持初始为零 long总和
 一个或多个变量一起保持初始为零 long总和






 AtomicInteger类
 AtomicInteger类


 AtomicInteger()
 AtomicInteger()
 创建一个新的AtomicInteger，初始值为 0
 创建一个新的AtomicInteger，初始值为 0


 AtomicInteger(int initialValue)
 AtomicInteger(int initialValue)
 用给定的初始值创建一个新的AtomicInteger
 用给定的初始值创建一个新的AtomicInteger


 int	accumulateAndGet(int x, IntBinaryOperator accumulatorFunction)
 int	accumulateAndGet(int x, IntBinaryOperator accumulatorFunction)
 使用将给定函数应用于当前值和给定值的结果原子更新当前值，返回更新后的值
 使用将给定函数应用于当前值和给定值的结果原子更新当前值，返回更新后的值


 int	addAndGet(int delta)
 int	addAndGet(int delta)
 将给定的值原子地添加到当前值
 将给定的值原子地添加到当前值


 boolean	compareAndSet(int expect, int update)
 boolean	compareAndSet(int expect, int update)
 如果当前值 ==为预期值，则将该值原子设置为给定的更新值
 如果当前值 ==为预期值，则将该值原子设置为给定的更新值


 int	decrementAndGet()
 int	decrementAndGet()
 原子减1当前值
 原子减1当前值


 float	floatValue()
 float	floatValue()
 返回此值 AtomicInteger为 float一个宽元转换后
 返回此值 AtomicInteger为 float一个宽元转换后


 int	get()
 int	get()
 获取当前值
 获取当前值


 void	lazySet(int newValue)
 void	lazySet(int newValue)
 最终设定为给定值
 最终设定为给定值


 long	longValue()
 long	longValue()
 返回此值 AtomicInteger为 long一个宽元转换后
 返回此值 AtomicInteger为 long一个宽元转换后


 void	set(int newValue)
 void	set(int newValue)
 设置为给定值
 设置为给定值




 */

