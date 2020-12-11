package JVM.MEM.OOM;

/**
 * Created by Defias on 2020/09.
 * Description: 演示虚拟机栈和本地方法栈溢出  -- OutOfMemoryError异常

 VM Args:-Xss2M(这时候不妨设置大些)

 为每个线程的栈分配的内存越大，反而越容易产生内存溢出异常

 因为操作系统分配给每个进程的内存是有限制的，譬如32位的Windows限制为2GB。虚拟机提供了参数来控制Java堆和方法区的这两部分内存的最大值。
 剩余的内存为2GB（操作系统限制）减去Xmx（最大堆容量），再减去MaxPermSize（最大方法区容量），程序计数器消耗内存很小，可以忽略掉。如果
 虚拟机进程本身耗费的内存不计算在内，剩下的内存就由虚拟机栈和本地方法栈“瓜分”了。每个线程分配到的栈容量越大，可以建立的线程数量自然就
 越少，建立线程时就越容易把剩下的内存耗尽

 */
public class JavaVMStackOOM {
    private void dontStop(){
        while(true){
        }
    }
    public void stackLeakByThread(){
        while(true){
            Thread thread=new Thread(new Runnable(){
                @Override
                public void run(){
                    dontStop();
                }
            });
            thread.start();
        }
    }
    public static void main(String[]args)throws Throwable{
        JavaVMStackOOM oom=new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}