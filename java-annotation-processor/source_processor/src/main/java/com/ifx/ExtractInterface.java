package com.ifx;
import java.lang.annotation.*;
/**
 * Created by Defias on 2020/07.
 * Description: SOURCE级注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ExtractInterface {
    String interfaceName() default "-!!-";
}
