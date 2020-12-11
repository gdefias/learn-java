package com.simple;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 注解处理器
 */

@SupportedAnnotationTypes("com.simple.Simple")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SimpleProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        System.out.println("==== process ====");
        for(TypeElement t : annotations)
            System.out.println(t);

        //getElementsAnnotatedWith: 获得包含指定注解类型的元素的集合
        for(Element el : env.getElementsAnnotatedWith(Simple.class)) {
            display(el);
        }
        return false;
    }

    private void display(Element el) {
        System.out.println("==== " + el + " ====");
        System.out.println(el.getKind() +
                " : " + el.getModifiers() +
                " : " + el.getSimpleName() +
                " : " + el.asType());
        if(el.getKind().equals(ElementKind.CLASS)) {
            TypeElement te = (TypeElement)el;
            System.out.println("----" + el + " " + te + "----");
            System.out.println(te.getQualifiedName());
            System.out.println(te.getSuperclass());
            System.out.println(te.getEnclosedElements());
        }
        if(el.getKind().equals(ElementKind.METHOD)) {
            ExecutableElement ex = (ExecutableElement)el;
            System.out.println("----" + el + " " + ex + "----");
            System.out.print(ex.getReturnType() + " ");
            System.out.print(ex.getSimpleName() + "(");
            System.out.println(ex.getParameters() + ")");
        }
        System.out.println("==== end ====");
    }
}