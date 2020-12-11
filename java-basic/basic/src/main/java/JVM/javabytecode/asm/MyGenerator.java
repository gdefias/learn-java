package JVM.javabytecode.asm;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
/**
 * Created by Defias on 2017/10/9.
 *
 * ASM
 * ASM是一个Java字节码操控框架。它能够以二进制形式修改已有类或者动态生成类。ASM可以直接产生二进制class文件，也可以在类被加载入Java虚拟机之前动态改变
 * 类行为。ASM从类文件中读入信息后，能够改变类行为，分析类信息，甚至能够根据用户要求生成新类。不过ASM在创建class字节码的过程中，操纵的级别是底层JVM的汇
 * 编指令级别
 */



public class MyGenerator {
    public static void main(String[] args) throws IOException {
        ///生成Users/yzh/Code/JavaDemo/java-basic/src/main/java/JVM/Classload/example/Programmer.java对应的字节码
        System.out.println();
        ClassWriter classWriter = new ClassWriter(0);

        //通过visit方法确定类的头部信息
        classWriter.visit(Opcodes.V1_8,// java版本
                Opcodes.ACC_PUBLIC,// 类修饰符
                "Programmer", // 类的全限定名
                null, "java/lang/Object", null);

        //创建构造函数
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        //定义code方法
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn("I'm a Programmer,Just Coding.....");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
        classWriter.visitEnd();

        //使classWriter类已经完成
        //将classWriter转换成字节数组写到文件里面去
        byte[] data = classWriter.toByteArray();
        File file = new File("/Users/yzh/Code/learn-java/java-basic/basic/src/main/java/JVM/javabytecode/asm/out/Programmer.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }
}
