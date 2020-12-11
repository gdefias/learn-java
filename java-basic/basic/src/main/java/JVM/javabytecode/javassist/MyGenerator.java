package JVM.javabytecode.javassist;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
/**
 * Created by Defias on 2017/10/9.
 *
 * Javassist
 * 开源的分析、编辑和创建Java字节码的类库
 * javassist是jboss的一个子项目，其主要的优点，在于简单，而且快速。直接使用java编码的形式，而不需要了解虚拟机指令，就能动态改变类的结构，或者动态生成类
 */


public class MyGenerator {
    public static void main(String[] args) throws Exception {
        ///生成Users/yzh/Code/JavaDemo/java-basic/src/main/java/JVM/Classload/example/Programmer.java对应的字节码
        ClassPool pool = ClassPool.getDefault();

        //创建Programmer类
        CtClass cc = pool.makeClass("com.test.Programmer2");
        //定义code方法
        CtMethod method = CtNewMethod.make("public void code(){}", cc);
        //插入方法代码
        method.insertBefore("System.out.println(\"I'm a Programmer,Just Coding.....\");");
        cc.addMethod(method);

        //保存生成的字节码
        cc.writeFile("/Users/yzh/Code/learn-java/java-basic/basic/src/main/java/JVM/javabytecode/javassist/out");
    }
}
