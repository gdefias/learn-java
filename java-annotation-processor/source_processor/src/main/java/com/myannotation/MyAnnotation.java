package com.myannotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Created by Defias on 2020/07.
 * Description:
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface MyAnnotation {
}