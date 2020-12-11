/**
 * Created by Defias on 2016/2/27.
 *
 * this
 */
package Basic;
public class JavaThis2 {
    public static void main(String[] args){
        AA a = new AA();
        BB b = new BB(a); //这里创建BB类的对象b时，对BB类的构造函数传入了AA类的对象的引用（指针）
    }
}

class AA {
    public AA() {
        System.out.println("step 1 ");
        new BB(this).print();  //创建BB类的匿名对象，并且引用了BB类匿名对象的print()方法
        //this作为参数(AA类的对象的引用)传递给BB类的构造方法BB(AA a)
    }
    public void print(){
        System.out.println("step 3");
        System.out.println("Hello from AA!");
    }
}


class BB {
    AA a;
    public BB(AA a){
        System.out.println("DEBBUG---");
        this.a = a;
    }
    public void print() {
        System.out.println("step 2 ");
        a.print();
        System.out.println("step 4 ");
        System.out.println("Hello from BB!");
    }
}