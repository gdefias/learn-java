/**
 * Created by Jeff on 2016/2/27.
 *
 * Class
 *
 */
package Basic;

public class JavaConstruction0 {
    String name;
    int age;

    JavaConstruction0(String name1, int age1) {
        name = name1;
        age = age1;
        System.out.println("感谢主人领养了我");
    }

    void bark() {
        System.out.println("汪汪，不要过来");
    }

    void hungry(){
        System.out.println("主人，我饿了");
    }

    public static void main(String[] arg) {
        JavaConstruction0 DogO = new JavaConstruction0("花花", 3);
        // 访问成员变量
        String namem = DogO.name;
        int agem = DogO.age;
        System.out.println("我是一只小狗，我名字叫" + namem + "，我" + agem + "岁了");
        // 访问方法
        DogO.bark();
        DogO.hungry();
    }


}
