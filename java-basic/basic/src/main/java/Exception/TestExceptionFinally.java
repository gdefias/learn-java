package Exception;
import static Basic.Print.*;
/**
 * Created by Defias on 2016/2/28.
 *
 * 异常: finally子句
 */

public class TestExceptionFinally {

    public static void main(String args[]) {
//        try {
//            procA();
//        } catch (Exception e) {
//            System.out.println("Exception caught");
//        } finally {
//            System.out.println("A finally");
//        }

//        procB();
//        procC();

        for(int i = 1; i <= 4; i++)
            f(i);
    }

    //ABC3种不同的退出方法。每一个都执行了finally子句
    static void procA() {
        try {
            System.out.println("inside procA");
            throw new RuntimeException("demo");
        } finally {
            System.out.println("procA's finally");
        }
    }

    static void procB() {
        try {
            System.out.println("inside procB");
            return;
        } finally {
            System.out.println("procB's finally");  //仍然会被执行
        }
    }

    static void procC() {
        try {
            System.out.println("inside procC");
        } finally {
            //无论有无真的发生异常，异常处理机制也会在跳到更高一层的异常处理程序之前执行finally子句
            System.out.println("procC's finally");
        }
    }


    public static void f(int i) {
        print("Initialization that requires cleanup");
        try {
            print("Point 1");
            if(i == 1) return;
            print("Point 2");
            if(i == 2) return;
            print("Point 3");
            if(i == 3) return;
            print("End");
            return;
        } finally {
            print("Performing cleanup");
        }
    }
}