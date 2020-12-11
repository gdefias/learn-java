package Concurrency.atomic;

/**
 * Created by Defias on 2020/07.
 * Description: 查看是否原子性操作


 反汇编器

 cd java-basic/base/src/main/java
 javac Concurrency/Atomicity.java
 javap -c Concurrency/Atomicity

 javap 用于帮助开发者深入了解 Java 编译器的机制，主要选项有：
     -c：分解方法代码，即显示每个方法具体的字节码
     -public | protected | package | private：用于指定显示哪种级别的类成员
     -verbose：指定显示更进一步的详细信息
 */

public class Atomicity {
    int i;
    void f1() { i++; }  //非原子操作
    void f2() { i += 3; }
}
