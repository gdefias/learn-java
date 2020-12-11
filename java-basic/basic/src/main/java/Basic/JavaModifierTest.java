package Basic;
//相同包中的测试

public class JavaModifierTest {
    public static void main(String[] args) {
        //受保护的protected方法在相同的包中的任意类（JavaModifierTest）都能访问
        JavaModifier O = new JavaModifier();
        O.setpd(123);
        System.out.println(O.getpd());
    }
}
