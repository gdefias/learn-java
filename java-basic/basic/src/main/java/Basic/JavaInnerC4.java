package Basic;

/**
 * Created by Defias on 2020/07.
 * Description: 接口内部类 - 接口中的嵌套类
 *
 * 如果想创建某些公共代码，使得它们可以被某个接口的所有不同实现所共用，则可以使用接口内部类
 *
 */
public class JavaInnerC4 {
    public static void main(String[] args) {
        shixian sh = new shixian();
        sh.howdy();
    }
}


interface ClassInInterface {
    void howdy();

    class Test implements ClassInInterface {  //接口中的类自动是public和static的
        public void howdy() {
            System.out.println("Howdy!");
        }
        public static void main(String[] args) {
            new Test().howdy();
        }
    }
}


class shixian implements ClassInInterface {
    public void howdy() {
        Test O = new Test();
        O.howdy();
        System.out.println("shixian");
    }
}