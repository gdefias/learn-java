/**
 * Created by Defias on 2016/2/28.
 *
 * 断言
 */
package Basic;

/*
断言使用方式：
assert<<布尔表达式>>;
如果<boolean表达式>为true，则程序继续执行。
如果为false，则程序抛出AssertionError，并终止执行

assert<<布尔表达式>>:<<细节描述>>;
如果<boolean表达式>为true，则程序继续执行。
如果为false，则程序抛出java.lang.AssertionError，并输入<错误信息表达式>

assert是为了调试测试程序用，不要在正式生产环境下用，不要用来控制程序的业务流程，另外调试测试程序可以考虑更好的测试JUint来代替其作用
* */

public class JavaAssert {
    public static void main(String[] args) {
        int x = 10;
        if (args.length > 0) {
            try {
                x = Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {
                /* Ignore */
            }
        }
        System.out.println("Testing assertion that x == 10");
        assert x == 11 : "Our assertion failed";
        System.out.println("Test passed");
    }
}

/*
使用断言功能需要使用-ea参数开启，否则不生效:
java -ea JavaAssert
java -ea JavaAssert 11  //java –enableassertions JavaAssert 1

屏蔽断言(默认就是屏蔽断言)：
java JavaAssert 11
java –da JavaAssert 11  //java –disableassertions  JavaAssert 11
* */