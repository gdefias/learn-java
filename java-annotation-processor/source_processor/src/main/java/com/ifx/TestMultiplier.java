package com.ifx;

/**
 * Created by Defias on 2020/07.
 * Description: 使用SOURCE级别的ExtractInterface注解
 功能：根据ExtractInterface注解标注的类自动生成一个接口

 执行：
 javac com/ifx/IfaceExtractorProcessor.java

 javac -processor com.ifx.IfaceExtractorProcessor  com/ifx/TestMultiplier.java -d com/ifx/out
 新生成的接口ITestMultiplier.java的文件位于指定的com/ifx/out下

 */

@ExtractInterface(interfaceName="ITestMultiplier")
public class TestMultiplier {
    public boolean flag = false;
    private int n = 0;

    public int multiply(int x, int y) {
        int total = 0;
        for(int i = 0; i < x; i++)
            total = add(total, y);
        return total;
    }

    public int fortySeven() { return 47; }

    private int add(int x, int y) {
        return x + y;
    }

    public double timesTen(double arg) {
        return arg * 10;
    }

    public static void main(String[] args) {
        TestMultiplier m = new TestMultiplier();
        System.out.println("11 * 16 = " + m.multiply(11, 16));
    }
}