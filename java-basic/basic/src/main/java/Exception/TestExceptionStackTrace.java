package Exception;
import static Basic.Print.*;

/**
 * Created with IntelliJ IDEA.
 * Description:  异常跟踪
 * User: Defias
 * Date: 2018-05
 */
import java.util.*;

public class TestExceptionStackTrace {
    public static void main(String[] args) {

        //testFactorial();
        //test0();
        testWhoCalled();
    }

    public static void test0() {
        try {
            throw new Exception("My Exception");
        } catch(Exception e) {
            print("Caught Exception");
            print("getMessage():" + e.getMessage());
            print("getLocalizedMessage():" + e.getLocalizedMessage());
            print("toString():" + e);
            print("printStackTrace():");
            e.printStackTrace(System.out);
        }
    }

    //调用链
    public static void testWhoCalled() {
        f();
        System.out.println("--------------------------------");
        g();
        System.out.println("--------------------------------");
        h();
    }

    static void f() {
        // Generate an exception to fill in the stack trace
        try {
            throw new Exception();
        } catch (Exception e) {
            for(StackTraceElement ste : e.getStackTrace())
                System.out.println(ste.getMethodName());
        }
    }

    static void g() { f(); }

    static void h() { g(); }



    public static void testFactorial() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = in.nextInt();
        factorial(n);
    }


    //计算n! = 1 * 2 * . . . * n
    public static int factorial(int n) {
        System.out.println("factorial(" + n + "):");
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();  //返回由栈轨迹中元素所构成的数组（printStackTrace方法所提供的信息）
        for (StackTraceElement f : frames)
            System.out.println(f);

        int res = 1;
        if (n <= 1)
            res = 1;
        else
            res = n * factorial(n - 1);
        //System.out.println("return " + res);
        return res;
    }
}
