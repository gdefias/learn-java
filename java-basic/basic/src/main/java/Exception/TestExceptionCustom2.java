package Exception;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description: 自定义异常
 */
public class TestExceptionCustom2 {

    public static void main(String[] args) {
        try {
            f();
        } catch(MyException3 e) {
            e.printStackTrace(System.out);
        }
        try {
            g();
        } catch(MyException3 e) {
            e.printStackTrace(System.out);
        }
        try {
            h();
        } catch(MyException3 e) {
            e.printStackTrace(System.out);
            System.out.println("e.val() = " + e.val());
        }
    }

    public static void f() throws MyException3 {
        print("Throwing MyException2 from f()");
        throw new MyException3();
    }

    public static void g() throws MyException3 {
        print("Throwing MyException2 from g()");
        throw new MyException3("Originated in g()");
    }

    public static void h() throws MyException3 {
        print("Throwing MyException2 from h()");
        throw new MyException3("Originated in h()", 47);
    }
}



class MyException3 extends Exception {
    private int x;
    public MyException3() {}
    public MyException3(String msg) { super(msg); }
    public MyException3(String msg, int x) {
        super(msg);
        this.x = x;
    }
    public int val() {
        return x;
    }

    public String getMessage() {
        return "Detail Message: "+ x + " "+ super.getMessage();
    }
}