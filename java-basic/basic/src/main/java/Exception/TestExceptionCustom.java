package Exception;

/**
 * Created by Defias on 2016/2/28.
 *
 * 自定义异常
 * 尽量使用java提供的异常类，只在遇到不能用预定义的异常类恰当的描述问题时才创建自己的异常类
 *
 * Exception类自己没有定义任何方法。当然它继承了Throwable提供的一些方法。因此，所有异常，包括自己创建的继承Exception类的异常，
 * 都可以获得Throwable定义的方法，可以在自己创建的异常类中覆盖一个或多个这样的方法
 */

public class TestExceptionCustom {

    public static void main(String args[]) {
//        try {
//            compute(1);
//            compute(6);
//            compute(11);
//        } catch (MyException e) {
//            System.out.println("Caught " + e.toString());
//        } catch (SimpleException e) {
//            System.out.println("Caught " + e.toString());
//        }


        try {
            g();
        } catch(MyException2 e) {
            e.printStackTrace(System.out); //打印从方法调用处到异常抛出处的方法调用序列  默认输出到标准错误流  这里重定向到标准输出流
        }
    }


    public static void compute(int a) throws MyException,SimpleException {
        System.out.println("Called compute(" + a + ")");

        if(a > 10)
            throw new MyException(a);

        if(a > 5)
            throw new SimpleException();

        System.out.println("Normal exit");
    }


    public static void g() throws MyException2 {
        System.out.println("Throwing MyException2 from g()");
        throw new MyException2("Originated in g()");
    }
}

//定义自己的异常子类
class SimpleException extends Exception {}

class MyException extends Exception {
    private int detail;

    MyException(int detail) {
        detail = detail;
    }

    public String toString() {  //覆盖
        return "Exception.MyException[" + detail + "]";
    }
}


class MyException2 extends Exception {
    public MyException2() {}

    public MyException2(String msg) {
        super(msg);
    }
}