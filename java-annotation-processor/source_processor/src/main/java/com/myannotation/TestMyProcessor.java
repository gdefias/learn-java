package com.myannotation;

/**
 * Created by Defias on 2020/07.
 * Description: 使用源码级别注解
 *
 执行：
 cd src/main/java
 javac com/myannotation/MyProcessor.java
 javac -processor com.myannotation.MyProcessor com/myannotation/TestMyProcessor.java
 */

@MyAnnotation
public class TestMyProcessor {
    public static void main(String[] args) {
        System.out.printf("Hello, World!");
    }
}
