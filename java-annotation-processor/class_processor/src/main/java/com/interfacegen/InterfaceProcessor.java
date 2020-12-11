package com.interfacegen;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import com.google.auto.service.AutoService;
/**
 * Created by Defias on 2020/07.
 * Description: 注解处理器（核心）
 *
 * 功能：[使用方]编译时自动生成一个对应的接口，接口名即为Interface注解中的value值，这样就可以偷懒不写接口类了
 */

@AutoService(Processor.class)
public class InterfaceProcessor extends AbstractProcessor  {
    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;


    //从ProcessingEnvironment获取各种工具
    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        elementUtils = env.getElementUtils();
        filer = env.getFiler();
        typeUtils = env.getTypeUtils();
        messager = env.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Map<String, AnnotatedClass> classMap = new HashMap<String, AnnotatedClass>();
        /*
         * Map: classMap结构
         * key -> 类名
         * value -> AnnotatedClass
         *
         * AnnotatedClass结构
         * String className -> 类名
         * String packageName -> 包名
         * List<AnnotatedMethod> methods  -> 要生成的方法列表
         */

        //得到所有标注了@Interface的Element集合
        Set<? extends Element> elementSet = roundEnv.getElementsAnnotatedWith(Interface.class);

        for (Element e : elementSet) {
            System.out.println("==== " + e + " ====");
            if (e.getKind() != ElementKind.METHOD) {
                error(e, "错误的注解类型，只有方法能够被该 @%s 注解处理", Interface.class.getSimpleName());
                return true;
            }

            ExecutableElement element = (ExecutableElement) e;
            AnnotatedMethod annotatedMethod = new AnnotatedMethod(element);
            String classname = annotatedMethod.getSimpleClassName();
            AnnotatedClass annotatedClass = classMap.get(classname);
            if (annotatedClass == null) {
                PackageElement pkg = elementUtils.getPackageOf(element);
                annotatedClass = new AnnotatedClass(pkg.getQualifiedName().toString(), element.getAnnotation(Interface.class).value());
                annotatedClass.addMethod(annotatedMethod);
                classMap.put(classname, annotatedClass);
            } else
                annotatedClass.addMethod(annotatedMethod);

        }

        //代码生成
        for (AnnotatedClass annotatedClass : classMap.values()) {
            annotatedClass.generateCode(elementUtils, filer);
        }
        return false;
    }

    //定义该注解处理器注册到哪些注解上
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new TreeSet<String>();
        types.add(Interface.class.getCanonicalName());
        return types;
    }

    //指定支持的java版本，一般来说都是支持到最新版本，因此直接返回SourceVersion.latestSupported()即可
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    //使注解处理器程序(APT)必须完成运行而不崩溃
    private void error(Element e, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }
}
