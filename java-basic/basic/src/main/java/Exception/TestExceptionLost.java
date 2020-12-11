package Exception;

/**
 * Created by Defias on 2020/07.
 * Description:  异常丢失
 */


public class TestExceptionLost {

    public static void main(String[] args) {
        try {
            LostMessage lm = new LostMessage();
            try {
                lm.f();  //VeryImportantException捕获到了但是没有对应的catch处理，导致丢失了
            } finally {
                lm.dispose();
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        test0();

    }

    //更简单的丢失异常的方式是从finally子句中返回
    public static void test0() {
        try {
            System.out.println("test0");
            throw new RuntimeException();
        } finally {
            // Using 'return' inside the finally block
            // will silence any thrown exception.
            return;
        }
    }
}



class VeryImportantException extends Exception {
    public String toString() {
        return "A very important exception!";
    }
}

class HoHumException extends Exception {
    public String toString() {
        return "A trivial exception";
    }
}

class LostMessage {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }
}


