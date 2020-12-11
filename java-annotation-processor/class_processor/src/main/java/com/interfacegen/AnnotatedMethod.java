package com.interfacegen;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by Defias on 2020/07.
 * Description:
 */
public class AnnotatedMethod {
    private ExecutableElement annotatedMethodElement;
    private String simpleMethodName;
    private String simpleClassName;

    public AnnotatedMethod(ExecutableElement annotatedMethodElement) {
        this.annotatedMethodElement = annotatedMethodElement;
        simpleMethodName = annotatedMethodElement.getSimpleName().toString();

        TypeElement parent = (TypeElement) annotatedMethodElement.getEnclosingElement();
        simpleClassName = parent.getQualifiedName().toString();
    }

    public ExecutableElement getAnnotatedMethodElement() {
        return annotatedMethodElement;
    }

    public String getSimpleMethodName() {
        return simpleMethodName;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }
}
