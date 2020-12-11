package JVM.javasrccode.javapoet;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.Collections;
/**
 * Created by Defias on 2020/07.
 * Description: javapoet    Java源码生成工具
 */

public class TestJavapoet {
    public static void main(String[] args) throws IOException {
        generateHelloworld0();
        //generateHelloworld();
        //generateHelloTest1();

    }

    private static void generateHelloworld0() throws IOException {
        MethodSpec main = MethodSpec.methodBuilder("main")  //main代表方法名
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)  //Modifier修饰的关键字
                .addParameter(String[].class, "args")   //添加string[]类型的名为args的参数
                .addStatement("$T.out.println($S)", System.class,"Hello World") //添加代码，这里$T和$S后面会讲，这里其实就是添加了System,out.println("Hello World");
                .build();

        TypeSpec typeSpec = TypeSpec.classBuilder("HelloWorld")  //HelloWorld是类名
                .addModifiers(Modifier.FINAL,Modifier.PUBLIC)
                .addMethod(main)  //在类中添加方法
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.helloworld", typeSpec)
                .build();

        javaFile.writeTo(System.out);
    }

    public static void generateHelloworld() throws IOException {
        //方法
        MethodSpec main = MethodSpec.methodBuilder("main") //main代表方法名
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)  //Modifier修饰的关键字
                .addParameter(String[].class, "args") //添加string[]类型的名为args的参数
                .addStatement("$T.out.println($S)", System.class,"Hello World")  //添加代码，这里$T和$S后面会讲，这里其实就是添加了System,out.println("Hello World");
                //.addCode("System.out.println(\"Hello World\");\n")  //与addStatement的区别：缺少导入语句
                .build();

        MethodSpec test1 = MethodSpec.methodBuilder("test1")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(int.class)
                .addCode(""
                        + "int total = 0;\n"
                        + "for (int i = 0; i < 10; i++) {\n"
                        + "  total += i;\n"
                        + "}\n"
                        + "return total;\n")
                .build();

        MethodSpec test2 = MethodSpec.methodBuilder("test2")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(int.class)
                .addStatement("int total = 0")
                .beginControlFlow("for (int i = 0; i < 10; i++)")
                .addStatement("total += i")
                .endControlFlow()
                .addStatement("return total")  //使用addStatement无需加分号，会自动加上

                .build();

        //类
        TypeSpec typeSpec = TypeSpec.classBuilder("HelloWorld") //HelloWorld是类名
                .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                .addMethod(main)  //在类中添加方法
                .addMethod(test1)
                .addMethod(test2)
                .build();

        //文件
        JavaFile javaFile = JavaFile.builder("com", typeSpec)
                .addStaticImport(Collections.class, "*")  //静态导入
                .build();

        //输出
        javaFile.writeTo(System.out);
    }


    public static void generateHelloTest1() throws IOException {
        //定义变量var1
        FieldSpec var1 = FieldSpec.builder(String.class, "android")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .initializer("$S + $L", "Lollipop v.", 5.0d)
                .build();

        TypeSpec typeSpec = TypeSpec.classBuilder("HelloTest1")
                .addModifiers(Modifier.FINAL,Modifier.PUBLIC)
                .addField(var1)  //添加变量var1
                .addField(String.class, "robot", Modifier.PRIVATE, Modifier.FINAL)   //定义变量
                .addMethod(computeRange("multiply10to20", 10, 20, "*"))
                .build();

        JavaFile javaFile = JavaFile.builder("com", typeSpec)
                .build();

        javaFile.writeTo(System.out);

    }

    //专门用于生成方法的方法
    public static MethodSpec computeRange(String name, int from, int to, String op) {
        return MethodSpec.methodBuilder(name)
                .returns(int.class)
                .addStatement("int result = 0")
                //.beginControlFlow("for (int i = " + from + "; i < " + to + "; i++)")
                //.addStatement("result = result " + op + " i")
                .beginControlFlow("for (int i = $L; i < $L; i++)", from, to)  //使用占位符
                .addStatement("result = result $L i", op)
                .endControlFlow()
                .addStatement("return result")
                .build();
    }

}
