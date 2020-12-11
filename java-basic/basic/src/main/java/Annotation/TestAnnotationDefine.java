package Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import javax.validation.Payload;

/**
 * Created by Defias on 2017/3/16.
 *
 * 注解 - 注解的定义

 自定义注解时，自动继承了java.lang.annotation.Annotation接口，在定义注解时，不能继承其他的注解或接口

 注解的属性也叫做成员变量，注解只有成员变量，没有方法
 注解的成员变量在注解的定义中以"无形参的方法"形式来声明，其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型

 通过default来声明参数的默认值， 参数成员只能用八种基本数据类型(byte,short,char,int,long,float,double,boolean)和
 String,Enum,Class,Annotations以及这些类型的数组

 如果注解中的元素以value来命名，并且在应用该注解的时候，如果该元素是唯一需要赋值的一个元素，那么此时无需使用名-值对的这种语法，
 而只需要在括号内给出value元素所需的值即可

 注解中可以包含常量或枚举

 默认值限制
 元素要么有默认值，要么就在使用注解时提供元素的值
 任何非基本类型的元素， 无论是在源代码声明时还是在注解接口中定义默认值时，都不能使用 null 作为其值

 */



public class TestAnnotationDefine {
    @Test(10)
    public void test1() {
    }

    @Student
    String student1;

    @Student(10)
    String student2;

    @Student(name = "Tom", age = 17)
    String student3;

    @Student(name = "jack", other = @Extra(isMonitor = true, region = "Canada"))
    String student4;

    public static void main(String[] args) {
        System.out.println("定义注解！");
    }
}



//定义注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Test {

    public int value() ;

    public String description() default "";
}



@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Extra {
    boolean isMonitor() default false;
    String region() default "";
    String hobby() default "";
}

//嵌套注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Student {
    int value() default 0;
    String name() default "";
    int age() default 0;
    Extra  other() default @Extra(isMonitor = false, region = "China");
}


//容器注解: 用来存放其它注解的地方。它本身也是一个注解
//它里面必须要有一个value的属性，属性类型是一个被@Repeatable注解过的注解数组
@interface Persons {
    Person[]  value();
}


@Repeatable(Persons.class)  //@Repeatable后面括号中的类相当于一个容器注解
@interface Person {
    String role() default "";
}

@Person(role="artist")
@Person(role="coder")
@Person(role="PM")
class SuperMan{

}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface Roles {
    String PUBLIC = "PUBLIC";   //注解中可以包含常量或枚举
    String ANY = "ANY";

    String[] value() default {"ANY"};
}


@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@interface IsIntEnum {
    Class<? extends Enum<?>> enumType();

    String message() default "should be {enumType} type";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
