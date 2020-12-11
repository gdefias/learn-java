/**
 * Created by Defias on 2016/3/6.
 *
 * 赋值与克隆
 *
 * Cloneable接口： 标记接口（接口是空的） 给出一个可克隆的对象
 */
package Basic;

public class JavaClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        test2();

        //Object o1 = new Object();
        //Object o2 = o1.clone();
    }

    //赋值
    public static void test1() throws CloneNotSupportedException {
        Personyy p = new Personyy(23, "zhang");
        Personyy p1 = p;

        System.out.println(p);
        System.out.println(p1);  //打印的地址相同，同一个对象

        //虽然Personyy隐式的继承了Object，但是由于没有重写clone方法，所以还是不能访问
        //Personyy p2 = (Personyy) p.clone();


        p1.setName("Ye");
        String result = (p.getName()==p1.getName() ? "==" : "!=");
        System.out.println(result);

        System.out.println(p.getName());
        System.out.println(p1.getName());
    }


    //浅拷贝
    public static void test2() throws CloneNotSupportedException {
        Persony p = new Persony(23, "zhang");
        Persony p1 = p;

        System.out.println(p);
        System.out.println(p1);  //打印的地址相同，同一个对象

        Persony p2 = new Persony(23, "zhang");
        Persony p3 = (Persony)p2.clone();

        System.out.println(p2);
        System.out.println(p3);  //打印的地址不同，不同的对象
        String result = (p2.getName()==p3.getName() ? "clone是浅拷贝的" : "clone是深拷贝的");
        System.out.println(result);  //clone方法执行的是浅拷贝
    }
}


class Persony implements Cloneable {
    private int age ;
    private String name;

    public Persony(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Persony() {

    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Persony)super.clone();
    }
}

class Personyy {
    private int age ;
    private String name;

    public Personyy(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Personyy() {

    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Personyyy {
    private int age ;
    private String name;

    public Personyyy(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Personyyy() {

    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    //重写clone不必一定实现cloneable接口，只要不调用super.clone即可
    public Object clone() {
        Personyyy O = new Personyyy(0, "");
        return O;
    }

}