package Schedule;
public @interface zone {}
/**
 * Created by Defias on 2020/08.
 * Description: JDK Timer  ----java提供的简单任务调度功能

 允许按照固定频率重复执行某项任务
 诸如"在每周周一8点执行"的这种和日历相关的任务调度需求，JDK Timer就已经无能为力了


 Timer类 - 定时器 用来在一个后台线程执行指定计划任务
 public class Timer extends Object
 线程调度任务以供将来在后台线程中执行的功能。 任务可以安排一次执行，或定期重复执行
 对应于每个Timer对象是单个后台线程，用于依次执行所有定时器的所有任务，计时器任务应该快速完成

 如果定时器的任务执行线程意外终止，例如，因为它调用了stop方法，那么在计时器上安排任务的任何进一步的尝试将会产生一个
 IllegalStateException ，就像定时器的cancel方法被调用一样
 这个类是线程安全的：多个线程可以共享一个单独的Timer对象，而不需要外部同步

 Timer实际上是个线程，定时调度所拥有的TimerTasks
 注意，javax.swing包中也有一个Timer类，如果import中用到swing包，要注意名字的冲突

 Timer()
 创建一个新的计时器

 Timer(boolean isDaemon)
 创建一个新的定时器，其相关线程可以指定为 run as a daemon（守护线程）

 Timer(String name)
 创建一个新的定时器，其相关线程具有指定的名称

 Timer(String name, boolean isDaemon)
 创建一个新的定时器，其相关线程具有指定的名称，可以指定为 run as a daemon（守护线程）

 void	cancel()
 终止此计时器，丢弃任何当前计划的任务

 int	purge()
 从该计时器的任务队列中删除所有取消的任务

 void	schedule(TimerTask task, Date time)
 在指定的时间安排指定的任务执行

 void	schedule(TimerTask task, Date firstTime, long period)
 从指定的时间开始 ，对指定的任务执行重复的 固定延迟执行

 void	schedule(TimerTask task, long delay)
 在指定的延迟之后安排指定的任务执行

 void	schedule(TimerTask task, long delay, long period)
 在指定 的延迟之后开始 ，重新执行 固定延迟执行的指定任务

 void	scheduleAtFixedRate(TimerTask task, Date firstTime, long period)
 从指定的时间 开始 ，对指定的任务执行重复的 固定速率执行

 void	scheduleAtFixedRate(TimerTask task, long delay, long period)
 在指定的延迟之后 开始 ，重新执行 固定速率的指定任务



 TimerTask抽象类  ---相当于Job
 public abstract class TimerTask extends Object implements Runnable
 可以由定时器进行一次性或重复执行的任务
 一个TimerTask实际上就是一个拥有run方法的类，需要定时执行的代码放到run方法体内

 protected	TimerTask()
 创建一个新的计时器任务

 boolean	cancel()
 取消此计时器任务

 abstract void	run()
 该定时器任务要执行的操作

 long	scheduledExecutionTime()
 返回此任务最近 实际执行的 预定执行时间


 */

