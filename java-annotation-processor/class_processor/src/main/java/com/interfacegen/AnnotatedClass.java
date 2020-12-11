package com.interfacegen;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Defias on 2020/07.
 * Description:
 */

public class AnnotatedClass {
    private String packageName;
    private String className;
    private List<AnnotatedMethod> methods = new LinkedList<AnnotatedMethod>();


    //参数：将要生成的类的包名,将要生成的类的类名
    public AnnotatedClass(String packageName, String generateClassName) {
        this.packageName = packageName;
        this.className = generateClassName;
    }

    public void addMethod(AnnotatedMethod annotatedMethod) {
        methods.add(annotatedMethod);
    }

    //通过javapoet生成代码，参数为工具类
    public void generateCode(Elements elementUtils, Filer filer) {
        //构造接口类
        TypeSpec.Builder typeBuilder = TypeSpec.interfaceBuilder(className).addModifiers(Modifier.PUBLIC);

        for (AnnotatedMethod m : methods) {
            //构造方法
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(m.getSimpleMethodName())
                    .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                    .returns(TypeName.get(m.getAnnotatedMethodElement().getReturnType()));

            //给方法添加参数
            int i = 1;
            for (VariableElement e : m.getAnnotatedMethodElement().getParameters()) {
                methodBuilder.addParameter(TypeName.get(e.asType()), "param" + String.valueOf(i));
                ++i;
            }
            //将方法添加到接口类上
            typeBuilder.addMethod(methodBuilder.build());
        }

        //构造java文件
        JavaFile javaFile = JavaFile.builder(packageName, typeBuilder.build()).build();

        //输出
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}