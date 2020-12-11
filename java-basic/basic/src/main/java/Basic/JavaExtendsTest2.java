/**
 * Created by Defias on 2016/3/5.
 *
 * 继承 - 构造器 - 向上转型
 */
package Basic;

class Animalll {
    public Animalll() {
        System.out.println("making an Animall");
    }

    public Animalll(String str) {
        System.out.println(str);
    }

    public void play() {
        System.out.println("play!!!");
    }

    public static void runplay(Animalll o) {
        o.play();
    }
}

class Hippooo extends Animalll {
    public Hippooo() {
        //super();  //调用父类构造函数（必须是构造函数的第一个语句）
                    //因为父类构造器为无参构造器，所以如果此处没有这条语句，编译器会给加上的（如果父类有多个构造函数，默认加没有参数的版本）
        System.out.println("making a Hippooo");
    }

}



public class JavaExtendsTest2 {
    public static void main(String[] args) {
        System.out.println("starting...");
        Hippooo h = new Hippooo();

        Animalll.runplay(h);  //向上转型
    }
}
