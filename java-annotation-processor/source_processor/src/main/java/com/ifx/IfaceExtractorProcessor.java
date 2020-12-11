package com.ifx;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.*;
import java.util.*;
import java.util.stream.*;
import java.io.*;
/**
 * Created by Defias on 2020/07.
 * Description: 注解处理器
 */

@SupportedAnnotationTypes("com.ifx.ExtractInterface")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class IfaceExtractorProcessor extends AbstractProcessor {
    private ArrayList<Element> interfaceMethods = new ArrayList<Element>();
    private Elements elementUtils;
    private ProcessingEnvironment processingEnv;


    //初始化
    @Override
    public void init(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        this.elementUtils = processingEnv.getElementUtils();
    }

    //具体处理注解的逻辑
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        System.out.println("==== process ====");
        for(Element elem: env.getElementsAnnotatedWith(ExtractInterface.class)) {
            System.out.println("=== " + elem + " ===");
            for(Element enclosed : elem.getEnclosedElements()) {
                System.out.println("== " + enclosed + " ==");
                if(enclosed.getKind().equals(ElementKind.METHOD)
                        && enclosed.getModifiers().contains(Modifier.PUBLIC)
                        && !enclosed.getModifiers().contains(Modifier.STATIC)) {
                    interfaceMethods.add(enclosed);
                }
            }
            System.out.println("interfaceMethods: " + interfaceMethods);

            String interfaceName = elem.getAnnotation(ExtractInterface.class).interfaceName();
            System.out.println("interfaceName: " + interfaceName);
            if(interfaceMethods.size() > 0)
                writeInterfaceFile(interfaceName);
        }
        return false;
    }

    private void writeInterfaceFile(String interfaceName) {
        try(Writer writer = processingEnv.getFiler()
                        .createSourceFile(interfaceName)
                        .openWriter()) {
            String packageName = elementUtils.getPackageOf(interfaceMethods.get(0)).toString();
            writer.write("package " + packageName + ";\n");
            writer.write("public interface " + interfaceName + " {\n");
            for(Element elem : interfaceMethods) {
                ExecutableElement method = (ExecutableElement)elem;
                String signature = " public ";
                signature += method.getReturnType() + " ";
                signature += method.getSimpleName();
                signature += createArgList(method.getParameters());
                System.out.println(signature);
                writer.write(signature + ";\n");
            }
            writer.write("}");
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String createArgList(List<? extends VariableElement> parameters) {
        String args = parameters.stream()
                .map(p -> p.asType() + " " + p.getSimpleName())
                .collect(Collectors.joining(", "));
        return "(" + args + ")";
    }
}