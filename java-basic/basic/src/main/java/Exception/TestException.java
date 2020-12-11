package Exception;

import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2016/2/28.
 *
 * 异常
 *
 * 所有异常类型都是内置类Throwable的子类。因此，Throwable在异常类层次结构的顶层
 *
 * Throwable下面的是两个把异常分成两个不同分支的子类。一个分支是Exception（子类RuntimeException ...），另一类分支由Error:
 *      Error: 系统错误，由java虚拟机抛出，较少发生
 *      Exception： 由程序或外部环境引起的错误
 *      RuntimeException： 描述的是程序设计错误，如错误的类型转换、数组越界等。。。
 *
 * 免检异常: RuntimeException、Error以及它们的子类  不强制要求编写代码捕获或声明免检异常
 * 必检异常: 免检异常以外的所有其他异常   编译器会强制程序员检查并通过try-catch块处理它们，或者在方法头进行声明
 *
 *
 * 异常说明
 * 如果一个方法可以导致一个异常但不处理它，必须指定这种行为以使方法的调用者可以保护它们自己而不发生异常
 * 可以在方法声明中包含一个throws子句，列举方法可能抛出的所有异常类型
 *
 * 异常说明的意思：
 * 1、我的代码会产生这些异常，这由你来处理
 * 2、我的代码忽略了这些异常，这由你来处理
 *
 * 对于除Error或RuntimeException及它们子类以外类型的所有异常是必要的。一个方法可以抛出的所有其他类型的异常必须在throws子句中声明。
 * 如果不这样做，将会导致编译错误
 *
 *  Throwable中定义的方法：
 *  方法	                                     描述
 *  Throwable fillInStackTrace()	        返回一个包含完整堆栈轨迹的Throwable对象（记录栈帧的当前状态），该对象可能被再次引发
 *  String getLocalizedMessage()	        返回一个异常的局部描述
 *  String getMessage()	                返回一个异常的描述
 *  void printStackTrace()	                显示堆栈轨迹（默认输出到标准错误流）
 *  StackTraceElement getStackTrace()       访问由printStackTrace()打印输出的栈跟踪信息
 *  void printStackTrace(PrintStreamstream)	把堆栈轨迹送到指定的流
 *  void printStackTrace(PrintWriterstream)	把堆栈轨迹送到指定的流
 *  String toString()	                返回一个包含异常描述的String对象。当输出一个Throwable对象时，该方法被println( )调用
 */


public class TestException {
    public static void main(String args[]) {
        testtry(args);

        try {
            demoproc();
        } catch(NullPointerException e) {
            System.out.println("Recaught: " + e);
        }

        try {
            throwOne();
        } catch (IllegalAccessException e) {
            System.out.println("Caught： " + e);
        }
    }

    public static void testtry(String args[]) {
        try {
            int a = args.length;
            System.out.println("a = " + a);
            int b = 42 / a;
            int c[] = { 1 };
            c[42] = 99;
        } catch(ArithmeticException e) {
            System.out.println("Divide by 0: " + e);
            //throw e;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Collection.Array index oob: " + e);
            //throw e;
        }
        System.out.println("After try/catch blocks.");
    }

    //重新抛出异常
    public static void demoproc() {
        try {
            throw new NullPointerException("demo");
        } catch(NullPointerException e) {
            System.out.println("Caught inside demoproc.");
            e.printStackTrace();  //显示的是原来异常抛出点的调用栈信息
            //throw e; // 重新抛出异常
        }
    }

    //异常说明（表示throwOne方法可能会抛出IllegalAccessException异常）
    public static void throwOne()  throws IllegalAccessException {
        System.out.println("Inside throwOne.");
        throw new IllegalAccessException("demo");
    }


    //从异常中获取信息
    public static void getExceptionInfo() throws InterruptedException {
        try {
            System.out.println(sum(new int[] {1,2,3,4,5}));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            TimeUnit.SECONDS.sleep(1); //暂停1s等待上一条语句打印完

            System.out.println("----------");
            System.out.println("\n--" + ex.getMessage());
            System.out.println("\n--" + ex.toString());
            System.out.println("\nTrace info obtained from qetstacktrace");

            StackTraceElement[] traceElements = ex.getStackTrace();
            for(int i=0; i<traceElements.length; i++) {
                System.out.print("method " + traceElements[i].getMethodName());
                System.out.print("(" + traceElements[i].getClassName() + ":");
                System.out.println(traceElements[i].getLineNumber() + ")");
            }
        }
    }

    private static int sum(int[] list) {
        int result = 0;
        for(int i=0; i<=list.length; i++) {  //数组越界异常
            result += list[i];
        }
        return result;
    }
}