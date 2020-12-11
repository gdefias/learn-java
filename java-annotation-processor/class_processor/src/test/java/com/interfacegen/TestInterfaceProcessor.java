package com.interfacegen;

import org.junit.Test;

/**
 * Created by Defias on 2020/07.
 * Description: 使用CLASS级的注解@Interface并测试
 */

public class TestInterfaceProcessor {
    @Interface("ManInterface")
    public void eat() {
        System.out.println("Eat");
    }

    @Test
    public void test1() throws ClassNotFoundException {
        Class<?> c = Class.forName("com.interfacegen.ManInterface");
        System.out.println(c.getName());  //ManInterface接口类
        System.out.println(c.getMethods()[0].getName());  //ManInterface接口类的eat方法
    }
}
