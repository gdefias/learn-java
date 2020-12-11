package JVM.MEM.OOM;

/**
 * Created by Defias on 2020/09.
 * Description: 演示虚拟机栈和本地方法栈溢出  -- StackOverflowError异常

 HotSpot虚拟机中并不区分虚拟机栈和本地方法栈
 对于HotSpot来说，虽然-Xoss参数（设置本地方法栈大小）存在，但实际上是无效的，栈容量只由-Xss参数设定

 VM Args:-Xss128k
 */

public class JavaVMStackSOF {
    private int stackLength=1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }
    public static void main(String[]args)throws Throwable{
        JavaVMStackSOF oom=new JavaVMStackSOF();
        try{
            oom.stackLeak();
        }catch(Throwable e){
            System.out.println("stack length:"+oom.stackLength);
            throw e;
        }
    }
}