package Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Defias on 2017/3/15.
 *
 * 注解处理器 - 利用反射处理注解

 Annotation接口
 所有注解类型的父接口

 AnnotatedElement接口
 表示目前正在此VM中运行的程序的一个已注解元素。该接口允许反射性地读取注解
 反射相关的Class、Method、Constructor、Field、Package等类都继承了AnnotatedElement接口

 AnnotatedElement API：
 <T extends Annotation> T getAnnotation(Class<T> annotationClass)
 如果存在该元素的指定类型的注解，则返回这些注解，否则返回null

 Annotation[] getAnnotations()
 返回此元素上存在的所有注解

 Annotation[] getDeclaredAnnotations()
 返回直接存在于此元素上的所有注解（不包括父类中被Inherited修饰的注解）

 boolean isAnnotationPresent(Class<? extends Annotation> annotationClass)
 如果指定类型的注解存在于此元素上，则返回 true，否则返回 false

 */

public class TestAnnotationProcessor {
    //注解处理器
    public static void trackUser(Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            if (m.isAnnotationPresent(UseCase.class)) {  //是否存在UseCase注解
                UseCase uc = m.getAnnotation(UseCase.class); //获取UseCase注解
                System.out.println("id: "+uc.id() + " description: " + uc.description());
            }
        }
    }

    //注解处理器
    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for(Method m : cl.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if(uc != null) {
                System.out.println("Found Use Case " +
                        uc.id() + "\t" + uc.description());
                useCases.remove(Integer.valueOf(uc.id()));
            }
        }
        //缺失的用例
        useCases.forEach(i -> System.out.println("Missing use case " + i));
    }

    public static void main(String[] args) {
        trackUser(Userr.class);
        System.out.println();

        List<Integer> useCases = IntStream.range(47, 51).boxed().collect(Collectors.toList());
        trackUseCases(useCases, PasswordUtils.class);
    }
}


class PasswordUtils {
    @UseCase(id = 47, description = "Passwords must contain at least one numeric")
    public boolean validatePassword(String passwd) {
        return (passwd.matches("\\w*\\d\\w*"));
    }

    @UseCase(id = 48)
    public String encryptPassword(String passwd) {
        return new StringBuilder(passwd)
                .reverse().toString();
    }

    @UseCase(id = 49, description = "New passwords can't equal previously used ones")
    public boolean checkForNewPassword(List<String> prevPasswords, String passwd) {
        return !prevPasswords.contains(passwd);
    }
}


class Userr {
    @UseCase(id=10,description = "test1 description")
    public void test1() {
    }

    @UseCase(id=20)
    public void test2() {
    }

    @UseCase(id=30,description = "test3 description")
    public void test3() {
    }
}


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface UseCase {
    public int id() ;
    public String description() default "no description";
}



