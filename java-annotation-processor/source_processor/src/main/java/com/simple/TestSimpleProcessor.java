package com.simple;

/**
 * Created by Defias on 2020/07.
 * Description: 使用SOURCE级别的Simple注解

 执行：
 cd src/main/java
 javac com/simple/SimpleProcessor.java
 javac -processor com.simple.SimpleProcessor com/simple/TestSimpleProcessor.java
 */

@Simple
public class TestSimpleProcessor {
    @Simple
    int i;

    @Simple
    public TestSimpleProcessor() {}

    @Simple
    public void foo() {
        System.out.println("SimpleTest.foo()");
    }

    @Simple
    public void bar(String s, int i, float f) {
        System.out.println("SimpleTest.bar()");
    }

    @Simple
    public static void main(String[] args) {
        @Simple
        TestSimpleProcessor st = new TestSimpleProcessor();
        st.foo();
    }
}