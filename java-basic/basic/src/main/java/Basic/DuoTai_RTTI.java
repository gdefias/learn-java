package Basic;

/**
 * Created by Defias on 2020/07.
 * Description:  向下转型
 *
 * RTTI： 运行时类型识别
 */

class Useful {
    public void f() { System.out.println("Useful f"); }
    public void g() { System.out.println("Useful g"); }
}

class MoreUseful extends Useful {
    public void f() { System.out.println("MoreUseful f"); }
    public void g() { System.out.println("MoreUseful g"); }
    public void u() { System.out.println("MoreUseful u"); }
    public void v() { System.out.println("MoreUseful v"); }
    public void w() { System.out.println("MoreUseful w"); }
}

public class DuoTai_RTTI {
    public static void main(String[] args) {
        Useful[] x = {
                new Useful(),
                new MoreUseful()
        };      //放入数组时MoreUseful向上转型为Useful
        x[0].f();
        x[0].g();

        x[1].f();   //RTTI 从数组中取出时转型回MoreUseful
        x[1].g();

        // Compile time: method not found in Useful:
        //x[1].u();
        ((MoreUseful)x[1]).u(); // Downcast/向下转型
//        ((MoreUseful)x[0]).u(); // Exception thrown
    }

}