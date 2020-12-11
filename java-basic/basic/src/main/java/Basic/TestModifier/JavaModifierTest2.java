package Basic.TestModifier;
import Basic.JavaModifier;

//测试修饰符protected

public class JavaModifierTest2 extends JavaModifier {
    public static void main(String[] args) {

        //受保护的protected方法在不同的包中的子类(JavaModifierTest2)是能访问的
        JavaModifierTest2 O = new JavaModifierTest2();
        O.setpd(123);
        System.out.println(O.getpd());

        //注意：这样还是不能访问
        JavaModifier O1 = new JavaModifier();
        //O1.setpd(123);
        //System.out.println(O1.getpd());
    }
}
