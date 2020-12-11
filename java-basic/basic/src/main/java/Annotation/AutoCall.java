package Annotation;
import java.lang.annotation.*;
/**
 * Created by Defias on 2017/9/6.
 *
 * 自动调用注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoCall {
    String name();
    String tip() default "";
}
