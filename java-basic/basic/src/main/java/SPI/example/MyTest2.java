package SPI.example;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * 第二个实现类
 */

public class MyTest2 implements ITest {
    public void test(String data) throws Exception {
        System.out.println("my test2222:" + data);
    }

    public void test0(String data) throws Exception {
        System.out.println("test02222222:" + data);
    }
}