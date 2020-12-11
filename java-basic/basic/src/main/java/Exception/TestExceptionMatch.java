package Exception;

/**
 * Created by Defias on 2020/07.
 * Description: 异常匹配
 *
 * 抛出异常的时候，异常处理系统会按照代码的书写顺序找到最近的处理程序，找到匹配的处理程序之后，就认为异常得到了处理，然后不再继续查找
 *
 * 通过捕获基类异常可以一次性捕获该基类的整个继承体系中的所有异常
 */
public class TestExceptionMatch {

    public static void main(String[] args) {
        // Catch the exact type:
        try {
            throw new Sneeze();
        } catch(Sneeze s) {
            System.out.println("Caught Sneeze");
        } catch(Annoyance a) {
            System.out.println("Caught Annoyance");
        }

        // Catch the base type:
        try {
            throw new Sneeze();
        } catch(Annoyance a) {  //会捕获Annoyance异常以及所有从Annoyance派生的异常
            System.out.println("Caught Annoyance");
        }
    }
}



class Annoyance extends Exception {}
class Sneeze extends Annoyance {}
class Sneeze2 extends Annoyance {}