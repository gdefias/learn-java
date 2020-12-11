package Basic.TestModifier;
import Basic.JavaModifier;
//测试修饰符protected

public class JavaModifierTest {
    public static void main(String[] args) {
        //受保护的protected方法在不同的包中的非子类是不能访问的
        JavaModifier O = new JavaModifier();
        //O.setpd(123);
        //System.out.println(O.getpd());
    }
}
