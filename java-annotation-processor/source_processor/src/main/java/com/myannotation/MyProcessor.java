package com.myannotation;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Defias on 2020/07.
 * Description: 定义注解处理器
 */

public class MyProcessor extends AbstractProcessor {

    // Processor初始化回调
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        System.out.println("MyProcessor init");
    }

    // processor处理过程的回调（如果需要生成代码, 就在这个方法中写）
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("process1");
        return true;
    }

    //声明该processor支持的注解类型
    @Override
    public Set<String> getSupportedAnnotationTypes() {

        Set<String> set = new HashSet<>();
        set.add("com.myannotation.MyAnnotation");
        //set.add(MyAnnotation.class.getCanonicalName());
        return set;
    }

    //声明支持的Java版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}